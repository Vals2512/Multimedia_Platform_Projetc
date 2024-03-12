package co.edu.uptc.view;

import co.edu.uptc.controller.AdminControl;
import co.edu.uptc.controller.FileManagement;
import co.edu.uptc.controller.UserControl;
import co.edu.uptc.view.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.io.IOException;

public class MainView{
    @FXML
     ToggleButton passwordB;
    @FXML
    TextField emailField;
    @FXML
    TextField passwordField;
    @FXML
    Button buttonLogin;
    @FXML
    Button buttonVisitor;
    @FXML
    Button buttonSignIn;
    @FXML
    Label errorLabel;


    public MainView() {

        errorLabel=new Label();
//        errorLabel.setStyle("--block-text-color:#FF0000 ");

    }
    public void initialize() {
        errorLabel.setWrapText(true);
    }

    @FXML
    public void testButton(){
        
    }

    
@FXML
    public void signInButton() throws IOException {
        Main.setRoot("singInView");

    }
    @FXML
    private void loginButton() throws IOException {


        if (Main.getUc().login(emailField.getText(),passwordField.getText())){

        }else if (Main.getAc().loginAdminBackup(emailField.getText(),passwordField.getText())){
            Main.setRoot("adminView");
            errorLabel.setText("");

        }else{
            errorLabel.setStyle("-fx-text-fill: RED");
//            errorLabel.setTextFill(Color.web("#FF0000"));
            errorLabel.setText("Revise las credenciales de acceso ya que puede estar mal escrito el correo o la contraseña");

        }

    }

    public void seePassword() {
    }

    public void passwordFieldKeyTyped() {
    }
}
