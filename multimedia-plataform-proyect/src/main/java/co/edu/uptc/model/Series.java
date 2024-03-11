package co.edu.uptc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Series extends Multimedia {
    // private Multimedia multimedia;
    // private List<Chapter> chapters;
    // private String chapterName;
    // private int chapterDuration;
    // private Chapter chap;
    // private int releaseYear;
    // @SerializedName("name")
    private String name;
    // @SerializedName("seasons")
    private List<Season> seasons;
    private String imagepath;

    
    
    public Series() {
    }

    public Series(String name, String imagepath){
        this.name = name;
        this.seasons = new ArrayList<>();
        this.imagepath = imagepath;
    }

    
    public void addSeason(Season season) {
        this.seasons.add(season);
    }
    
    public List<Season> getSeasons(){
        return this.seasons;
    }
    
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagepath;
    }
    
    public void setImagePath(String imagepath) {
        this.imagepath = imagepath;
    }

    @Override
    public String toString() {
        return "Series{" +
                "name='" + name + '\'' +
                ", seasons=" + seasons +
                '}';
    }
}


