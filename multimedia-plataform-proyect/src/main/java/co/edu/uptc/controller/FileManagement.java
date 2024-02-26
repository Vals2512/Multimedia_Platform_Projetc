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

public class FileManagement {

    private String filename1 = "Series";
    private String filename2 = "Peliculas";
    private static final Type SERIES_TYPE = new TypeToken<List<Series>>() {}.getType();
    public static final String filePath = "src\\main\\java\\co\\edu\\uptc\\persistence\\";
    public static final String fileExtension = ".json";
    String fileNamee1 = filePath+filename1+fileExtension;

    Gson gson=new Gson();


    public void saveSerie(Series serie){
        List<Series> series = readJsonFile(fileNamee1, SERIES_TYPE);
        series.add(serie);

        writeJsonFile(fileNamee1, series);


    }

    public void displaySeries(){
        List<Series> series = readJsonFile(fileNamee1, SERIES_TYPE);
        for (Series serie : series) {
            System.out.println(serie);
        }
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

