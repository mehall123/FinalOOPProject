package application.Views;

import application.Controllers.StudentQueries;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddRemovePanel extends Tab {
	private TextArea studentDetails;
	protected static StudentQueries studentControl = new StudentQueries();
	
	public AddRemovePanel(){
		
	Label fNameLabel, mNameLabel, lNameLabel, dobLabel, idLabel;
	TextField fNameField, mNameField, lNameField, idField;
	Button addButton, removeButton,listButton, loadButton, saveButton, exitButton;
	DatePicker datePicker;
	
	setText("Add/Remove Student");
    fNameLabel = new Label("First Name: ");	
    mNameLabel = new Label("Middle Name: ");	
    lNameLabel = new Label("Last Name: ");	
    idLabel = new Label("Student Number: ");
    dobLabel = new Label("Date of Birth: ");
    
    fNameField = new TextField();
    mNameField = new TextField();
    lNameField = new TextField();
    idField = new TextField();
    datePicker = new DatePicker();
    
    addButton = new Button ("Add");
    addButton.setOnAction(e ->  
    studentDetails.setText(studentControl.addStudentToList(fNameField.getText(), mNameField.getText(), lNameField.getText(), idField.getText(), datePicker.getValue())));
    
    removeButton = new Button("Remove");
    removeButton.setOnAction(e ->  
    studentDetails.setText(studentControl.removeStudentFromList(fNameField.getText(), mNameField.getText(), lNameField.getText(), idField.getText(), datePicker.getValue())));
    
    listButton = new Button("List");
    listButton.setOnAction(new EventHandler<ActionEvent>() {
		 @Override
		 public void handle(ActionEvent event) {
			 	String allStudent = studentControl.getListStudent();
			 	studentDetails.setText(allStudent);
		 	}
		 });	
    loadButton = new Button ("Load");
    loadButton.setOnAction(new EventHandler<ActionEvent>() {
		 @Override
		 public void handle(ActionEvent event) {
			 	String loadStudent = studentControl.loadStudentList();
			 	studentDetails.setText(loadStudent);
		 	}
		 }); 
    saveButton = new Button("Save");
    saveButton.setOnAction(e -> studentControl.saveStudentList());
    exitButton = new Button("Exit");
    exitButton.setOnAction(e ->  ExitWindow.display());
    
    studentDetails = new TextArea(studentControl.loadStudentList());
       
    HBox r1 = new HBox(10);
    r1.getChildren().addAll(fNameLabel, fNameField);
    
    HBox r2 = new HBox(10);
    r2.getChildren().addAll(idLabel, idField);
    
    HBox r3 = new HBox(10);
    r3.getChildren().addAll(dobLabel, datePicker);  
    
    HBox r4 = new HBox(20);
    r4.getChildren().addAll(addButton,removeButton,listButton);
    
    HBox r5 = new HBox(10);
    r5.getChildren().addAll(studentDetails);
    
    HBox r6 = new HBox(20);
    r6.getChildren().addAll(loadButton, saveButton, exitButton);
    
    HBox middleN = new HBox(10);
    middleN.getChildren().addAll(mNameLabel, mNameField);
    
    HBox lastN = new HBox(10);
    lastN.getChildren().addAll(lNameLabel, lNameField);
    
    studentDetails.setEditable(false);
    setClosable(false);
    VBox layout = new VBox(10);
    layout.setPadding(new Insets(40, 40, 40, 40));
    layout.getChildren().addAll(r1,middleN,lastN,r2,r3,r4,r5,r6);
    setContent(layout);
}
}