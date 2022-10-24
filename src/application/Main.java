//Micheal O Sullivan R00102982
package application;
import application.Views.*;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("MTU Student Record System");
		try {
			BorderPane mainPane = new BorderPane();
			Group root = new Group();
			Scene scene = new Scene(root,650,550);
			
		      TabPane tp = new TabPane();
		      tp.getTabs().add (new AddRemovePanel() );
		      tp.getTabs().add (new ModulePanel() );
		      tp.getTabs().add (new AddGradePanel() );
		      tp.getTabs().add (new StudentRecordsPanel() );
            		
			  mainPane.setCenter(tp);
			  
		      mainPane.prefHeightProperty().bind(scene.heightProperty());
		      mainPane.prefWidthProperty().bind(scene.widthProperty());
		      
		      root.getChildren().add(mainPane);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
