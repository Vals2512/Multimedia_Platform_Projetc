module multimedia.plataform.proyect {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires kernel;
    requires io;
    requires layout;


    opens co.edu.uptc.view to javafx.fxml;
    opens co.edu.uptc.controller to com.itextpdf.text;
    exports co.edu.uptc.view;
    exports co.edu.uptc.controller;
    opens co.edu.uptc.model to com.google.gson;
}