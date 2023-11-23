package co.uptc.edu.model;

import java.util.ArrayList;
import java.util.List;

public class Series extends Multimedia {

    private int seasons;
    private Multimedia multimedia;
    private List<Capitulo> capitulos;
    private String nombreCapitulo;
    private int duracionCapitulo;
    private Capitulo chap;


    public Series(String tittle, String category, String details, int releaseYear, int seasons) {
        super(tittle, category, details, releaseYear);
        this.seasons = seasons;
        multimedia = new Multimedia(tittle, category, details, releaseYear);
        this.capitulos= new ArrayList<>();
    }

    public Series(String nombreCapitulo, int duracionCapitulo) {
        this.nombreCapitulo = nombreCapitulo;
        this.duracionCapitulo = duracionCapitulo;
        chap = new Capitulo(nombreCapitulo, duracionCapitulo);


    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public void agregarCapitulo(Capitulo capitulo) {
        this.capitulos.add(capitulo);
    }


    @Override
    public String toString() {
        return "Serie: " + multimedia + " temporadas: " + seasons + chap ;
    }

}
