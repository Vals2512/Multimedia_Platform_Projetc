package co.edu.uptc.view;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.controller.FileManagement;
import co.edu.uptc.model.Movies;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Series;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.lang.reflect.Type;

import javafx.scene.image.Image; 
import javafx.scene.image.ImageView; 

public class VisitView {
    private static final String FILE_PATH = "src\\main\\java\\co\\edu\\uptc\\persistence\\";
    private static final String FILE_EXTENSION = ".json";

    @FXML
    private VBox vBox;
    @FXML
    private Button backToLogin;
    @FXML
    private ListView<Series> listView;
    @FXML
    private ListView<Movies> moviesListView;
    @FXML
    private TabPane tabPane;
    @FXML
    private Tab seriesTab;
    @FXML
    private Tab moviesTab;

    private FileManagement fm = new FileManagement();
    private String fileName = "Series";
    private String fileName1 = "movies";
    private Type SERIES_TYPE = new TypeToken<List<Series>>() {}.getType();
    private Type MOVIES_TYPE = new TypeToken<List<Movies>>() {}.getType();
    private String fileNamee1 = FILE_PATH + fileName + FILE_EXTENSION;
    private String fileNamee2 = FILE_PATH + fileName1 + FILE_EXTENSION;

    @FXML
    private void showSeries(ActionEvent event) {
        tabPane.getSelectionModel().select(seriesTab);
    }

    
    public void initialize() {
        listView.setCellFactory(param -> new SeriesListCell());
        moviesListView.setCellFactory(param -> new MoviesListCell());
        if (this.seriesTab == null) {
            this.seriesTab = new Tab();
        }
        this.seriesTab.setContent(listView);
        if (this.moviesTab == null) {
            this.moviesTab = new Tab();
        }
        moviesTab.setContent(moviesListView);
        loadSeries();

    }

    @FXML
    private void loadSeries() {
        File checkFile = new File(fileNamee1);
        if (checkFile.exists()) {
            List<Series> seriesFromFile = fm.readJsonFile(fileNamee1, SERIES_TYPE);
            listView.getItems().clear();
            for (Series serie : seriesFromFile) {
                listView.getItems().add(serie);
            }
            listView.setCellFactory(param -> new ListCell<Series>() {
                private ImageView imageView = new ImageView();
                private final Label label = new Label();
                private final VBox vbox = new VBox();
                private final Button button = new Button();
                {
                    vbox.getChildren().addAll(label, button);
                    vbox.setAlignment(Pos.CENTER);
                    button.setGraphic(imageView); 
                    imageView.setFitWidth(179); // Establece el ancho de la imagen
                    imageView.setFitHeight(211); // Establece la altura de la imagen
                    imageView.setPreserveRatio(false); // Mantiene la relación de aspecto de la imagen
                }
                @Override
                protected void updateItem(Series item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        String imagePath = item.getImagePath();
                        if (imagePath != null && !imagePath.isEmpty()) {
                            File file = new File(imagePath);
                            imageView.setImage(new Image(file.toURI().toString()));
                            label.setText(item.getName());
                            setGraphic(vbox);
                        } else {
                            label.setText(item.getName());
                            setGraphic(vbox);
                        }

                        // Agrega el evento de click
                        setOnMouseClicked(event -> {
                            if (event.getClickCount() == 2) { // Doble click
                                // Muestra la información completa de la serie
                                int totalEpisodes = 0;
                                for (Season season : item.getSeasons()) {
                                    totalEpisodes += season.getNumberOfEpisodes();
                                }
                                Alert alert = new Alert(AlertType.INFORMATION);
                                alert.setTitle("Serie details");
                                alert.setHeaderText(null);
                                alert.setContentText("Tittle: " + item.getName() + "\n Seasons: " + item.getSeasons().size() + "\n" + //
                                                                        " Total Episodes: " + totalEpisodes);
                                alert.showAndWait();
                            }
                        });
                    }
                }
            });
            listView.setVisible(true);
        } else {
            listView.getItems().clear();
            Series serie = new Series("No hay series disponibles.", "/src/images/718672.png");
            listView.getItems().add(serie);
            listView.setCellFactory(param -> new ListCell<Series>() {
                private ImageView imageView = new ImageView();
                @Override
                public void updateItem(Series serie, boolean empty) {
                    super.updateItem(serie, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        File file = new File(serie.getImagePath());
                        imageView.setImage(new Image(file.toURI().toString()));
                        imageView.setFitHeight(50); // ajusta el tamaño de la imagen
                        imageView.setFitWidth(50);
                        setText(serie.getName());
                        setGraphic(imageView);
                    }
                }
            });
            listView.setVisible(true);
            tabPane.getSelectionModel().select(seriesTab);

            
        }
    }

    @FXML
    private void loadMovies() {
        File checkFile = new File(fileNamee2);
        if (checkFile.exists()) {
            List<Movies> moviesFromFile = fm.readJsonFile(fileNamee2, MOVIES_TYPE);
            moviesListView.getItems().clear();
            for (Movies movie : moviesFromFile) {
                moviesListView.getItems().add(movie);
            }
            moviesListView.setCellFactory(param -> new ListCell<Movies>() {
                private ImageView imageView = new ImageView();
                private final Label label = new Label();
                private final VBox vbox = new VBox();
                private final Button button = new Button();
                {
                    vbox.getChildren().addAll(label, button);
                    vbox.setAlignment(Pos.CENTER);
                    button.setGraphic(imageView); 
                    imageView.setFitWidth(179); // Establece el ancho de la imagen
                    imageView.setFitHeight(211); // Establece la altura de la imagen
                    imageView.setPreserveRatio(false); // Mantiene la relación de aspecto de la imagen
                }
                @Override
                protected void updateItem(Movies item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        String imagePath = item.getImagePath();
                        if (imagePath != null && !imagePath.isEmpty()) {
                            File file = new File(imagePath);
                            imageView.setImage(new Image(file.toURI().toString()));
                            label.setText(item.getTittle());
                            setGraphic(vbox);
                        } else {
                            label.setText(item.getTittle());
                            setGraphic(vbox);
                        }
                    }
                }
            });
            moviesListView.setVisible(true);
        } else {
            moviesListView.getItems().clear();
            Movies movie = new Movies();
            moviesListView.getItems().add(movie);
            moviesListView.setCellFactory(param -> new ListCell<Movies>() {
                private ImageView imageView = new ImageView();
                @Override
                public void updateItem(Movies movie, boolean empty) {
                    super.updateItem(movie, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        File file = new File(movie.getImagePath());
                        imageView.setImage(new Image(file.toURI().toString()));
                        imageView.setFitHeight(50); // ajusta el tamaño de la imagen
                        imageView.setFitWidth(50);
                        setText(movie.getTittle());
                        setGraphic(imageView);
                    }
                }
            });
            moviesListView.setVisible(true);
            tabPane.getSelectionModel().select(moviesTab);

        }
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