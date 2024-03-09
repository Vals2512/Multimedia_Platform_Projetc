package co.edu.uptc.view;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.controller.FileManagement;
import co.edu.uptc.model.Movies;
import co.edu.uptc.model.Series;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.lang.reflect.Type;

public class VisitView {
    private static final String FILE_PATH = "src\\main\\java\\co\\edu\\uptc\\persistence\\";
    private static final String FILE_EXTENSION = ".json";

    @FXML
    private VBox vBox;
    @FXML
    private MenuItem verSeries;
    @FXML
    private Button backToLogin;
    @FXML
    private ListView<Series> listView;
    @FXML
    private ListView<Movies> moviesListView;
    @FXML
    private MenuItem verMovies;
    @FXML
    private SplitPane splitPane;

    private FileManagement fm = new FileManagement();
    private String fileName = "Series";
    private String fileName1 = "movies";
    private Type SERIES_TYPE = new TypeToken<List<Series>>() {}.getType();
    private Type MOVIES_TYPE = new TypeToken<List<Movies>>() {}.getType();
    private String fileNamee1 = FILE_PATH + fileName + FILE_EXTENSION;
    private String fileNamee2 = FILE_PATH + fileName1 + FILE_EXTENSION;

    @FXML
    private void showSeries(ActionEvent event) {
        listView.setVisible(true);
    }

    public void initialize() {
        setupMenu();
        listView.setCellFactory(param -> new SeriesListCell());
        moviesListView.setCellFactory(param -> new MoviesListCell());
        splitPane.getItems().addAll(listView, moviesListView);

    }

    private void setupMenu() {
        Menu menu = new Menu("Options ");

        verSeries.setOnAction(e -> loadSeries());
        verMovies.setOnAction(e -> loadMovies());

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(menuBar);
    }

    @FXML
    private void loadSeries() {
        File checkFile = new File(fileNamee1);
        if (checkFile.exists()) {
            try {
                List<Series> seriesFromFile = fm.readJsonFile(fileNamee1, SERIES_TYPE);
                ObservableList<Series> seriesObservableList = FXCollections.observableArrayList(seriesFromFile);
                listView.setItems(seriesObservableList);
            } catch (Exception ex) {
                handleException(ex);
            }
        } else {
            listView.getItems().clear();
            Series serie = new Series("No hay series disponibles.");
            listView.getItems().add(serie);
        }
    }

    @FXML
    private void loadMovies() {
        File checkFile = new File(fileNamee2);
        if (checkFile.exists()) {
            try {
                List<Movies> moviesFromFile = fm.readJsonFile(fileNamee2, MOVIES_TYPE);
                ObservableList<Movies> moviesObservableList = FXCollections.observableArrayList(moviesFromFile);
                moviesListView.setItems(moviesObservableList);
            } catch (Exception ex) {
                handleException(ex);
            }
        } else {
            moviesListView.getItems().clear();
            Movies movie = new Movies();
            moviesListView.getItems().add(movie);
        }
    }

    private void handleException(Exception ex) {
        // Manejar la excepción de manera adecuada
        ex.printStackTrace();
    }

    @FXML
    public void backToMenu() throws IOException {
        Main.setResizable(false);
        Main.setRoot("mainView");
    }

    class SeriesListCell extends ListCell<Series> {
        @Override
        protected void updateItem(Series series, boolean empty) {
            super.updateItem(series, empty);
            if (series == null || empty) {
                setGraphic(null);
            } else {
                HBox hBox = new HBox(10);
                Label nameLabel = new Label(series.getName());
                hBox.getChildren().addAll(nameLabel);
                setGraphic(hBox);
            }
        }
    }

    class MoviesListCell extends ListCell<Movies> {
        @Override
        protected void updateItem(Movies movie, boolean empty) {
            super.updateItem(movie, empty);
            if (movie == null || empty) {
                setGraphic(null);
            } else {
                HBox hBox = new HBox(10);
                Label nameLabel = new Label(movie.getTittle());
                hBox.getChildren().addAll(nameLabel);
                setGraphic(hBox);
            }
        }
    }
}