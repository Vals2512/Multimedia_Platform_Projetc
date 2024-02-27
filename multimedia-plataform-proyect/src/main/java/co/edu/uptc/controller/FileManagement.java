package co.edu.uptc.controller;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Series;
import co.edu.uptc.util.FileManager;

public class FileManagement {

    private String filename1 = "Series";
    private String filename2 = "Peliculas";
    private FileManager fileManager = new FileManager();
    private static final Type SERIES_TYPE = new TypeToken<List<Series>>() {
    }.getType();
    public static final String filePath = "src\\main\\java\\co\\edu\\uptc\\persistence\\";
    public static final String fileExtension = ".json";
    String fileNamee1 = filePath + filename1 + fileExtension;

    Gson gson = new Gson();

    public void saveSerie(Series serie) {
        List<Series> series = fileManager.readFile(fileNamee1, SERIES_TYPE);
        series.add(serie);
        fileManager.saveObjectToFile(fileNamee1, series, SERIES_TYPE);
    }

    public void displaySeries() {
        List<Series> series = fileManager.readFile(fileNamee1, SERIES_TYPE);

        // Save the series information to file
        fileManager.saveObjectToFile("series_output", series, SERIES_TYPE);
    }

    public <T> List<T> readJsonFile(String fileName, Type type) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                return new ArrayList<>();
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        }
        try (FileReader reader = new FileReader(file)) {
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private <T> void writeJsonFile(String fileName, List<T> data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
