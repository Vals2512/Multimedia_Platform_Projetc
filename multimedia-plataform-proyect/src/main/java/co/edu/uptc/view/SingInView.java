package co.edu.uptc.view;


import co.edu.uptc.controller.UserControl;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SingInView implements Initializable {
    UserControl uc=new UserControl();
    @FXML
    Label showPassword2;
    @FXML
    Label showPassword1;
    @FXML
    Button sendContent;
    @FXML
    Label errorLabel;
    @FXML
    TextField emailField;
    @FXML
    PasswordField passwordF1;
    @FXML
    PasswordField passwordF2;
    @FXML
    ToggleButton password1;
    @FXML
    ToggleButton password2;
    @FXML
    Button backButton;
    @FXML
    public void seeFirstPassword(){
        if (password1.isSelected()) {
            showPassword1.setVisible(true);
            showPassword1.textProperty().bind(Bindings.concat(passwordF1.getText()));

            password1.setText("Hide");
        } else {
            showPassword1.setVisible(false);
            password1.setText("Show");
        }
    }
    @FXML
    public void seeSecondPassword(){
        if (password2.isSelected()) {
            showPassword2.setVisible(true);
            showPassword2.textProperty().bind(Bindings.concat(passwordF2.getText()));

            password2.setText("Hide");
        } else {
            showPassword2.setVisible(false);
            password2.setText("Show");
        }
    }
    @FXML
    void passwordFieldKeyTyped2(KeyEvent event) {
        showPassword2.textProperty().bind(Bindings.concat(passwordF2.getText()));
    }
    @FXML
    void passwordFieldKeyTyped1(KeyEvent event) {
        showPassword1.textProperty().bind(Bindings.concat(passwordF1.getText()));
    }

    public void send() {
        if (passwordF1.getText().equals(passwordF2.getText())){
        if (passwordF1.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")){
            System.out.println(uc.addUser(emailField.getText(),passwordF1.getText()));

        }else {
            errorLabel.setStyle("-fx-text-fill: Red");
            errorLabel.setText("su contraseña no cumple con tener uno de estos requisitos: \n mas de 8 caracteres\n tener una mayuscula \n una minuscula \n un caracter númerico ");
            errorLabel.setWrapText(true);
        }
        }else {
            errorLabel.setStyle("-fx-text-fill: Red");
            errorLabel.setText("Las contraseñas no coinciden");
            errorLabel.setWrapText(true);
        }
    }

    public void backToLogin() throws IOException {
        Main.setRoot("mainView");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showPassword1.setVisible(false);
        showPassword2.setVisible(false);
    }
}
