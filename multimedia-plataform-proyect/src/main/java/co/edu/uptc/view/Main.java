package co.edu.uptc.view;

import co.edu.uptc.controller.AdminControl;
import co.edu.uptc.controller.UserControl;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    private static UserControl uc;
    private static AdminControl ac;
    private static Stage primaryStage;
     static Scene sc;
    public static void main(String[] args){
        launch();
    }
   public static void setRoot(String fxml) throws IOException {

       Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource(fxml + ".fxml")));
       primaryStage.setScene(new Scene(root));
    }

    public static void setResizable(boolean b) {
        primaryStage.setResizable(b);
    }

    public static UserControl getUc() {
        return uc;
    }

    public static void setUc(UserControl uc) {
        Main.uc = uc;
    }

    public static AdminControl getAc() {
        return ac;
    }

    public static void setAc(AdminControl ac) {
        Main.ac = ac;
    }

    @Override
    public void start(Stage stage) throws Exception {
        uc=new UserControl();
        ac=new AdminControl();
        primaryStage = stage;
    primaryStage.setTitle("Login");
        setRoot("mainView");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}