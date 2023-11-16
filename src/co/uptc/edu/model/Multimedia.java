package co.uptc.edu.model;

import java.util.Timer;

public class Multimedia {

    private String tittle;
    private String category;
    private char details;
    private int releaseYear;
    Timer timer = new Timer();

    public Multimedia() {
    }

    public Multimedia(String tittle, String category, char details, int releaseYear) {
        this.tittle = tittle;
        this.category = category;
        this.details = details;
        this.releaseYear = releaseYear;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public char getDetails() {
        return details;
    }

    public void setDetails(char details) {
        this.details = details;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void play(int duration) {
        try {

            System.out.println("playing");// agregar segundos
            Thread.sleep(duration);

            // Así, se da la impresión de que se ejecuta cada cierto tiempo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopPlay() {

    }

    @Override
    public String toString() {
        return "Multimedia [tittle=" + tittle + ", category=" + category + ", details=" + details + ", releaseYear="
                + releaseYear + "]";
    }

}
