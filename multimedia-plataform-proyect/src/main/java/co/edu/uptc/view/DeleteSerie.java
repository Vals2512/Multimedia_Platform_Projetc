package co.edu.uptc.view;

import co.edu.uptc.controller.FileManagement;

import co.edu.uptc.model.Series;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeleteSerie {
    @FXML
    private TableView<Series> seriesTable;
    @FXML
    private TableColumn<Series, String> titleColumn;
    @FXML
    private Button deleteButton;
    
    private FileManagement fm = new FileManagement();

    @FXML
    public void initialize() {
        // Configura la columna del título para que muestre el título de la película
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Carga las películas en la tabla
        seriesTable.setItems(FXCollections.observableArrayList(fm.getSeries()));

        // Configura el botón de eliminar para eliminar la película seleccionada
        deleteButton.setOnAction(e -> deleteSerie());
    }

    @FXML
    public void deleteSerie() {
        Series selectedSerie = seriesTable.getSelectionModel().getSelectedItem();
        if (selectedSerie != null) {
            fm.deleteSeries(selectedSerie.getName());
            seriesTable.getItems().remove(selectedSerie);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Delete Serie");
            alert.setHeaderText(null);
            alert.setContentText("The serie was deleted sucessfully.");
            alert.showAndWait();
        }
    }
}
