//Micheal O Sullivan R00102982
package application.Views;

import javafx.stage.Stage;
import application.Models.*;
import application.Controllers.StudentQueries;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;

public class ExitWindow extends AddRemovePanel {
	
	public static void display() { 
			
		Stage window = new Stage();
		Button yesButton = new Button("Yes");
		Button noButton = new Button("No");
		Label label = new Label("Would you like to save students before exiting the program?");
		
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle("MTU Student Record System");
		window.setMinWidth(300);
		window.setHeight(200);
		
		yesButton.setOnAction(e -> {
			studentControl.saveStudentList();
			System.exit(0);  
		});
		
		noButton.setOnAction(e -> {
			 System.exit(0);   
		});
		
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, yesButton, noButton);
		layout.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(layout);
		window.setScene(scene);
		window.showAndWait();
		}
	}
