import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

class FileManager {
    // Implementación de FileManager (puedes ajustar según tus necesidades)
}

class Category {
    private String name;
    private String description;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Getters y setters (puedes añadir según tus necesidades)

    @Override
    public boolean equals(Object obj) {
        // Implementación de equals
    }

    @Override
    public String toString() {
        // Implementación de toString
    }
}

class Multimedia {
    // Implementación de Multimedia (puedes ajustar según tus necesidades)
}

class Movies extends Multimedia {
    private int duration;

    public Movies(String title, List<Category> categories, String details, int releaseYear, int duration) {
        super(title, categories, details, releaseYear);
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
        return super.toString() + ", duration: " + duration + " minutes";
    }
}

interface MoviePlayer {
    void playMovie(Movies movie);
}

class SimpleMoviePlayer implements MoviePlayer {
    @Override
    public void playMovie(Movies movie) {
        System.out.println("Reproduciendo película: " + movie.toString());
    }
}

public class Main {
    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        MoviePlayer moviePlayer = new SimpleMoviePlayer();
        MoviesControl moviesControl = new MoviesControl(fileManager, moviePlayer);

        // Agregar algunas películas
        moviesControl.addMovie("Pelicula1", "Categoria1", "Detalles1", 2022, 120, List.of(new Category("Categoria1", "Descripcion1")));
        moviesControl.addMovie("Pelicula2", "Categoria2", "Detalles2", 2023, 90, List.of(new Category("Categoria2", "Descripcion2")));
        moviesControl.addMovie("Pelicula3", "Categoria3", "Detalles3", 2021, 150, List.of(new Category("Categoria3", "Descripcion3")));

        // Mostrar todas las películas
        List<String> allMovies = moviesControl.showMovies();
        System.out.println("Todas las películas:");
        for (String movie : allMovies) {
            System.out.println(movie);
        }

        // Reproducir películas con duración de 120 minutos
        moviesControl.playMoviesByDuration(120);
    }
}

class MoviesControl {
    private FileManager fileManager;
    private ArrayList<Movies> movies;
    private MoviePlayer moviePlayer;

    public MoviesControl(FileManager fileManager, MoviePlayer moviePlayer) {
        this.movies = new ArrayList<>();
        this.fileManager = fileManager;
        this.moviePlayer = moviePlayer;

        if (fileManager.readFile("movies", new TypeToken<ArrayList<Movies>>() {}.getType()) != null) {
            movies = fileManager.readFile("movies", new TypeToken<ArrayList<Movies>>() {}.getType());
        } else {
            fileManager.createFile("movies");
        }
    }

    public void addMovie(String title, String categories, String details, int releaseYear, int duration, List<Category> categoriesList) {
        // Implementación de addMovie (puedes ajustar según tus necesidades)
    }

    public List<String> showMovies() {
        // Implementación de showMovies (puedes ajustar según tus necesidades)
    }

    public void playMoviesByDuration(int targetDuration) {
        List<Movies> matchingMovies = getMoviesByDuration(targetDuration);

        if (matchingMovies.isEmpty()) {
            System.out.println("No hay películas con la duración especificada.");
        } else {
            System.out.println("Reproduciendo películas con duración de " + targetDuration + " minutos:");
            for (Movies movie : matchingMovies) {
                moviePlayer.playMovie(movie);
            }
        }
    }

    public List<Movies> getMoviesByDuration(int targetDuration) {
        List<Movies> filteredMovies = new ArrayList<>();

        for (Movies movie : movies) {
            if (movie.getDuration() == targetDuration) {
                filteredMovies.add(movie);
            }
        }

        return filteredMovies;
    }
}
