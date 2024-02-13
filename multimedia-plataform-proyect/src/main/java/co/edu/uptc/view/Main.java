package co.edu.uptc.view;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
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
//        FXMLLoader loader= new FXMLLoader(getClass().getResource("main.fxml"));
//        Group root=loader.load();
        Group root=new Group();
        MainView sc=new MainView(root,300,400);
        stage.setScene(sc);
        stage.setTitle("prueba");
        stage.show();
    }
}