package co.uptc.edu.control;

import java.util.ArrayList;
import java.util.List;

import co.uptc.edu.model.Multimedia;

public class MultimediaControl {
    private List<Multimedia> movies;
    private List<Multimedia> series;

    public MultimediaControl(){
        movies=new ArrayList<>();
    }
    public int searchMovie(String tittle){
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTittle().equals(tittle)) {
                return i;
            }
        }
        return -1;
}

    public Multimedia getMovieTittle(String tittle){
        int movieIndex = searchMovie(tittle);
        if (movieIndex != -1) {
            return movies.get(movieIndex);
        }
        return null;
    }
    public boolean addMovie(Multimedia tittle){
        if (searchMovie(tittle.getTittle()) == -1) {
            movies.add(tittle);
            return true;
        }
        return false;
    }
    public List<Multimedia> getMovies() {
        return movies;
    }
    public void setMovies(List<Multimedia> movies) {
        this.movies = movies;
    }
    public List<Multimedia> getSeries() {
        return series;
    }
    public void setSeries(List<Multimedia> series) {
        this.series = series;
    }


    


}
