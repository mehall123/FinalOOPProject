package application.Views;

import application.Controllers.ModuleQueries;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ModulePanel extends Tab{
	private TextArea moduleDetails;
	protected static ModuleQueries moduleControl = new ModuleQueries();
	
	public ModulePanel() {
		Label nameLabel, idLabel;
		TextField nameField, idField;
		Button addButton, removeButton,listButton, loadButton, saveButton, exitButton;
		
		setText("Add/Remove Module");
	    nameLabel = new Label("Module Name: ");	
	    idLabel = new Label("Module ID: ");
	    
	    nameField = new TextField();
	    idField = new TextField();
	    
	    addButton = new Button ("Add");
	    addButton.setOnAction(e ->  
	    moduleDetails.setText(moduleControl.addModuletToList(nameField.getText(), idField.getText())));
	    
	    removeButton = new Button("Remove");
	    removeButton.setOnAction(e ->  
	    moduleDetails.setText(moduleControl.removeModuleFromList(nameField.getText(), idField.getText())));
	    
	    listButton = new Button("List");
	    listButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 	String allModule = moduleControl.getListModule();
				 	moduleDetails.setText(allModule);
			 	}
			 });	
	    loadButton = new Button ("Load");
	    loadButton.setOnAction(new EventHandler<ActionEvent>() {
			 @Override
			 public void handle(ActionEvent event) {
				 	String loadModule = moduleControl.loadModuleList();
				 	moduleDetails.setText(loadModule);
			 	}
			 }); 
	    saveButton = new Button("Save");
	    saveButton.setOnAction(e -> moduleControl.saveModuleList());
	    exitButton = new Button("Exit");
	    exitButton.setOnAction(e ->   ExitWindow2.display());
	    
	    moduleDetails = new TextArea(moduleControl.loadModuleList());
	       
	    HBox r1 = new HBox(10);
	    r1.getChildren().addAll(nameLabel, nameField);
	    
	    HBox r2 = new HBox(10);
	    r2.getChildren().addAll(idLabel, idField);
	    
	    HBox r4 = new HBox(20);
	    r4.getChildren().addAll(addButton,removeButton,listButton);
	    
	    HBox r5 = new HBox(10);
	    r5.getChildren().addAll(moduleDetails);
	    
	    HBox r6 = new HBox(20);
	    r6.getChildren().addAll(loadButton, saveButton, exitButton);
	    
	    moduleDetails.setEditable(false);
	    setClosable(false);
	    VBox layout = new VBox(10);
	    layout.setPadding(new Insets(40, 40, 40, 40));
	    layout.getChildren().addAll(r1,r2,r4,r5,r6);
	    setContent(layout);
	}
}
