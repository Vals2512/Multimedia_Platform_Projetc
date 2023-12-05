package co.uptc.edu.control;

import java.util.ArrayList;
import java.util.List;

import co.uptc.edu.model.Movies;
import co.uptc.edu.model.MultimediaAction;

public class MoviesControl extends MultimediaAction {
    private List<Movies> movies;
    int duration = 0;

    public MoviesControl() {
        movies = new ArrayList<>();
    }

    public int searchMovie(String tittle) {
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getTittle().equals(tittle)) {
                return i;
            }
        }
        return -1;
    }

    public Movies getMovieTittle(String tittle) {
        int movieIndex = searchMovie(tittle);
        if (movieIndex != -1) {
            return movies.get(movieIndex);
        }
        return null;
    }

    public boolean addMovie(Movies tittle) {
        if (searchMovie(tittle.getTittle()) == -1) {
            movies.add(tittle);
            return true;
        }
        return false;
    }

    public Movies updateMovie(String title, Movies updatedMovie) {
        int movieIndex = searchMovie(title);
        if (movieIndex != -1) {
            movies.set(movieIndex, updatedMovie);
            return updatedMovie;
        }
        return null;
    }

    public boolean deleteMovie(String title) {
        int movieIndex = searchMovie(title);
        if (movieIndex != -1) {
            movies.remove(movieIndex);
            return true;
        }
        return false;
    }

    @Override
    public void play() {
        try {

            System.out.println("playing");// agregar segundos
            Thread.sleep(3000);

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
