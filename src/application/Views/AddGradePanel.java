package application.Views;

import javafx.collections.ObservableList;
import application.Controllers.*;
import application.Models.Student;
import application.Models.Module;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AddGradePanel extends Tab{
	private static Student studentSelected;
	private static Module moduleSelected;
	private static ListView<Module> moduleView;
	private static ListView<Student> studentView;
	private static ObservableList<Student> studentlistSelected;
	private static ObservableList<Module> modulelistSelected;
	private static TextField gradeField;
	protected static StudentQueries studentControl = new StudentQueries();
	protected static ModuleQueries moduleControl = new ModuleQueries();
	private static int enableButton3 = 0;
	
	public AddGradePanel() {
		    Label studentLabel, moduleLabel, studentSelectedLabel, moduleSelectedLabel, errorLabel, scoreLabel;
		    studentView = new ListView<>();
		    moduleView = new ListView<>();
		    gradeField = new TextField();
		    setText("Add Module Grade");
		    Button button1 = new Button("Choose Selected Student");
		    Button button2 = new Button("Choose Selected Module");
			Button button3 = new Button("Add Module Grade");
			Button loadModulesButton = new Button("Load Module List");
			Button loadStudentsButton = new Button("Load Student List");
			studentControl.loadStudentList();
			moduleControl.loadModuleList();
			
			studentLabel = new Label("Selected Student: ");	
		    moduleLabel = new Label("Selected Module: "); 
		    errorLabel = new Label("");
		    scoreLabel = new Label("Enter grade: ");
			studentSelectedLabel = new Label("No student has been selected");
			moduleSelectedLabel = new Label("No module has been selected");
			gradeField.setTextFormatter(null);
			for (int i = 0; i<studentControl.getSize(); i++)
			{
				studentView.getItems().add(studentControl.getStudentFromList(i));	
			}
            
			studentView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			button3.setDisable(true);
			
			button1.setOnAction(e -> {
				enableButton3++;
				studentlistSelected = studentView.getSelectionModel().getSelectedItems();
				studentSelected = studentlistSelected.get(0);
				studentSelectedLabel.setText(studentSelected.toString());
				if (enableButton3 > 1) {
					button3.setDisable(false);
				}
			});
			
			for (int i = 0; i<moduleControl.getSize(); i++)
			{
				moduleView.getItems().add(moduleControl.getModuleFromList(i));	
			}
            moduleView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			
			button2.setOnAction(e -> {
				enableButton3++;
				modulelistSelected = moduleView.getSelectionModel().getSelectedItems();
				moduleSelected = modulelistSelected.get(0);
				moduleSelectedLabel.setText(moduleSelected.toString());
				if (enableButton3 > 1) {
					button3.setDisable(false);
				}
			});	
			button3.setOnAction(e -> {
				String score = gradeField.getText();
				errorLabel.setText(studentControl.addGradeToStudent(studentSelected, moduleSelected, score));
				studentControl.saveStudentList();
			});	

			loadModulesButton.setOnAction(e -> {
				moduleView.getItems().clear();
				moduleControl.loadModuleList();
				for (int i = 0; i<moduleControl.getSize(); i++)
				{
					moduleView.getItems().add(moduleControl.getModuleFromList(i));	
				}
			});	
			loadStudentsButton.setOnAction(e -> {
				studentView.getItems().clear();
				studentControl.loadStudentList();
				for (int i = 0; i<studentControl.getSize(); i++)
				{
					studentView.getItems().add(studentControl.getStudentFromList(i));	
				}
			});	
			
			HBox r1 = new HBox(10);
		    r1.getChildren().addAll(studentLabel, studentSelectedLabel);
		    
		    HBox r2 = new HBox(10);
		    r2.getChildren().addAll(moduleLabel, moduleSelectedLabel);
		    
		    HBox r3 = new HBox(10);
		    r3.getChildren().addAll(scoreLabel, gradeField, button3, errorLabel);
		   
		    HBox r4 = new HBox(10);
		    r4.getChildren().addAll(loadStudentsButton, button1);
		    
		    HBox r5 = new HBox(10);
		    r5.getChildren().addAll(loadModulesButton, button2);
			setClosable(false);
			VBox layout = new VBox(10);
			layout.setPadding(new Insets(20, 20, 20, 20));
			layout.getChildren().addAll(studentView, r4, moduleView, r5, r1, r2, r3);
			//layout.setAlignment(Pos.CENTER);
			setContent(layout);			
	}
}
