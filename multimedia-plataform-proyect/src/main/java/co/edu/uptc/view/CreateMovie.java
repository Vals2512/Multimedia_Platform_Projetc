package co.edu.uptc.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import co.edu.uptc.controller.MoviesControl;
import co.edu.uptc.model.Category;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreateMovie {
    private MoviesControl mc = new MoviesControl();
    Category drama = new Category("Drama", "Películas o series de género dramático");
    Category action = new Category("Action", "Películas o series de acción");
    Category fiction = new Category("Fiction", "Películas o series de ficción");
    Category fantasy = new Category("Fantasy", "Películas o series de fantasía");
    Category thriller = new Category("Thriller", "Películas o series de suspenso");
    Category romance = new Category("Romance", "Películas o series de temática romántica");
    Category crime = new Category("Crimen", "Películas o series de crimen");
    
    List<Category> categoriesList = List.of(drama, action, fiction, fantasy, thriller, romance, crime);
    @FXML
    private TextField titleTextField;
    @FXML
    private TextField categoriesTextField;
    @FXML
    private TextField detailsTextField;
    @FXML
    private TextField releaseYearTextField;
    @FXML
    private TextField durationTextField;
    @FXML
    private Button addButton;

    @FXML
    public void initialize(){
        // Create UI components
        addButton.setOnAction(e -> handleAddMovie());
    }

    @FXML
    public void handleAddMovie() {
        try {
            String title = titleTextField.getText();
            String selectedCategories = categoriesTextField.getText();
            String details = detailsTextField.getText();
            String releaseYearStr = releaseYearTextField.getText();
            String durationStr = durationTextField.getText();

            int releaseYear = Integer.parseInt(releaseYearStr);
            int duration = Integer.parseInt(durationStr);
            

            String[] categoryIndices = selectedCategories.split(",");
            List<Category> selectedCategoryList = new ArrayList<>();

            for (String index : categoryIndices) {
                try {
                    int categoryIndex = Integer.parseInt(index.trim()) - 1;
                    if (categoryIndex >= 0 && categoryIndex < categoriesList.size()) {
                        selectedCategoryList.add(categoriesList.get(categoryIndex));
                    } else {
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Error: Invalid category index");
                        alert.showAndWait();
                        return;
                    }
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error: Invalid category index");
                    alert.showAndWait();
                    return;
                }
            }

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Cover Image");

            // Configurar el filtro de extensión
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files (*.png, *.jpg, *.jpeg, *.gif)", "*.png", "*.jpg", "*.jpeg", "*.gif");
            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showOpenDialog(addButton.getScene().getWindow());
            if (file != null) {
                String coverImage = file.getPath();

                if (mc.addMovie(title, selectedCategoryList, details, releaseYear, duration, coverImage, categoriesList)) {

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Add Another Movie");
                    alert.setHeaderText(null);
                    alert.setContentText("Do you want to add another movie?");

                    ButtonType yesButton = new ButtonType("Yes");
                    ButtonType noButton = new ButtonType("No");
                    alert.getButtonTypes().setAll(yesButton, noButton);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == yesButton) {
                        // El usuario seleccionó "Sí". Limpia los campos.
                          titleTextField.clear();
                            categoriesTextField.clear();
                            detailsTextField.clear();
                            releaseYearTextField.clear();
                            durationTextField.clear();
                    } else {
                        // El usuario seleccionó "No". Cierra la escena actual.
                        addButton.getScene().getWindow().hide();
                    }
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error adding movie");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No File Selected");
                alert.setHeaderText(null);
                alert.setContentText("No file selected. Please select a cover image.");

                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error: Please input valid numerical values for release year and duration.");
            alert.showAndWait();
        }
    }
}