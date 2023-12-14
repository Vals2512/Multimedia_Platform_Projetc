package co.uptc.edu.control;

import java.util.ArrayList;
import java.util.List;

import co.uptc.edu.model.Category;
import co.uptc.edu.model.Movies;
import co.uptc.edu.model.Series;

public class MoviesControl {
    private ArrayList<Movies> movies;
    int duration = 0;

    public MoviesControl() {
        movies = new ArrayList<>();
    }

    public Movies searchMoviesObject(String name) {
        for (Movies s : movies) {
            if (s.getTittle().equals(name)) {
                return s;
            }
        }
        return null;
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

    public boolean addMovie(String tittle, String categories, String details, int releaseYear, int duration,
            List<Category> categoriesList) {
        String[] selectedIndices = categories.split(",");
        List<Category> selectedCategories = new ArrayList<>();
        for (String index : selectedIndices) {
            try {
                int categoryIndex = Integer.parseInt(index.trim()) - 1;
                if (categoryIndex >= 0 && categoryIndex < categoriesList.size()) {
                    selectedCategories.add(categoriesList.get(categoryIndex));
                } else {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return addMovie(new Movies(tittle, selectedCategories, details, releaseYear, duration));
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

    public void showMovies(){
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i+1)+". "+movies.get(i).toString());
        }
    }

    public void showMoviesTittles(){
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i+1)+". "+movies.get(i).getTittle());
        }
    }

    public ArrayList<Movies> getMovies() {
        return movies;
    }

}
