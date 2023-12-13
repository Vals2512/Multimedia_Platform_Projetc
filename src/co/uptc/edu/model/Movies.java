package co.uptc.edu.model;

import java.util.List;

public class Movies extends Multimedia {

    private int duration;
    private Multimedia multimedia;

    public Movies() {

    }

    public Movies(String tittle, List<Category> categories, String details, int releaseYear, int duration) {
        super(tittle, categories, details, releaseYear);
        this.duration = duration;
        multimedia = new Multimedia(tittle, categories, details, releaseYear);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return multimedia +  " duration:" + duration;
    }

}
