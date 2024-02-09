package co.edu.uptc;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label label=new Label("esta perra mamada que");
        StackPane sp=new StackPane();
        sp.getChildren().add(label);
        Scene sc=new Scene(sp,300,400);
        stage.setScene(sc);
        stage.setTitle("prueba");
        stage.show();
    }
}