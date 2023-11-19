package co.uptc.edu.control;

import java.util.ArrayList;
import java.util.List;

import co.uptc.edu.model.Movies;
import co.uptc.edu.model.Multimedia;
import co.uptc.edu.model.MultimediaAction;

public class MoviesControl extends MultimediaAction {
    private List<Multimedia> movies;
    int duration = 0;

    public MoviesControl(){
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



    @Override
    public void play() {
        try {

            System.out.println("playing");// agregar segundos
            Thread.sleep(duration);

            // Así, se da la impresión de que se ejecuta cada cierto tiempo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void restart() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'restart'");
    }

    @Override
    public void continuePlaying() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'continuePlaying'");
    }

    @Override
    public void stopPlay() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stopPlay'");
    }

}
