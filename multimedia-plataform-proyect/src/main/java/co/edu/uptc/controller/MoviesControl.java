package co.edu.uptc.controller;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Movies;
import co.edu.uptc.util.FileManager;

public class MoviesControl {
    private FileManager fileManager;
    private ArrayList<Movies> movies;
    int duration = 0;

    public MoviesControl() {
        movies = new ArrayList<>();
        fileManager = new FileManager();
        if (fileManager.readFile("movies", new TypeToken<ArrayList<Movies>>() {
        }.getType()) != null) {
            movies = fileManager.readFile("movies", new TypeToken<ArrayList<Movies>>() {
            }.getType());
        } else {
            fileManager.createFile("movies");
        }
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
        // Load existing movies from file
        ArrayList<Movies> existingMovies = fileManager.readFile("movies", new TypeToken<ArrayList<Movies>>() {
        }.getType());

        if (existingMovies != null) {
            for (int i = 0; i < existingMovies.size(); i++) {
                if (existingMovies.get(i).getTittle().equals(tittle)) {
                    return i;
                }
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

    public boolean addMovie(Movies movie) {
        if (searchMovie(movie.getTittle()) == -1) {
            movies.add(movie);
            fileManager.saveObjectToFile("movies", movies, new TypeToken<ArrayList<Movies>>() {
            }.getType());
            return true;
        }
        return false;
    }

    public Movies updateMovie(String title, Movies updatedMovie) {
        int movieIndex = searchMovie(title);
        if (movieIndex != -1) {
            movies.set(movieIndex, updatedMovie);
            fileManager.saveObjectToFile("movies", movies, new TypeToken<ArrayList<Movies>>() {
            }.getType());
            return updatedMovie;
        }
        return null;
    }

    public boolean deleteMovie(String title) {
        int movieIndex = searchMovie(title);
        if (movieIndex != -1) {
            movies.remove(movieIndex);
            fileManager.saveObjectToFile("movies", movies, new TypeToken<ArrayList<Movies>>() {
            }.getType());
            return true;
        }
        return false;
    }

    public List<String> showMovies() {
        List<String> movieDetails = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            movieDetails.add((i + 1) + ". " + movies.get(i).toString());
        }
        return movieDetails;
    }

    public List<String> showMoviesTittles() {
        List<String> movieTitles = new ArrayList<>();
        for (int i = 0; i < movies.size(); i++) {
            movieTitles.add((i + 1) + ". " + movies.get(i).getTittle());
        }
        return movieTitles;
    }

    public ArrayList<Movies> getMovies() {
        return movies;
    }

    // public Multimedia getMovieTittle(String tittle) {
    // int movieIndex = searchMovie(tittle);
    // if (movieIndex != -1) {
    // return movies.get(movieIndex);
    // }
    // return null;
    // }

}
