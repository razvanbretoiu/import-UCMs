package binding;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement( name = "desc")
@XmlType(propOrder = {"label", "description"})
public class Desc {

	private String label;
	
	private String description;

	
	
	public Desc(String label, String description) {
		super();
		this.label = label;
		this.description = description;
	}

	public Desc() {
		super();
	}

	/**
	 * @return the label
	 */
	@XmlElement(name = "label")
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the description
	 */
	@XmlElement(name = "description")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
