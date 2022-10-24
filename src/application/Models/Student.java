//Micheal O Sullivan R00102982
package application.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Student implements Serializable {
	private Name name;
	private String id;
	private LocalDate dob;
	private LocalDate currentDate;
	private ArrayList<Grade> grades = new ArrayList<Grade>();
	
	public Student(String fName, String mName, String lName, String id, LocalDate dob) {
		this.name = new Name(fName, mName, lName);
		this.id = id;
		this.dob = dob;
	}
	public String getfName() {
		return name.getfName();
	}
	public void setfName(String fName) {
		this.name.setfName(fName);
	}
	public String getmName() {
		return name.getmName();
	}
	public void setmName(String mName) {
		this.name.setmName(mName);
	}
	public String getlName() {
		return name.getlName();
	}
	public void setlName(String lName) {
		this.name.setfName(lName);
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public int getAge() {
		int age = Period.between(dob, currentDate.now()).getYears();
		return age;
	}
	
	public void addGrade(Grade grade) {
		this.grades.add(grade);
	}
	
	public void removeGrade(int i) {
		this.grades.remove(i);
	}
	
	public Grade getGrade(int i)
	{
		if ((i>-1) && (i < grades.size()))
     		return grades.get(i);
		return null;
	}
	
	public int getGradesSize (){
		return grades.size();
	}
	
	public void clearGrades (){
		grades.clear();
	}
	
	 public ArrayList<Grade> getList (){
	     return grades;
	 }
	public String toString()
	 {
      String s = "Name: " + name + " Student ID: " + id + " Date of Birth: " + dob + " Age: " + this.getAge() + "\n";
	   return s;
	 }
}
