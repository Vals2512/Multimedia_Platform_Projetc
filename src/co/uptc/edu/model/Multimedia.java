package co.uptc.edu.model;

import java.util.Timer;

public class Multimedia {

    private String tittle;
    private String category;
    private String details;
    private int releaseYear;



    public Multimedia() {
    }

    public Multimedia(String tittle, String category, String details, int releaseYear) {
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }


    @Override
    public String toString() {
        return "Titulo: " + tittle + ", categoria: " + category + ", detalles: " + details + ", año de lanzamiento: "
                + releaseYear;
    }

}
