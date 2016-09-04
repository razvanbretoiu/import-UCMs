package receive;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="startPoint")
public class StartPoint {

	private int id;
	
	private int idTarget;

	
	
	public StartPoint(int id, int idTarget) {
		super();
		this.id = id;
		this.idTarget = idTarget;
	}

	
	
	public StartPoint() {
		super();
	}



	/**
	 * @param id the id to set
	 */
	@XmlElement(name="id")
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param idTarget the idTarget to set
	 */
	@XmlElement(name="idTarget")
	public void setIdTarget(int idTarget) {
		this.idTarget = idTarget;
	}



	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the idTarget
	 */
	public int getIdTarget() {
		return idTarget;
	}
}
