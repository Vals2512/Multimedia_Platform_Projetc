package co.edu.uptc.view;

import co.edu.uptc.controller.FileManagement;
import co.edu.uptc.controller.UserControl;
import co.edu.uptc.model.Payment;
import co.edu.uptc.model.Plan;
import co.edu.uptc.util.FileManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentView implements Initializable {
    @FXML
    ComboBox<Payment> payMethod;
    private Plan selectedPlan;
    private Payment method;
    private String email,password;
    List<Payment> payMethods=new ArrayList<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @FXML
    ComboBox<Plan>comboBoxPlans;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        email=Main.getUc().getEmail();
        password=Main.getUc().getPassword();
        List<Payment> payMethods=new ArrayList<>();
        payMethods.add(new Payment("paypal"));
        payMethods.add(new Payment("debit card"));
        payMethods.add(new Payment("credit card"));
        List<Plan> plans=new ArrayList<>();
        plans.add(new Plan("Basic", 7.99, 30));
        plans.add(new Plan("Standard", 9.99, 30));
        plans.add(new Plan("Premium", 11.99, 30));
        comboBoxPlans.setItems(FXCollections.observableList(plans));
        comboBoxPlans.setOnAction(e->{
                selectedPlan=comboBoxPlans.getSelectionModel().getSelectedItem();
                  
                    for (Payment payment : payMethods) {
                payment.setMonto(selectedPlan.getPrice());
                }
                }
        );


        payMethod.setItems(FXCollections.observableList(payMethods));
        payMethod.setOnAction(e->{
            method=payMethod.getSelectionModel().getSelectedItem();
           
        });
    }

    public void backToSignIn() throws IOException {
        Main.setRoot("signInView");
    }

    public void send() throws IOException {

        Main.getUc().addUser(email,password,selectedPlan,method);
        Main.setRoot("mainView");
    }
}
