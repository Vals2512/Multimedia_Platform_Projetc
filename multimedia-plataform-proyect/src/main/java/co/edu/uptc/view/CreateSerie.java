package co.edu.uptc.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import co.edu.uptc.controller.FileManagement;
import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Series;

public class CreateSerie {

    FileManagement fm = new FileManagement();
    List<Series> series = new ArrayList<>();

    @FXML
    private TextField titleTextField;

    @FXML
    private Spinner<Integer> seasonsSpinner;

    @FXML
    private Button addButton;

    @FXML
    public void initialize() {
        addButton.setOnAction(e -> handleAddSeries());
    }

    @FXML
    private void handleAddSeries() {
    String title = titleTextField.getText();
    int numSeasons = seasonsSpinner.getValue();

    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open Cover Image");

        // Configurar el filtro de extensión
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.jpeg, *.gif)", "*.png", "*.jpg", "*.jpeg", "*.gif");
    fileChooser.getExtensionFilters().add(extFilter);

    File file = fileChooser.showOpenDialog(addButton.getScene().getWindow());
    if (file != null) {
        String coverImage = file.getPath();

        Series sr = new Series(title, coverImage);

        for (int i = 1; i <= numSeasons; i++) {
            Season season = new Season();

            String seasonName = getInput("Enter the name of season " + i + ":");
            season.setName(seasonName);

            int numEpisodes = Integer.parseInt(getInput("How many episodes does season " + i + " have?"));

            for (int j = 1; j <= numEpisodes; j++) {
                String episodeName = getInput("Enter the name of episode " + j + ":");
                int episodeDuration = Integer.parseInt(getInput("Enter the duration of episode " + j + " (in minutes):"));

                Chapter chapter = new Chapter(episodeName, episodeDuration);
                season.addChapters(chapter);
            }

            sr.addSeason(season);
        }

        series.add(sr);
        fm.saveSerie(sr);

        // Pregunta al usuario si desea agregar otra serie
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add Another Series");
        alert.setHeaderText(null);
        alert.setContentText("Do you want to add another series?");

        ButtonType yesButton = new ButtonType("Yes");
        ButtonType noButton = new ButtonType("No");
        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesButton) {
            // El usuario seleccionó "Sí". Limpia los campos.
            titleTextField.clear();
            seasonsSpinner.getValueFactory().setValue(1);
        } else {
            // El usuario seleccionó "No". Cierra la escena actual.
            addButton.getScene().getWindow().hide();
        }
    } else {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No File Selected");
        alert.setHeaderText(null);
        alert.setContentText("No file selected. Please select a cover image.");

        alert.showAndWait();
    }


    }

    @FXML
    private void handleAddButtonClick() {
        handleAddSeries();
    }

    private String getInput(String prompt) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setContentText(prompt);
        return dialog.showAndWait().orElse("");
    }

}