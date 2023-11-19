package co.uptc.edu.model;

public class Movies extends Multimedia {

    private int duration;

    public Movies() {

    }

    public Movies(String tittle, String category, char details, int releaseYear, int duration) {
        super(tittle, category, details, releaseYear);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Movies [duration=" + duration + "]";
    }

}
