package co.edu.uptc.view;

import java.io.IOException;

import co.edu.uptc.model.Series;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AdminView {


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
        stage.show(); 
    }


    @FXML
    private void updateMovie() throws IOException{
               // Carga el archivo FXML para el formulario
               FXMLLoader loader = new FXMLLoader(getClass().getResource("updateMovie.fxml"));        
               // Crea una nueva escena con el formulario
               Scene scene = new Scene(loader.load());
               // Crea una nueva ventana (Stage) para mostrar el formulario
               Stage stage = new Stage();
               stage.setScene(scene);
               stage.show(); 
    }

    @FXML
    private void deleteMovie() throws IOException{
               // Carga el archivo FXML para el formulario
               FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteMovie.fxml"));        
               // Crea una nueva escena con el formulario
               Scene scene = new Scene(loader.load());
               // Crea una nueva ventana (Stage) para mostrar el formulario
               Stage stage = new Stage();
               stage.setScene(scene);
               stage.show(); 
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
    private void updateSerie() throws IOException{
           //Arreglar
    }

    @FXML
    private void deleteSerie() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteSerie.fxml"));        
        // Crea una nueva escena con el formulario
        Scene scene = new Scene(loader.load());
        
        // Crea una nueva ventana (Stage) para mostrar el formulario
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void backToMenu() throws IOException {
        Main.setResizable(false);
        Main.setRoot("mainView");
    }
}