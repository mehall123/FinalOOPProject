//Micheal O Sullivan R00102982
package application.Controllers;

import java.io.File;
import application.Models.Module;
import application.Models.*;
import application.Views.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class StudentQueries {

	private StudentList cl;
	private String loadStudent="\0";
	
	public StudentQueries(){
		cl = new StudentList();
	}
	
	public String addStudentToList(String fName, String mName, String lName, String id, LocalDate dob)
	{
		int a = 0;
		String message = "Student could not be added\n";
		if (fName.length() < 3){
		a = a + 1;
		message = message.concat("First Name needs to be bigger than 2 letters\n");
		}
		if (mName.length() < 3){
		a = a + 1;
		message = message.concat("Middle Name needs to be bigger than 2 letters\n");
		}
		if (lName.length() < 3){
		a = a + 1;
		message = message.concat("Last Name needs to be bigger than 2 letters\n");
		}
		if(cl.getSize() > 0) {
			for (int i = 0;i<cl.getSize();i++)
			{
			   if(cl.getStudent(i).getId().equals(id)) {
				  i = cl.getSize();
				  a = a + 1;
				  message = message.concat("Student number is already tied to another student\n");
			   }
			}
			}
		if(id.length() != 10) {
			
			a = a + 1;
			message = message.concat("Student ID needs to be a unique 10 digit number\n");
		}
 	
		if(dob != null) {
		if(Period.between(dob, LocalDate.now()).getYears() < 18 && Period.between(dob, LocalDate.now()).getYears() < 65) {
		a = a + 1;
		message = message.concat("Age needs to be between 18 and 65\n");
		}
	    }else {
	    	a = a + 1;
	    	message = message.concat("Age needs to be between 18 and 65\n");
	    }
		if(a == 0){
		Student c = new Student(fName, mName, lName, id, dob);
		cl.addStudent(c);
		message = "Student added succesfully";
		}
		return message;
	}
	
	public String removeStudentFromList(String fName, String mName, String lName, String id, LocalDate dob)
	{
		String message = "Student could not be found and removed";
		if(cl.getSize() > 0) {
		for (int i = 0;i<cl.getSize();i++)
		{
		   if(cl.getStudent(i).getId().equals(id) && cl.getStudent(i).getfName().equals(fName)
				   && cl.getStudent(i).getmName().equals(mName) && cl.getStudent(i).getDob().equals(dob)) {
			  cl.removeStudent(i);
			  i = cl.getSize();
			  message = "Student was suuccessfully removed";
		   }
		}
		}
		return message;
		
	}
	public Student getStudentFromList(int i)
	{
		loadStudent="\0";	
	    return cl.getStudent(i);
	}
	public String getListStudent()
	{
		loadStudent="\0";

		if(cl.getSize() > 0) {
		
		for (int i = 0;i<cl.getSize();i++)
		{
		    loadStudent = loadStudent+cl.getStudent(i);	
		}	
		return loadStudent;
		}
		else {
			return "The student list is empty";
		}
	}
	
	public void saveStudentList()
	{
		
		    try {
				FileOutputStream f = new FileOutputStream(new File("studentList.txt"));
				ObjectOutputStream o = new ObjectOutputStream(f);
				for (int i = 0;i<cl.getSize();i++) {
				// Write objects to file
				o.writeObject(cl.getStudent(i));
				}
				o.close();
				f.close();

			} catch (FileNotFoundException e) {
				loadStudent = "No File found";
			} catch (IOException e) {
				loadStudent = "Error initializing stream";
			}
			
	}
	
	public String loadStudentList()
	{
		loadStudent="\0";
		cl.clearStudents();
		    try { 	
				FileInputStream fi = new FileInputStream(new File("studentList.txt"));
				ObjectInputStream oi = new ObjectInputStream(fi);
                if(fi.available() > 0) {
				for (int i = 0;i<fi.available();i++)  {
				// Read objects
				Student student = (Student) oi.readObject();
				
				cl.addStudent(student);
				loadStudent = loadStudent+student.toString();
				}
                }else {
                	loadStudent = "The student list is empty";
                }
				oi.close();
				fi.close();

			} catch (FileNotFoundException e) {
				loadStudent = "No File found";
			} catch (IOException e) {
				loadStudent = "Error initializing stream";
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return loadStudent;		
	}
	public String addGradeToStudent(Student student, Module module, String score)
	{
		String message = "Grade Added Successfully";
		int i= 101;
		try {
			i = Integer.parseInt(score);
		  } catch (NumberFormatException e1) {
		  message = "Grade need to be a number 0-100 inclusive";
		  }
		if(i >= 0 && i <= 100) {
			Grade grade = new Grade(module, i); 
			student.addGrade(grade);
		}
		else {
			message = "Grade need to be a number 0-100 inclusive";
		}
		return message;
	}
	public ArrayList<Grade> getGradeList(String studentID)
	{
		return cl.getStudentWithID(studentID).getList();
	}
	public int getSize (){
		return cl.getSize();
	}
}
