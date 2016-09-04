package parser;

public class MyNode{

	private int id;
	
	private String name;

	private String type;
	
	public MyNode(int id, String name, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		//result = prime * result + ((name == null) ? 0 : name.hashCode());
		//result = prime * result + ((type == null) ? 0 : type.hashCode());
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MyNode))
			return false;
		MyNode other = (MyNode) obj;
		if (id != other.id)
			return false;
		return true;
	}




	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "id= " + id + ",  name= " + name + ",  type= " + type;
	}
	
	
	
}
