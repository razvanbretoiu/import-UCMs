package binding;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name ="precondition")
@XmlType(propOrder = {"expression", "desc", "label"})
public class Precondition {

	private boolean expression;
	
	private Desc desc;
	
	private Label label;

	
	public Precondition(boolean expresion, Desc desc, Label label) {
		super();
		this.expression = expresion;
		this.desc = desc;
		this.label = label;
	}

	
	
	public Precondition() {
		super();
	}



	/**
	 * @return the expresion
	 */
	@XmlElement(name = "expression")
	public boolean isExpression() {
		return expression;
	}

	/**
	 * @param expresion the expresion to set
	 */
	public void setExpression(boolean expresion) {
		this.expression = expresion;
	}

	/**
	 * @return the desc
	 */
	@XmlElement(name = "desc")
	public Desc getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(Desc desc) {
		this.desc = desc;
	}

	/**
	 * @return the label
	 */
	@XmlElement(name = "label")
	public Label getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(Label label) {
		this.label = label;
	}
	
	
}
