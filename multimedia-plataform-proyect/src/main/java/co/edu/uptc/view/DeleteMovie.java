package co.edu.uptc.view;

import co.edu.uptc.controller.MoviesControl;
import co.edu.uptc.model.Movies;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeleteMovie {
    @FXML
    private TableView<Movies> moviesTable;
    @FXML
    private TableColumn<Movies, String> titleColumn;
    @FXML
    private Button deleteButton;

    private MoviesControl mc = new MoviesControl();

    @FXML
    public void initialize() {
        // Configura la columna del título para que muestre el título de la película
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("tittle"));

        // Carga las películas en la tabla
        moviesTable.setItems(FXCollections.observableArrayList(mc.getMovies()));

        // Configura el botón de eliminar para eliminar la película seleccionada
        deleteButton.setOnAction(e -> deleteMovie());
    }

    @FXML
    public void deleteMovie() {
        Movies selectedMovie = moviesTable.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            mc.deleteMovie(selectedMovie.getTittle());
            moviesTable.getItems().remove(selectedMovie);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Delete Movie");
            alert.setHeaderText(null);
            alert.setContentText("The movie was deleted sucessfully.");
            alert.showAndWait();
        }
    }
}
