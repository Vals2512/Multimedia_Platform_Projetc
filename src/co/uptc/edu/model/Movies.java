package co.uptc.edu.model;

import java.util.List;

public class Movies extends Multimedia {

    private int duration;
    private Multimedia multimedia;

    public Movies(String tittle, String category, String details, int releaseYear, int duration2) {

    }

    public Movies(String tittle, String category, String details, int releaseYear, int duration) {
        super(tittle, category, details, releaseYear);
        this.duration = duration;
        multimedia = new Multimedia(tittle, category, details, releaseYear);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Pelicula: " + multimedia + " duracion:" + duration;
    }

}
