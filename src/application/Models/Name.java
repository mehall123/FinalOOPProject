package application.Models;

import java.io.Serializable;

public class Name implements Serializable{
	private String fName;
	private String mName;
	private String lName;
	
	public Name(String fName, String mName, String lName) {
		this.fName = fName;
		this.mName = mName;
		this.lName = lName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}
	public String toString(){
		String s = fName + " " + mName + " " + lName;
		return s;
	}
}
