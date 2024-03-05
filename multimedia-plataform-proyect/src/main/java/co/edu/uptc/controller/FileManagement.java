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

import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Payment;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.Playlist;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Series;
import co.edu.uptc.model.User;

public class FileManagement {

    private String filename1 = "Series";
    private String filename2 = "Peliculas";
    private String filename3 = "Usuarios";
    private static final Type SERIES_TYPE = new TypeToken<List<Series>>() {}.getType();
    private static final Type USERS_TYPE = new TypeToken<List<User>>() {}.getType();

    public static final String filePath = "src\\main\\java\\co\\edu\\uptc\\persistence\\";
    public static final String fileExtension = ".json";
    String fileNamee1 = filePath+filename1+fileExtension;
    String fileNamee3 = filePath+filename3+fileExtension;


    Gson gson = new Gson();


public void register(String email, String password, Plan plan, Payment payment) {
        User user = new User(email, password, plan, payment);
        List<User> users = readJsonFile(fileNamee3, USERS_TYPE);
        for (User existingUser : users) {
            if (existingUser.getEmail().equals(user.getEmail())) {
                throw new IllegalArgumentException("El correo electrónico ya está en uso");
            }
        }
        users.add(user);
        writeJsonFile(fileNamee3, users);
    }

    public boolean login(String email, String password){
        List<User> users = readJsonFile(fileNamee3, USERS_TYPE);
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
        
    }



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

    
    public List<Series> getSeries() {
        return readJsonFile(fileNamee1, SERIES_TYPE);
    }

    public Payment makePayment(Plan plan, String metodoPago) {
        Payment payment = new Payment(plan.getPrice(), metodoPago);
        return payment;
    }

    public List<String> playSerie(String serieName){
        List<Series> series = readJsonFile(fileNamee1, SERIES_TYPE);
        List<String> playbackLog = new ArrayList<>();
        for (Series serie : series) {
            if (serie.getName().equals(serieName)){
                playbackLog.add("Playing: " + serie.getName());
                for (Season season : serie.getSeasons()) {
                    for (Chapter chapter : season.getChapters()) {
                    playbackLog.add("Playing chapter: " + chapter.getName() + ", Season: " + season.getName());
                    playbackLog.add("Chapter duration is: " + chapter.getDuration() + " minutes");
                    int numberOfSpaces = 50;
                    long episodeDuration = chapter.getDuration() * 10;
                    for (int currentPitch = 0; currentPitch <= numberOfSpaces; currentPitch++) {
                        int porcentaje = (currentPitch * 100) / numberOfSpaces;

                        StringBuilder progress = new StringBuilder("\r|");
                        for (int i = 0; i < currentPitch; i++) {
                            progress.append("*");
                        }

                        for (int i = currentPitch; i < numberOfSpaces; i++) {
                            progress.append(" ");
                        }

                        progress.append("|").append(porcentaje).append(" %");
                        playbackLog.add(progress.toString());
                        try {
                            Thread.sleep(episodeDuration / numberOfSpaces); // Espera la cantidad de tiempo calculada
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    playbackLog.add("The episode " + chapter.getName() +", Season: " + season.getName() + " has finished playing.");
                    playbackLog.add("Do you want to play the next episode? (1 for yes, 0 for no)");
                    }
                    
                }
                playbackLog.add("There are no more episodes to play.");
                playbackLog.add("Do you want to play another series? (1 for yes, 0 for no)");
            }
        }
        return playbackLog;
    
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
    public void deleteMovie(){}
    private <T> void writeJsonFile(String fileName, List<T> data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
