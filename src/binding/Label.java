package binding;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "label")
@XmlType(propOrder = {"deltaX", "deltaY"})
public class Label {

	private int deltaX;
	
	private int deltaY;

	
	public Label(int deltaX, int deltaY) {
		super();
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	
	
	public Label() {
		super();
	}



	/**
	 * @return the deltaX
	 */
	@XmlElement(name = "deltaX")
	public int getDeltaX() {
		return deltaX;
	}

	/**
	 * @param deltaX the deltaX to set
	 */
	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	/**
	 * @return the deltaY
	 */
	@XmlElement(name = "deltaY")
	public int getDeltaY() {
		return deltaY;
	}

	/**
	 * @param deltaY the deltaY to set
	 */
	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}
	
	
}
