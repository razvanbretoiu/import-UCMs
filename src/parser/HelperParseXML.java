package parser;

import java.io.File; 

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import binding.Binding;
import receive.ReceiveObj;

public class HelperParseXML {

	private Document doc;
	
	public HelperParseXML(Document doc) {
		super();
		this.doc = doc;
	}

	public String appendNewTagOnNodeConnection(int targetID, int stubID, String typeBinding, String idBinding){
		String rez = "";
		NodeList ucmNodes = doc.getElementsByTagName("connections");
		for (int i = 0; i < ucmNodes.getLength(); i++){
			Node connection = ucmNodes.item(i);
			
			NodeList list = connection.getChildNodes();
			boolean target = false;
			boolean source = false;
			rez = "";
			//System.out.println(list.getLength());
			for (int j = 0; j < list.getLength();j++){
				 Node node = list.item(j);
				 // System.out.println(node.getNodeName());
				 if ("id".equals(node.getNodeName())){
					 rez = node.getTextContent();
				 }
				 if ("target".equals(node.getNodeName())){
					 if (Integer.parseInt(node.getTextContent()) == targetID){
						 target = true;
					 }
				 }
				 if ("source".equals(node.getNodeName())){
					 if (Integer.parseInt(node.getTextContent()) == stubID){
						 source = true;
					 }
				 }
				 
			}
			
			if (target == true && source == true){
				// append a new node to this connection node
				Element typeTag = doc.createElement(typeBinding);
				typeTag.appendChild(doc.createTextNode(idBinding));
				connection.appendChild(typeTag);

				return rez;
			
			}
		}
		
		return rez;
	}

	public void appendTypeBindingToSpecificNode(int idNode, String typeTag, String idBinding){
		NodeList ucmNodes = doc.getElementsByTagName("nodes");
		for (int i = 0; i < ucmNodes.getLength(); i++){
			Node simpleNode = ucmNodes.item(i);
			Node idNod = simpleNode.getFirstChild().getNextSibling();
			if (idNod != null){
				if (Integer.parseInt(idNod.getFirstChild().getNodeValue()) == idNode){
					//append
					Element tag = doc.createElement(typeTag);
					tag.appendChild(doc.createTextNode(idBinding));
					simpleNode.appendChild(tag);
				}
			}
		}
	}
	
	public void appendBindingObjectToDocument(int idStub, Binding binding){
		NodeList ucmNodes = doc.getElementsByTagName("nodes");
		for (int i = 0; i < ucmNodes.getLength(); i++){
			Node simpleNode = ucmNodes.item(i);
			Node idNod = simpleNode.getFirstChild().getNextSibling();
			if (idNod != null){
				if (Integer.parseInt(idNod.getFirstChild().getNodeValue()) == idStub){
					//append
					try {
						JAXBContext jaxb = JAXBContext.newInstance(binding.getClass());
						Marshaller marshaller = jaxb.createMarshaller();
						marshaller.marshal(binding, simpleNode);
						
					} catch (JAXBException e) {
						e.printStackTrace();
					}
					
				}
			}
		}
	}
	
	/**
	 * @return the doc
	 */
	public Document getDoc() {
		return doc;
	}



	/**
	 * @param doc the doc to set
	 */
	public void setDoc(Document doc) {
		this.doc = doc;
	}



	public static ReceiveObj newInstanceUnmarshaller(File file){
		ReceiveObj obj = new ReceiveObj();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance( obj.getClass());
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			obj = (ReceiveObj) jaxbUnmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
		return obj;
	}
	
}
