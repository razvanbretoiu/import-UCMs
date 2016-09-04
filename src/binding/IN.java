package binding;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "in")
@XmlType(propOrder = {"id", "startPoint", "stubEntry"})
public class IN {

	private String id;
	
	private int startPoint;
	
	private String stubEntry;

	public IN(String id, int startPoin, String stubEntry) {
		super();
		this.id = "Z151_id_ucm.map.impl.InBindingImpl_" +id;
		this.startPoint = startPoin;
		this.stubEntry = stubEntry;
	}
	
	
	
	public IN() {
		super();
	}



	/**
	 * @return the id
	 */
	@XmlElement(name = "id")
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = "Z151_id_ucm.map.impl.InBindingImpl_" + id;
	}

	/**
	 * @return the startPoin
	 */
	@XmlElement(name = "startPoint")
	public int getStartPoint() {
		return startPoint;
	}

	/**
	 * @param startPoin the startPoin to set
	 */
	public void setStartPoint(int startPoin) {
		this.startPoint = startPoin;
	}

	/**
	 * @return the stubEntry
	 */
	@XmlElement(name = "stubEntry")
	public String getStubEntry() {
		return stubEntry;
	}

	/**
	 * @param stubEntry the stubEntry to set
	 */
	public void setStubEntry(String stubEntry) {
		this.stubEntry = stubEntry;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "IN [id=" + id + ", startPoint=" + startPoint + ", stubEntry=" + stubEntry + "]";
	}
	
	
}
