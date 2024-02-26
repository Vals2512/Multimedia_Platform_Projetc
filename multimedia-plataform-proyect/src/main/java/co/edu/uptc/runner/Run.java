package co.edu.uptc.runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import co.edu.uptc.controller.AdminControl;
import co.edu.uptc.controller.FileManagement;
import co.edu.uptc.controller.MoviesControl;
import co.edu.uptc.controller.SeriesControl;
import co.edu.uptc.controller.UserControl;
import co.edu.uptc.model.Category;
import co.edu.uptc.model.Chapter;
import co.edu.uptc.model.Movies;
import co.edu.uptc.model.Multimedia;
import co.edu.uptc.model.Season;
import co.edu.uptc.model.Series;
import co.edu.uptc.model.User;

public class Run {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserControl userControl = new UserControl();
        MoviesControl mc = new MoviesControl();
        SeriesControl src = new SeriesControl();
        FileManagement fm = new FileManagement();

        String email = "";
        String password = "";
        int releaseYear = 0;
        int duration = 0;
        int opc3 = 0;
        boolean validInput = false;
        List<Movies> movies = mc.getMovies();
        List<Series> series = new ArrayList<>();
        // 2Series seriees = new Series();
        // ArrayList<Series> listAddedSeries = new ArrayList<>();

        // Crear instancias de Category
        Category drama = new Category("Drama", "Películas o series de género dramático");
        Category action = new Category("Action", "Películas o series de acción");
        Category fiction = new Category("Fiction", "Películas o series de ficción");
        Category fantasy = new Category("Fantasy", "Películas o series de fantasía");
        Category thriller = new Category("Thriller", "Películas o series de suspenso");
        Category romance = new Category("Romance", "Películas o series de temática romántica");
        Category crime = new Category("Crimen", "Películas o series de crimen");

        List<Category> categoriesList = List.of(drama, action, fiction, fantasy, thriller, romance, crime);

        // Películas
        Movies movie1 = new Movies("Titanic", Arrays.asList(drama, romance), "Película de barco que se hunde", 1997,
                188);
        Movies movie2 = new Movies("El padrino", Arrays.asList(action, crime),
                "Película de policía corrupto y actividades ilegales", 1972, 175);

        // Series
        // Series serie1 = new Series("Stranger Things", Arrays.asList(fiction, thriller),
        //         "Serie de niños que descubren un nuevo mundo fantástico", 2018, 4, 190);
        // Series serie2 = new Series("Peaky Blinders", Arrays.asList(action, drama), "Serie de mafiosos de época", 2013,
        //         7, 188);

        

        mc.addMovie(movie1);
        mc.addMovie(movie2);
        // src.addSerie(serie1);
        // src.addSerie(serie2);

