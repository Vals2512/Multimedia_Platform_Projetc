package co.edu.uptc.model;


import java.util.ArrayList;
import java.util.List;

public class Series extends Multimedia {

    private String name;
    private List<Season> seasons;
    private String imagePath;


    
    
    public Series() {
    }

    public Series(String name, String imagePath){
        this.name = name;
        this.seasons = new ArrayList<>();
        this.imagePath = imagePath;
    }

    public void addSeason(Season season) {
        this.seasons.add(season);
    }

    public List<Season> getSeasons() {
        return this.seasons;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Series{" +
                "name='" + name + '\'' +
                ", seasons=" + seasons +
                '}';
    }

}


