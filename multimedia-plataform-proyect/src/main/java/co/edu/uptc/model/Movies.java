package co.edu.uptc.model;

import java.util.List;

public class Movies extends Multimedia {

    private int duration;
    private Multimedia multimedia;
    private String imagepath;


    public Movies() {

    }

public Movies(String tittle, List<Category> categories, String details, int releaseYear, int duration, String imagepath) {
        super(tittle, categories, details, releaseYear);
        this.duration = duration;
        this.imagepath = imagepath;
        multimedia = new Multimedia(tittle, categories, details, releaseYear);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getImagePath() {
        return imagepath;
    }
    
    public void setImagePath(String imagepath) {
        this.imagepath = imagepath;
    }

    @Override
    public String toString() {
        return multimedia +  ", duration: " + duration;
    }

}
