package co.edu.uptc.view;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.controller.FileManagement;
import co.edu.uptc.model.Series;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AdminView {

    private Type SERIES_TYPE = new TypeToken<List<Series>>() {}.getType();
    private ObservableList<Series> series;
    private FileManagement fm = new FileManagement();
    private String fileName = "Series";
    private String filePath = "src\\co\\edu\\uptc\\persistence\\";
    private String fileExtension = ".json";
    private String fileNamee1 = filePath + fileName + fileExtension;
    
    @FXML
    Button createMovieButton;

    @FXML
    Button updateMovieButton;
    @FXML
    Button deleteMovieButton;
    @FXML
    private TextField nameField;

    @FXML
    private Button agregarSerieButton;

    @FXML
    private ListView<Series> listView; // Asegúrate de que este ListView es el correcto

    @FXML
    private void createMovie() throws IOException{
        // Carga el archivo FXML para el formulario
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createMovie.fxml"));        
        // Crea una nueva escena con el formulario
        Scene scene = new Scene(loader.load());
        
        // Crea una nueva ventana (Stage) para mostrar el formulario
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();    }


    @FXML
    private void updateMovie() {
        // Implementa la lógica para actualizar una película
    }

    @FXML
    private void deleteMovie() {
        // Implementa la lógica para eliminar una película
    }

    @FXML
    private void createSerie() throws IOException {
        // Carga el archivo FXML para el formulario
        FXMLLoader loader = new FXMLLoader(getClass().getResource("createSerie.fxml"));        
        // Crea una nueva escena con el formulario
        Scene scene = new Scene(loader.load());
        
        // Crea una nueva ventana (Stage) para mostrar el formulario
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void updateSerie() {
        // Implementa la lógica para actualizar una película
    }

    @FXML
    private void deleteSerie() {
        // Implementa la lógica para eliminar una película
    }

    @FXML
    public void backToMenu() throws IOException {
        Main.setResizable(false);
        Main.setRoot("mainView");
    }
}