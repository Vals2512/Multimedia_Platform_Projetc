package co.uptc.edu.model;

import java.util.List;
import java.util.Timer;

public class Multimedia {

    private String tittle;
    private List<Category> categories;
    private String details;
    private int releaseYear;

    Timer timer = new Timer();

    public Multimedia() {}

    public Multimedia(String tittle, List<Category> categories, String details, int releaseYear) {
        this.tittle = tittle;
        this.categories = categories;
        this.details = details;
        this.releaseYear = releaseYear;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
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
        return "Titulo: " + tittle + ", categorias: " + categories + ", detalles: " + details + ", año de lanzamiento: "
                + releaseYear;
    }

}
