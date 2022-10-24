package application.Models;

import java.io.Serializable;

public class Module implements Serializable {
	
	private String name;
	private String id;
	
	public Module(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String toString()
	 {
     String s = "Module Name: " + name + " Module ID: " + id + "\n";
	   return s;
	 }

}
