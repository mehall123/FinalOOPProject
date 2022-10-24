package application.Views;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import application.Controllers.ModuleQueries;
import application.Controllers.StudentQueries;
import application.Models.Grade;
import application.Models.Student;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StudentRecordsPanel extends Tab{
	private static ListView<Grade> gradeView;
	private static Student studentSelected;
	private static ListView<Student> studentView;
	private static ObservableList<Student> studentlistSelected;
	private static ObservableList<Grade> gradelistSelected;
	protected static StudentQueries studentControl = new StudentQueries();
	protected static ModuleQueries moduleControl = new ModuleQueries();
	
	public StudentRecordsPanel() {
		    studentView = new ListView<>();
		    gradeView = new ListView<>();
		    setText("View Student Records");
		    Button button1 = new Button("View Selected Student Grades");
		    Button loadButton1 = new Button("Load Student List");
		    Button orderName = new Button("Order by Module Name");
		    Button orderScore = new Button("Order by Grade");
			studentControl.loadStudentList();
			
			for (int i = 0; i<studentControl.getSize(); i++)
			{
				studentView.getItems().add(studentControl.getStudentFromList(i));	
			}
            
			studentView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
			
			button1.setOnAction(e -> {
				gradeView.getItems().clear();
				studentlistSelected = studentView.getSelectionModel().getSelectedItems();
				studentSelected = studentlistSelected.get(0);
				int i = 0;
			    while(i < studentSelected.getList().size()) {
			    gradeView.getItems().add(studentSelected.getList().get(i));
				i++;
				}
			});
			loadButton1.setOnAction(e -> {
				studentView.getItems().clear();
				studentControl.loadStudentList();
				for (int i = 0; i<studentControl.getSize(); i++)
				{
					studentView.getItems().add(studentControl.getStudentFromList(i));	
				}
			});
		    
			orderName.setOnAction(e -> {
				 Collections.sort(gradeView.getItems(), Comparator.comparing(Grade::getName).thenComparingInt(Grade::getScore));
			});
			
            orderScore.setOnAction(e -> {
            	Collections.sort(gradeView.getItems(), Comparator.comparingInt(Grade::getScore).reversed().thenComparing(Grade::getName));
			});
            
			HBox r1 = new HBox(10);
		    r1.getChildren().addAll(loadButton1, button1);
		    
		    HBox r2 = new HBox(10);
		    r2.getChildren().addAll(orderName, orderScore);
		    
			setClosable(false);
			VBox layout = new VBox(10);
			layout.setPadding(new Insets(20, 20, 20, 20));
			layout.getChildren().addAll(studentView, r1, gradeView, r2);
			//layout.setAlignment(Pos.CENTER);
			setContent(layout);			
	}
}
