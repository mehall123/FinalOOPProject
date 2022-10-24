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

public class ModuleQueries {
	private ModuleList cl;
	private String loadModule="\0";
	
	public ModuleQueries(){
		cl = new ModuleList();
	}
	
	public String addModuletToList(String name, String id)
	{
		int a = 0;
		String message = "Module could not be added\n";
		if (name.length() < 5){
		a = a + 1;
		message = message.concat("Module Name needs to be bigger than 4 letters\n");
		}
		if(cl.getSize() > 0) {
			for (int i = 0;i<cl.getSize();i++)
			{
			   if(cl.getModule(i).getId().equals(id)) {
				  i = cl.getSize();
				  a = a + 1;
				  message = message.concat("Module ID is already tied to another module\n");
			   }
			}
			}
		if(id.length() != 5) {
			
			a = a + 1;
			message = message.concat("Module ID needs to be a unique 5 digit number\n");
		}
 	
		if(a == 0){
		Module c = new Module(name, id);
		cl.addModule(c);
		message = "Module added succesfully";
		}
		return message;
	}
	
	public String removeModuleFromList(String name, String id)
	{
		String message = "Module could not be found and removed";
		if(cl.getSize() > 0) {
		for (int i = 0;i<cl.getSize();i++)
		{
		   if(cl.getModule(i).getId().equals(id) && cl.getModule(i).getName().equals(name)) {
			  cl.removeModule(i);
			  i = cl.getSize();
			  message = "Module was suuccessfully removed";
		   }
		}
		}
		return message;
		
	}
	public Module getModuleFromList(int i)
	{
		loadModule="\0";		
	    return cl.getModule(i);
	}
	public String getListModule()
	{
		loadModule="\0";

		if(cl.getSize() > 0) {
		
		for (int i = 0;i<cl.getSize();i++)
		{
		    loadModule = loadModule+cl.getModule(i);	
		}	
		return loadModule;
		}
		else {
			return "The module list is empty";
		}
	}
	
	public void saveModuleList()
	{
		
		    try {
				FileOutputStream f = new FileOutputStream(new File("moduleList.txt"));
				ObjectOutputStream o = new ObjectOutputStream(f);
				for (int i = 0;i<cl.getSize();i++) {
				// Write objects to file
				o.writeObject(cl.getModule(i));
				}
				o.close();
				f.close();

			} catch (FileNotFoundException e) {
				loadModule = "No File found";
			} catch (IOException e) {
				loadModule = "Error initializing stream";
			}
			
	}
	
	public String loadModuleList()
	{
		loadModule="\0";
		cl.clearModules();
		    try { 	
				FileInputStream fi = new FileInputStream(new File("moduleList.txt"));
				ObjectInputStream oi = new ObjectInputStream(fi);
                if(fi.available() > 0) {
				for (int i = 0;i<fi.available();i++)  {
				// Read objects
				Module module = (Module) oi.readObject();
				
				cl.addModule(module);
				loadModule = loadModule+module.toString();
				}
                }else {
                	loadModule = "The module list is empty";
                }
				oi.close();
				fi.close();

			} catch (FileNotFoundException e) {
				loadModule = "No File found";
			} catch (IOException e) {
				loadModule = "Error initializing stream";
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return loadModule;		
	}
	public int getSize (){
		return cl.getSize();
	}
}
