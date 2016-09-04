package binding;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement( name = "bindings")
@XmlType(propOrder = {"id", "probability", "replicationFactor", "in", "out", "plugin", "precondition"})
public class Binding {

	private String id;
	
	private int probability = 100;
	
	private int replicationFactor = 1;
	
	private List<IN> in;
	
	private List<OUT> out;
	
	private int plugin;

	private Precondition precondition = new Precondition(true, new Desc("",""), new Label(0, 0));

	public Binding(String id, List<IN> in, List<OUT> out, int plugin) {
		super();
		this.id = "Z151_id_ucm.map.impl.PluginBindingImpl_" + id;
		this.in = in;
		this.out = out;
		this.plugin = plugin;
	}

	public Binding() {
		super();
	}

	/**
	 * @return the id
	 */
	@XmlElement( name = "id")
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = "Z151_id_ucm.map.impl.PluginBindingImpl_" + id;
	}

	/**
	 * @return the in
	 */
	@XmlElement( name = "in")
	public List<IN> getIn() {
		return in;
	}

	/**
	 * @param in the in to set
	 */
	public void setIn(List<IN> in) {
		this.in = in;
	}

	/**
	 * @return the out
	 */
	@XmlElement( name = "out")
	public List<OUT> getOut() {
		return out;
	}

	/**
	 * @param out the out to set
	 */
	public void setOut(List<OUT> out) {
		this.out = out;
	}

	/**
	 * @return the plugin
	 */
	@XmlElement( name = "plugin")
	public int getPlugin() {
		return plugin;
	}

	/**
	 * @param plugin the plugin to set
	 */
	public void setPlugin(int plugin) {
		this.plugin = plugin;
	}

	/**
	 * @return the probability
	 */
	@XmlElement( name = "probability")
	public int getProbability() {
		return probability;
	}

	/**
	 * @return the replicationFactor
	 */
	@XmlElement( name = "replicationFactor")
	public int getReplicationFactor() {
		return replicationFactor;
	}

	/**
	 * @return the precondition
	 */
	@XmlElement( name = "precondition")
	public Precondition getPrecondition() {
		return precondition;
	}
	
	
}
