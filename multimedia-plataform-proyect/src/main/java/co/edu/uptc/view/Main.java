package co.edu.uptc.view;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;
     static Scene sc;
    public static void main(String[] args){
        launch();
    }
   public static void setRoot(String fxml) throws IOException {

       Parent root = FXMLLoader.load(Main.class.getResource(fxml+".fxml"));
       primaryStage.setScene(new Scene(root));
    }


    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        primaryStage.setTitle("Login");
        setRoot("mainView");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}