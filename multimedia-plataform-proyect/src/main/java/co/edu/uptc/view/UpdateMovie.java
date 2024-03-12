package co.edu.uptc.view;

import java.util.List;
import java.util.Optional;

import co.edu.uptc.controller.MoviesControl;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movies;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;

public class UpdateMovie {
    @FXML
    private TableView<Movies> moviesTable;
    @FXML
    private TableColumn<Movies, String> titleColumn;
    @FXML
    private Button updateButton;

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
    public void initialize() {
        // Configura la columna del título para que muestre el título de la película
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("tittle"));

        // Carga las películas en la tabla
        moviesTable.setItems(FXCollections.observableArrayList(mc.getMovies()));

        
        // Configura el botón de eliminar para eliminar la película seleccionada
        updateButton.setOnAction(e -> updateMovie());
    }

    @FXML
    public void updateMovie() {
        // Get the selected movie
        Movies selectedMovie = moviesTable.getSelectionModel().getSelectedItem();


        // If no movie was selected, display an error message and end the method
        if (selectedMovie == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No movie selected");
            alert.setContentText("Please select a movie from the table.");
            alert.showAndWait();
            return;
        }

        // Save the original title of the movie
        String originalTitle = selectedMovie.getTittle();

        // Create a TextInputDialog for each field you want to update
        TextInputDialog titleDialog = new TextInputDialog(selectedMovie.getTittle());
        titleDialog.setTitle("Update movie");
        titleDialog.setHeaderText("Enter the new title of the movie");

        // Show the dialog and wait for the user to enter the new title
        Optional<String> newTitle = titleDialog.showAndWait();

        // If the user entered a new title, update the movie's title
        newTitle.ifPresent(title -> selectedMovie.setTittle(title));

        // Here's how to do it for categories
        ChoiceDialog<Category> categoriesDialog = new ChoiceDialog<>(selectedMovie.getCategories().get(0), categoriesList);
        categoriesDialog.setTitle("Update movie");
        categoriesDialog.setHeaderText("Select the new category of the movie");

        // Show the dialog and wait for the user to select the new category
        Optional<Category> newCategory = categoriesDialog.showAndWait();

        // If the user selected a new category, update the movie's category
        newCategory.ifPresent(category -> selectedMovie.setCategories(List.of(category)));

        TextInputDialog detailsDialog = new TextInputDialog(selectedMovie.getDetails());
        detailsDialog.setTitle("Update movie");
        detailsDialog.setHeaderText("Enter the new details of the movie");

        Optional<String> newDetails = detailsDialog.showAndWait();
        newDetails.ifPresent(details -> selectedMovie.setDetails(details));

        TextInputDialog releaseYearDialog = new TextInputDialog(String.valueOf(selectedMovie.getReleaseYear()));
        releaseYearDialog.setTitle("Update movie");
        releaseYearDialog.setHeaderText("Enter the new release year of the movie");

        Optional<String> newReleaseYearString = releaseYearDialog.showAndWait();
        if (newReleaseYearString.isPresent()) {
            try {
                int newReleaseYear = Integer.parseInt(newReleaseYearString.get());
                selectedMovie.setReleaseYear(newReleaseYear);
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid input");
                alert.setContentText("The release  year must be a number.");
                alert.showAndWait();
            }
        }

        // Here I will assume that you have a setDuration method in your Movies class and that you have a TextInputDialog to get the new duration
        TextInputDialog durationDialog = new TextInputDialog(String.valueOf(selectedMovie.getDuration()));
        durationDialog.setTitle("Update movie");
        durationDialog.setHeaderText("Enter the new duration of the movie");

        Optional<String> newDurationString = durationDialog.showAndWait();
        if (newDurationString.isPresent()) {
            try {
                int newDuration = Integer.parseInt(newDurationString.get());
                selectedMovie.setDuration(newDuration);
            } catch (NumberFormatException e) {
                // Handle the exception in case the user does not enter a valid number
            }
        }

        // Finally, update the movie in your controller
        mc.updateMovie(originalTitle, selectedMovie);
    }
}

