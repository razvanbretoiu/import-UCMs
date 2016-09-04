/**
 * 
 */
package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import binding.Binding;
import binding.IN;
import binding.OUT;
import receive.EndPoint;
import receive.ReceiveObj;
import receive.StartPoint;

/**
 * @author Bretoiu Razvan
 *
 */
public class ParseXML {

	private static final String errorFilePath = "E:\\WorkSpace\\error.txt";
	
	private static final String variables = "variables";
	private static final String ucmMaps = "ucmMaps";
	private static final String components = "components";
	private static final String responsibilities = "responsibilities";
	private static final String nodes = "nodes";
	private static final String contRefs = "contRefs";
	private static final String scenarioGroups = "scenarioGroups";
	private static final String scenarioDefs = "scenarioDefs";
	
	private Collection<String> fileNodes = Arrays.asList(variables, ucmMaps, components, responsibilities, nodes, contRefs, scenarioDefs, scenarioDefs); 
	private Collection<MyNode> wrongNodes = new ArrayList<MyNode>();
	
	private Document mergeDoc;
	
	
	private void importPrivacyUCM( Document privateDoc, String nodeToImport) throws Exception{

		NodeList list = mergeDoc.getElementsByTagName("ucmspec");
		Node copyTo = list.item(list.getLength() - 1);
		
		NodeList ucmNodes = privateDoc.getElementsByTagName(nodeToImport);
		for (int i = 0 ; i < ucmNodes.getLength(); i++){
			Node simpleNode = ucmNodes.item(i);
			Node copyOfn = mergeDoc.importNode(simpleNode, true);
			copyTo.appendChild(copyOfn);
		}
	}	
	
	public void mergeFiles(File firstFile, File privateFile, File scriptFile, String  resultFilePath){
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
			Document privateDoc = builder.parse(privateFile);
			mergeDoc =  builder.parse(firstFile);
	
			writeErrorsInFile(privateDoc, errorFilePath);
			
			importPrivacyUCM(privateDoc, variables);
			importPrivacyUCM(privateDoc, ucmMaps);
			importPrivacyUCM(privateDoc, components);
			importPrivacyUCM(privateDoc, responsibilities);
			importPrivacyUCM(privateDoc, scenarioGroups);
			importPrivacyUCM(privateDoc, scenarioDefs);

			
			mergeDoc = setBindings(scriptFile);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(mergeDoc), new StreamResult(new File(resultFilePath)));
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}	
	
	private Document setBindings(File scriptFile){
		//make connection
		if (scriptFile == null)
			return this.mergeDoc;
		ReceiveObj obj = HelperParseXML.newInstanceUnmarshaller(scriptFile);
		HelperParseXML helperParse = new HelperParseXML(this.mergeDoc);
		
		List<IN> listIN = new ArrayList<IN>();
		List<OUT> listOUT = new ArrayList<OUT>();
		
		int nr = 0;
		for (StartPoint start : obj.getStartPoint()){
			
			IN in = new IN("1110000" + nr, start.getId(), "AA");
			String idNodeConnection = helperParse.appendNewTagOnNodeConnection(obj.getStubSource(),start.getIdTarget() , "inBindings", in.getId());
			in.setStubEntry(idNodeConnection);
			helperParse.appendTypeBindingToSpecificNode(start.getId(), "inBindings", in.getId());
			listIN.add(in);
			nr++;
		}
		nr = 0;
		for (EndPoint end : obj.getEndPoint()){
			OUT out = new OUT("1220000" + nr, end.getId(), "AA");
			String idNodeConnection = helperParse.appendNewTagOnNodeConnection(end.getIdTarget(), obj.getStubSource() , "outBindings", out.getId());
			out.setStubExit(idNodeConnection);
			helperParse.appendTypeBindingToSpecificNode(end.getId(), "outBindings", out.getId());
			listOUT.add(out);
			nr++;
		}
		//obj binding care trebuie data append in ucm source !
		Binding binding = new Binding("1000000000", listIN, listOUT, obj.getIdUCMTarget());
		//append binding object tu document
		helperParse.appendBindingObjectToDocument(obj.getStubSource(), binding);
		
		return helperParse.getDoc();
	}
	
	
	private void getAllNodes(Document doc, Set<MyNode> myNodes, String type){
		
		NodeList list = doc.getElementsByTagName(type);
		for (int i = 0 ; i < list.getLength(); i++){
			Node simpleNode = list.item(i);
			MyNode myNode = simpleNodeToMyNode(simpleNode, type);
			if (myNode != null){
				myNodes.add(myNode);
			}
		}
	}
	
	private void checkNode(Document doc, Set<MyNode> firstFileNodes, String type){
		NodeList list = doc.getElementsByTagName(type);
		for (int i = 0; i < list.getLength();i++){
			Node simpleNode = list.item(i);
			MyNode myNode = simpleNodeToMyNode(simpleNode, type);
			if (myNode != null){
				if (firstFileNodes.contains(myNode)){
					wrongNodes.add(myNode);
				}
			}
		}
	}
	
	
	private void writeErrorsInFile(Document privateDoc, String errorFilePath) throws FileNotFoundException, UnsupportedEncodingException{
		
		Set<MyNode> allNodesFromFirstFile = getNodesFromFile(mergeDoc);
		checkEqualsNodes(privateDoc, allNodesFromFirstFile);
		
		if (!wrongNodes.isEmpty()){
			PrintWriter writer = new PrintWriter(errorFilePath, "UTF-8");
			for (Iterator<MyNode> iterator = wrongNodes.iterator(); iterator.hasNext();) {
				MyNode myNode = iterator.next();
				writer.write(myNode.toString()+ "\n");
			}
			writer.close();
		}
	}	
	
	public Collection<MyNode> getWrongNodes(){
		return this.wrongNodes;
	}

	private MyNode simpleNodeToMyNode(Node simpleNode, String type){
		simpleNode = simpleNode.getFirstChild();
		Node idNode = simpleNode.getNextSibling();
		if (idNode != null){
			Node nameNode = idNode.getNextSibling().getNextSibling();
			return new MyNode(Integer.parseInt(idNode.getFirstChild().getNodeValue()), 
					nameNode.getFirstChild().getNodeValue(), type);
		}
		return null;
	}
	
	
	private Set<MyNode> getNodesFromFile(Document doc){
		Set<MyNode> myNodes = new HashSet<MyNode>();
		for (String type : fileNodes){
			getAllNodes(doc, myNodes, type);
		}
		
		return myNodes;
	}
	
	
	private void checkEqualsNodes(Document doc, Set<MyNode> firstFileNodes){
		for (String type : fileNodes){
			checkNode(doc, firstFileNodes, type);
		}
	}
}
