package application.Models;

public class Grade{
	
	private String name;
	private String id;
	private int score;
	
	public Grade(Module module, int score) {
		this.name = module.getName();
		this.id =  module.getId();
		this.score = score;
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
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String toString()
	 {
     String s = "Module Name: " + name + " Module ID: " + id + " Score: " + score + "\n";
	   return s;
	 }
}
