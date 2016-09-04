package receive;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "RECEIVEOBJ" )
public class ReceiveObj {

	private int idUCMSource;
	
	private int idUCMTarget;
	
	private int stubSource;
	
	private List<StartPoint> startPoint;
	
	private List<EndPoint> endPoint;

	
	
	public ReceiveObj(int idUCMSource, int idUCMTarget, int stubSource, List<StartPoint> startPoint,
			List<EndPoint> endPoint) {
		super();
		this.idUCMSource = idUCMSource;
		this.idUCMTarget = idUCMTarget;
		this.stubSource = stubSource;
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	public ReceiveObj() {
		super();
	}



	/**
	 * @return the idUCMSource
	 */
	public int getIdUCMSource() {
		return idUCMSource;
	}

	/**
	 * @param idUCMSource the idUCMSource to set
	 */
	@XmlElement( name = "idUCMSource" )
	public void setIdUCMSource(int idUCMSource) {
		this.idUCMSource = idUCMSource;
	}

	/**
	 * @return the idUCMTarget
	 */
	public int getIdUCMTarget() {
		return idUCMTarget;
	}

	/**
	 * @param idUCMTarget the idUCMTarget to set
	 */
	@XmlElement( name = "idUCMTarget" )
	public void setIdUCMTarget(int idUCMTarget) {
		this.idUCMTarget = idUCMTarget;
	}

	/**
	 * @return the stubSource
	 */
	public int getStubSource() {
		return stubSource;
	}

	/**
	 * @param stubSource the stubSource to set
	 */
	@XmlElement( name = "stubSource" )
	public void setStubSource(int stubSource) {
		this.stubSource = stubSource;
	}

	/**
	 * @return the startPoint
	 */
	public List<StartPoint> getStartPoint() {
		return startPoint;
	}

	/**
	 * @param startPoint the startPoint to set
	 */
	@XmlElement( name = "startPoint" )
	public void setStartPoint(List<StartPoint> startPoint) {
		this.startPoint = startPoint;
	}

	/**
	 * @return the endPoint
	 */
	public List<EndPoint> getEndPoint() {
		return endPoint;
	}

	/**
	 * @param endPoint the endPoint to set
	 */
	@XmlElement( name = "endPoint" )
	public void setEndPoint(List<EndPoint> endPoint) {
		this.endPoint = endPoint;
	}
	
	
}
