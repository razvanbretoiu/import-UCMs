package binding;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "out")
@XmlType( propOrder ={"id", "endPoint", "stubExit"})
public class OUT {

	private String id;
	
	private int endPoint;
	
	private String stubExit;

	
	public OUT(String id, int endPoint, String stubExit) {
		super();
		this.id = "Z151_id_ucm.map.impl.OutBindingImpl_" + id;
		this.endPoint = endPoint;
		this.stubExit = stubExit;
	}

	
	
	public OUT() {
		super();
	}



	/**
	 * @return the id
	 */
	@XmlElement(name ="id")
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = "Z151_id_ucm.map.impl.OutBindingImpl_" + id;
	}

	/**
	 * @return the endPoint
	 */
	@XmlElement(name = "endPoint")
	public int getEndPoint() {
		return endPoint;
	}

	/**
	 * @param endPoint the endPoint to set
	 */
	public void setEndPoint(int endPoint) {
		this.endPoint = endPoint;
	}

	/**
	 * @return the stubExit
	 */
	@XmlElement(name = "stubExit")
	public String getStubExit() {
		return stubExit;
	}

	/**
	 * @param stubExit the stubExit to set
	 */
	public void setStubExit(String stubExit) {
		this.stubExit = stubExit;
	}
	
	
}