        int opc = 0;
        do {
            System.out.println("**************************************************");
            System.out.println("");
            System.out.println("Welcome to the multimedy platform.");
            System.out.println("Select an option: ");
            System.out.println("1. Admin login");
            System.out.println("2. Register");
            System.out.println("3. Login as registered user");
            System.out.println("4. Login as visitor");
            System.out.println("5. Leave");
            System.out.println("");
            System.out.println("**************************************************");
            try {
                opc = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please, input a valid option");
                sc.next(); // descarta la entrada incorrecta
                continue;
            }
            switch (opc) {
                case 1:
                    boolean leave = false; // Variable to return to main menu when true
                    int tries = 0;
                    while (!leave && tries < 3) {
                        System.out.println("Admin login");
                        System.out.print("Input your username: ");
                        String adminEmail = sc.next();
                        System.out.print("Input your password: ");
                        String adminPassword = sc.next();
                        AdminControl adc = new AdminControl();

                        if (adc.loginAdminBackup(adminEmail, adminPassword)) {
                            System.out.println("Successful login");
                            tries = 0;
                            int opc1 = 0;
                            do {
                                System.out.println("**************************************************");
                                System.out.println("");
                                System.out.println("""
                                        Welcome admin user
                                        1. Search registered user
                                        2. Delete registered user
                                        3. Add movie
                                        4. Update movie
                                        5. Delete movie
                                        6. Add series
                                        7. Update serie
                                        8. Delete series
                                        0. Leave""");
                                System.out.println("");
                                System.out.println("**************************************************");
                                try {
                                    opc1 = sc.nextInt();
                                    sc.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Please, input a valid option");
                                    sc.nextLine(); // descarta la entrada incorrecta
                                    continue;
                                }
                                switch (opc1) {
                                    case 1:
                                        System.out.println("Input the user´s email you wants to search: ");
                                        email = sc.next();

                                        if (userControl.getUser(email) != null) {
                                            System.out.println("User found");
                                            System.out.println("Email: " + userControl.getUser(email).getEmail());
                                            System.out.println("Password: " + userControl.getUser(email).getPassword());
                                        } else {
                                            System.out.println("User not found");
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Input the user´s email you wants to delete: ");
                                        email = sc.next();
                                        if (userControl.deleteUser(email)) {
                                            System.out.println("User deleted successfully");
                                        } else {
                                            System.out.println("Error deleting user");
                                        }
                                        break;

                                    case 3:
                                        System.out.println("Input the title of the movie you want to add: ");
                                        String title = sc.nextLine();
                                        System.out.println("Select categories (enter numbers separated by comma): ");
                                        for (int i = 0; i < categoriesList.size(); i++) {
                                            System.out.println((i + 1) + ". " + categoriesList.get(i).getName());
                                        }
                                        System.out.print("Selected: ");
                                        String selectedCategories1 = sc.nextLine();
                                        System.out.println("Input the details of the movie you want to add: ");
                                        String details = sc.nextLine();

                                        do {
                                            System.out.println("Input the release year of the movie you want to add: ");
                                            try {
                                                releaseYear = sc.nextInt();
                                                validInput = true;
                                                if (releaseYear > java.time.Year.now().getValue() || releaseYear <= 0) {
                                                    System.out.println(
                                                            "Error: Release year cannot be greater than the current year.");
                                                    validInput = false;
                                                }
                                            } catch (InputMismatchException e) {
                                                System.out.println("Error: Please input a valid year.");
                                                sc.next(); // Clear the buffer
                                                validInput = false;
                                            }
                                        } while (!validInput);

                                        do {
                                            System.out.println(
                                                    "Input the duration of the movie you want to add (in minutes): ");
                                            try {
                                                duration = sc.nextInt();
                                                if (duration <= 0) {
                                                    System.out.println("Input a valid duration");
                                                }
                                            } catch (InputMismatchException e) {
                                                System.out.println("Input a valid value");
                                                sc.next(); // Clear the buffer
                                            }
                                        } while (duration <= 0);

                                        String fileName = "movies.json"; // Replace with your desired file name
                                        if (mc.addMovie(title, selectedCategories1, details, releaseYear, duration,
                                                categoriesList)) {
                                            System.out.println("Movie added successfully");
                                        } else {
                                            System.out.println("Error adding movie");
                                        }

                                        break;

                                    case 4:
                                        System.out.println("Enter the title of the movie you want to update: ");
                                        String movieToUpdateTitle = sc.nextLine();

                                        Movies existingMovie = mc.getMovieTittle(movieToUpdateTitle);
                                        if (existingMovie != null) {
                                            System.out.println(
                                                    "Enter the new title (or press Enter to keep the existing title): ");
                                            String newTitle = sc.nextLine();
                                            System.out.println(
                                                    "Enter the new categories (or press Enter to keep the existing categories): ");
                                            String newCategories = sc.nextLine();
                                            System.out.println(
                                                    "Enter the new details (or press Enter to keep the existing details): ");
                                            String newDetails = sc.nextLine();
                                            int newReleaseYear = 0;
                                            do {
                                                System.out.println(
                                                        "Enter the new release year (or press 0 to keep the existing release year): ");
                                                try {
                                                    newReleaseYear = sc.nextInt();
                                                    validInput = true;
                                                    if (newReleaseYear > java.time.Year.now().getValue()
                                                            || newReleaseYear <= 0) {
                                                        System.out.println(
                                                                "Error: Release year cannot be greater than the current year and doesn't can be negative.");
                                                        validInput = false;
                                                    }
                                                } catch (InputMismatchException e) {
                                                    System.out.println("Error: Please input a valid year.");
                                                    sc.next(); // Clear the buffer
                                                }
                                            } while (!validInput);
                                            System.out.println(
                                                    "Enter the new duration (or press 0 to keep the existing duration): ");
                                            int newDuration = sc.nextInt();

                                            List<Category> newCategoriesList;
                                            if (!newCategories.isEmpty()) {
                                                String[] selectedIndices = newCategories.split(",");
                                                newCategoriesList = new ArrayList<>();
                                                for (String index : selectedIndices) {
                                                    try {
                                                        int categoryIndex = Integer.parseInt(index.trim()) - 1;
                                                        if (categoryIndex >= 0
                                                                && categoryIndex < categoriesList.size()) {
                                                            newCategoriesList.add(categoriesList.get(categoryIndex));
                                                        } else {
                                                            System.out.println("Invalid category index.");
                                                        }
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("Invalid category index.");
                                                    }
                                                }
                                            } else {
                                                newCategoriesList = existingMovie.getCategories();
                                            }

                                            Movies updatedMovie = new Movies(
                                                    newTitle.isEmpty() ? existingMovie.getTittle() : newTitle,
                                                    newCategoriesList,
                                                    newDetails.isEmpty() ? existingMovie.getDetails() : newDetails,
                                                    newReleaseYear > 0 ? newReleaseYear
                                                            : existingMovie.getReleaseYear(),
                                                    newDuration > 0 ? newDuration : existingMovie.getDuration());

                                            if (mc.updateMovie(movieToUpdateTitle, updatedMovie) != null) {
                                                System.out.println("Movie updated successfully");
                                            } else {
                                                System.out.println("Error updating movie");
                                            }
                                        } else {
                                            System.out.println("Movie not found");
                                        }
                                        break;

                                    case 5:
                                        System.out.println("Enter the title of the movie you want to delete: ");
                                        String movieToDelete = sc.nextLine();

                                        if (mc.deleteMovie(movieToDelete)) {
                                            System.out.println("Movie deleted successfully");
                                        } else {
                                            System.out.println("Error deleting movie");
                                        }
                                        break;
                                    case 6:
                                        System.out.println("Input the series tittle");
                                        String tittle=sc.nextLine();
                                        Series sr=new Series(tittle);

                                        System.out.print("How many seasons do you want to add? ");
                                        int numSeasons = sc.nextInt();

                                        for (int i = 1; i <= numSeasons; i++) {
                                            Season season = new Season();
                                            System.out.print("Enter the name of season " + i + ": ");
                                            sc.nextLine(); // Consume the new line
                                            String seasonName = sc.nextLine();
                                            season.setName(seasonName);
                                        
                                            System.out.print("How many episodes does season " + i + " have? ");
                                            int numEpisodes = sc.nextInt();
                                            sc.nextLine(); // Consume the new line
                                        
                                            for (int j = 1; j <= numEpisodes; j++) {
                                                System.out.print("Enter the name of episode " + j + ": ");
                                                String episodeName = sc.nextLine();
                                        
                                                System.out.print("Enter the duration of episode " + j + " (in minutes): ");
                                                int episodeDuration = sc.nextInt();
                                                sc.nextLine(); // Consume the new line
                                        
                                                Chapter chapter = new Chapter(episodeName, episodeDuration);
                                                season.addChapters(chapter);
                                            }
                                            sr.addSeason(season);
                                        }

                                        series.add(sr);
                                        fm.saveSerie(sr);
                                        System.out.println("Serie added succesfully");

                                    break;

                                    // case 6:
                                        // System.out.println("Enter the title of the serie: ");
                                        // String seriesTitle = sc.nextLine();

                                        // List<Category> selectedCategories = selectCategories(categoriesList);

                                        // System.out.println("Enter the details of the series: ");
                                        // String seriesDetails = sc.nextLine();
                                        // do {
                                        //     System.out.println("Enter the release year of the series: ");
                                        //     try {
                                        //         releaseYear = sc.nextInt();
                                        //         validInput = true;
                                        //         if (releaseYear > java.time.Year.now().getValue() || releaseYear <= 0) {
                                        //             System.out.println(
                                        //                     "Error: Release year cannot be greater than the current year.");
                                        //             validInput = false;
                                        //         }
                                        //     } catch (InputMismatchException e) {
                                        //         System.out.println("Error: Please input a valid year.");
                                        //         sc.next(); // Clear the buffer
                                        //     }
                                        // } while (!validInput);
                                        // /* */
                                        // do {
                                        //     try {
                                        //         System.out.println("Enter the number of seasons of the series: ");
                                        //         seasons = sc.nextInt();
                                        //         if (seasons <= 0) {
                                        //             System.out.println("Input a valid value");
                                        //         }
                                        //         sc.nextLine(); // Consume the newline character left by nextInt()
                                        //     } catch (InputMismatchException e) {
                                        //         System.out.println("Input a valid value");
                                        //     }

                                        // } while (seasons <= 0);

                                        // // Crear la nueva serie con una lista vacía de capítulos
                                        // Series newSeries = new Series(seriesTitle, selectedCategories, seriesDetails,
                                        //         releaseYear, seasons, 0);

                                        // // Agregar la nueva serie
                                        // if (src.addSerie(newSeries)) {
                                        //     System.out.println("Series added successfully");
                                        // } else {
                                        //     System.out.println("Error adding series");
                                        // }
                                        // break;

                                    case 7:
                                        System.out.println("Enter the title of the series you want to update: ");
                                        String seriesToUpdateTitle = sc.nextLine();
                                        Series seriesToUpdate = src.getSerieTittle(seriesToUpdateTitle);

                                        if (seriesToUpdate != null) {
                                            // Resto del código para la actualización de la serie
                                        } else {
                                            System.out.println("Series not found");
                                        }
                                        break;

                                    case 8:
                                        System.out.println("Enter the title of the series you want to delete: ");
                                        String seriesToDeleteTitle = sc.nextLine();

                                        if (src.deleteSeries(seriesToDeleteTitle)) {
                                            System.out.println("Series deleted successfully");
                                        } else {
                                            System.out.println("Error deleting series");
                                        }
                                        break;

                                    // case 9:
                                    // System.out.println(
                                    // "Enter the title of the series you want to add a chapter to: ");
                                    // String seriesTitleToAddChapter = sc.nextLine();
                                    // Series seriesToAddChapter = src
                                    // .getSerieTittle(seriesTitleToAddChapter);

                                    // if (seriesToAddChapter != null) {
                                    // // Resto del código para agregar un capítulo a la serie
                                    // } else {
                                    // System.out.println("Series not found");
                                    // }
                                    // break;

                                    case 0:
                                        System.out.println("Exiting.");
                                        leave = true;
                                        break;

                                    default:
                                        System.out.println("Enter a valid option.");
                                        break;
                                }

                            } while (!leave && opc1 != 0);
                        } else {
                            tries++;
                            System.out.println("Error logging in. Attempt " + tries + " of 3");
                        }
                    }
                    if (tries == 3) {
                        System.out.println("You have exhausted your attempts. Returning to the main menu.");
                        break;
                    }
                    break;

                case 2:
                    System.out.println("User registration");
                    System.out.print("Enter your email: ");
                    email = sc.next();
                    while (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { // Line to validate that the email
                        // contains the necessary security
                        System.out.println("Invalid email. Please enter a valid email: ");
                        email = sc.next();
                    }
                    System.out.print("Enter your password: ");
                    password = sc.next();
                    while (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")) { // Line to validate that the
                        // password contains the
                        // necessary security
                        System.out.println(
                                "Invalid password. Please enter a password with at least 8 characters, one uppercase letter, one lowercase letter, and one number: ");
                        password = sc.next();
                    }
                    String passwordConfirmation = "";
                    do {
                        System.out.print("Confirm your password: ");
                        passwordConfirmation = sc.next();
                        if (!password.equals(passwordConfirmation)) {
                            System.out.println("Passwords do not match. Please try again.");
                        }
                    } while (!password.equals(passwordConfirmation));

                    if (userControl.addUser(new User(email, password), passwordConfirmation)) { // Added to the array
                        // list of users,
                        // validating that the
                        // data matches
                        System.out.println("User registered successfully");
                    } else {
                        System.out.println("Error registering the user");
                    }
                    
                break;

                case 3:
                    String continueOption = "";
                    do {
                        System.out.println("Login");
                        System.out.print("Enter your email: ");
                        email = sc.next();
                        System.out.print("Enter your password: ");
                        password = sc.next();

                        if (userControl.login(email, password)) {
                            System.out.println("Successful login");
                            int opc2 = 10;
                            do {
                                System.out.println("Welcome User");
                                System.out.println("1. Search for Movie");
                                System.out.println("2. your list ");
                                System.out.println("3. Play movies");
                                System.out.println("4. Play Series");
                                System.out.println("0. Go Back");
                                try {
                                    opc2 = sc.nextInt();
                                    sc.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Please enter a valid option");
                                    sc.next(); // Discards incorrect entry
                                    continue;
                                }
                                inner: switch (opc2) {
                                    case 1:

                                        System.out.println("Enter the title of the movie you want to search for: ");
                                        String tittle = sc.nextLine();
                                        Multimedia movie = mc.getMovieTittle(tittle);
                                        if (movie != null) {
                                            System.out.println(movie.toString());
                                        } else {
                                            System.out.println("Movie not found");
                                        }
                                        break inner;
                                    case 2:
                                        opc3 = 10;
                                        if (userControl.searchUserObject(email).getPlaylist().getName().isEmpty()) {

                                            System.out.println("input the playlist name");
                                            String playlistName = sc.nextLine();
                                            userControl.searchUserObject(email).getPlaylist().setName(playlistName);

                                        }
                                        do {
                                            System.out.println("this is your playlist: "
                                                    + userControl.searchUserObject(email).getPlaylist());
                                            System.out.println("""
                                                    1.add series to playlist
                                                    2.add movies to playlist
                                                    3.clear playlist
                                                    0.back""");
                                            try {

                                                opc3 = sc.nextInt();
                                            } catch (InputMismatchException e) {
                                                System.out.println("no valid option");
                                            }
                                            sc.nextLine();
                                            playlistoption: switch (opc3) {

                                                case 1:
                                                    System.out.println(src.showSeries());
                                                    System.out.println("Input the series name to be addded");
                                                    String seriesName = sc.nextLine();
                                                    if (src.searchSeriesObject(seriesName) != null) {
                                                        userControl.addSeriesToPlaylist(email,
                                                                src.searchSeriesObject(seriesName));
                                                    } else {
                                                        System.out.println("series not found");
                                                    }
                                                    break playlistoption;

                                                case 2:
                                                    mc.showMoviesTittles();
                                                    System.out.println("Input the movies name to be addded");
                                                    String moviesName = sc.nextLine();
                                                    if (mc.searchMoviesObject(moviesName) != null) {
                                                        userControl.addMoviesToPlaylist(email,
                                                                mc.searchMoviesObject(moviesName));
                                                    } else {
                                                        System.out.println("movies not found");
                                                    }
                                                    break playlistoption;
                                                case 3:
                                                    userControl.clearPlaylist(email);
                                                    break playlistoption;
                                                case 0:
                                                    break inner;
                                                default:
                                                    System.out.println("no valid option");
                                                    break playlistoption;
                                            }
                                        } while (opc2 != 0);
                                        break;
                                    case 3:
                                        int opc4 = 0;
                                        int selectedMovie = 0;
                                        do {
                                            System.out.println("Play Movie");
                                            System.out.println(
                                                    "Select the movie you want to reproduce or enter 0 to return to the previous menu");
                                            System.out.println("Press 0 if you want to return to the previous menu");
                                            mc.showMoviesTittles();
                                            // Solucionar validacion, me devuelve dos veces al menu anterior
                                            try {
                                                selectedMovie = sc.nextInt();
                                            } catch (InputMismatchException e) {
                                                System.out.println("Input a valid option");
                                                continue;
                                            }

                                            if (selectedMovie == 0) {
                                                break; // Si el usuario selecciona 0, rompe el bucle y vuelve al menú
                                                // anterior
                                            }
                                            System.out.println("The movie will last "
                                                    + mc.getMovies().get(selectedMovie - 1).getDuration() + " minutes");
                                            System.out.println(
                                                    "PLAYING: " + mc.getMovies().get(selectedMovie - 1).getTittle());
                                            int Spaces = 50;
                                            long movieDuration = mc.getMovies().get(selectedMovie - 1).getDuration()
                                                    * 10; // Duración de la película en segundos

                                            for (int Pitch = 0; Pitch <= Spaces; Pitch++) {
                                                int Percentage = (Pitch * 100) / Spaces;

                                                System.out.print("\r|");
                                                for (int i = 0; i < Pitch; i++) {
                                                    System.out.print("*");
                                                }

                                                for (int i = Pitch; i < Spaces; i++) {
                                                    System.out.print(" ");
                                                }

                                                System.out.print("|" + Percentage + " %");
                                                try {
                                                    Thread.sleep(movieDuration / Spaces); // Espera la cantidad de
                                                    // tiempo calculada
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }

                                            }
                                            System.out.println();

                                            System.out.println(
                                                    "The movie " + mc.getMovies().get(selectedMovie - 1).getTittle()
                                                            + " has finished playing.");
                                            System.out.println(
                                                    "Do you want to reproduce another movie? \n(0 for no, 1 for yes)");

                                            // Solucionar validacion, me devuelve dos veces al menu anterior y no me
                                            // deja seleccionar si quiero reproducir otra pelicula
                                            try {
                                                opc4 = sc.nextInt();
                                                sc.nextLine(); // Limpiar el buffer de entrada
                                            } catch (InputMismatchException e) {
                                                System.out.println("Input a valid option");
                                                sc.nextLine(); // Limpiar el buffer de entrada
                                                opc4 = 1; // Establecer un valor por defecto para opc4
                                            }

                                        } while (opc4 != 0);
                                        break;

                                    case 4:
                                        int opc5 = 0;
                                        int selectedSerie = 0;
                                        do {
                                            System.out.println("Play Serie");
                                            System.out.println(
                                                    "Select the serie you want to reproduce or enter 0 to return to the previous menu");
                                            System.out.println("Press 0 if you want to return to the previous menu");
                                            src.showSeriesTittles();

                                            // Solucionar validacion, me devuelve dos veces al menu anterior
                                            try {
                                                selectedSerie = sc.nextInt();
                                            } catch (InputMismatchException e) {
                                                System.out.println("Input a valid option");
                                                continue;
                                            }

                                            if (selectedSerie == 0) {
                                                break; // Si el usuario selecciona 0, rompe el bucle y vuelve al menú
                                                // anterior
                                            }
                                            System.out.println("The series will last 5 minutes");
                                            System.out.println(
                                                    "PLAYING: " + src.getSeries().get(selectedSerie - 1).getTittle());
                                            int Spaces = 50;
                                            long serieDuration = 5000; // Duración de la película en segundos
                                            for (int Pitch = 0; Pitch <= Spaces; Pitch++) {
                                                int porcentaje = (Pitch * 100) / Spaces;

                                                System.out.print("\r|");
                                                for (int i = 0; i < Pitch; i++) {
                                                    System.out.print("*");
                                                }

                                                for (int i = Pitch; i < Spaces; i++) {
                                                    System.out.print(" ");
                                                }

                                                System.out.print("|" + porcentaje + " %");
                                                try {
                                                    Thread.sleep(serieDuration / Spaces); // Espera la cantidad de
                                                    // tiempo calculada
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }

                                            }
                                            System.out.println();
                                            System.out.println(
                                                    "The series " + src.getSeries().get(selectedSerie - 1).getTittle()
                                                            + " has finished playing.");
                                            System.out.println(
                                                    "Do you want to reproduce another serie? \n(0 for no, 1 for yes)");

                                            // Solucionar validacion, me devuelve dos veces al menu anterior y no me
                                            // deja seleccionar si quiero reproducir otra pelicula
                                            try {
                                                opc5 = sc.nextInt();
                                                sc.nextLine(); // Limpiar el buffer de entrada
                                            } catch (InputMismatchException e) {
                                                System.out.println("Input a valid option");
                                                sc.nextLine(); // Limpiar el buffer de entrada
                                                opc5 = 1; // Establecer un valor por defecto para opc5
                                            }

                                        } while (opc5 != 0);
                                        break;

                                    case 0:
                                        System.out.println("Leaving menu");
                                        break;
                                    default:
                                        System.out.println("Select a valid option");
                                        break;
                                }

                            } while (opc2 != 0);

                            break;
                        } else {
                            System.out.println("Error logging in");
                        }

                        System.out.println("Do you want to try again? (y/n)");
                        continueOption = sc.next();
                    } while (continueOption.equalsIgnoreCase("y"));

                    break;

                case 4:

                    int opVisitante = 0;
                    do {
                        System.out.println("Welcome Visitor");
                        System.out.println("What would you like to do?");
                        System.out.println("1. See avaliable movies");
                        System.out.println("2. See avaliable series");
                        System.out.println("3. Go back");
                        try {
                            opVisitante = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a valid option");
                            sc.next(); // Discards incorrect entry
                            continue;
                        }

                        switch (opVisitante) {
                            case 1:
                                System.out.println("The list of avaliable movies is:");
                                if (movies.isEmpty()) {
                                    System.out.println("There is not movies to show.");
                                } else {
                                    for (Multimedia moviess : movies) {
                                        System.out.println(moviess.toString());
                                    }
                                }
                                break;

                            case 2:
                                System.out.println("The list of avaliable series is: ");
                                fm.displaySeries();
                                break;

                            case 3:
                                System.out.println("Leaving menu");
                                break;

                            default:
                                System.out.println("Option not avaliable");
                                break;
                        }
                    } while (opVisitante != 3);

                    break;

                case 5:
                    System.out.println("Thank you for using the multimedia platform. Exiting.");
                    break;

                default:
                    System.out.println("Enter a valid option. Please try again");
                    break;

            }
        } while (opc != 5);
        sc.close();

    }

    private static List<Category> selectCategories(List<Category> categoriesList) {
        Scanner sc = new Scanner(System.in);
        List<Category> selectedCategories = new ArrayList<>();

        System.out.println("Available categories:");
        for (int i = 0; i < categoriesList.size(); i++) {
            System.out.println((i + 1) + ". " + categoriesList.get(i).getName());
        }

        System.out.println("Enter the indices of the categories you want to select (comma-separated): ");
        String selectedIndices = sc.nextLine();
        String[] indicesArray = selectedIndices.split(",");

        for (String index : indicesArray) {
            try {
                int categoryIndex = Integer.parseInt(index.trim()) - 1;
                if (categoryIndex >= 0 && categoryIndex < categoriesList.size()) {
                    selectedCategories.add(categoriesList.get(categoryIndex));
                } else {
                    System.out.println("Invalid category index: " + index);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid category index: " + index);
            }
        }

        return selectedCategories;
    }
}