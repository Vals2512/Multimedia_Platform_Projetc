module multimedia.plataform.proyect {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    opens co.edu.uptc.view to javafx.fxml;
    exports co.edu.uptc.view;
    exports co.edu.uptc.controller;

}