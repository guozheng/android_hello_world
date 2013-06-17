package codepath.demos.helloworlddemo;

import java.io.Serializable;
import java.util.List;

public class Users implements Serializable {
	List<String> names;
	private static final long serialVersionUID = 5177222050535318633L;
	
	public Users (List<String> names) {
		this.names = names;
	}
	
	public String toString() {
		return this.names.toString();
	}
}
