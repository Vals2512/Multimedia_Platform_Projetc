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
import co.edu.uptc.model.Payment;
import co.edu.uptc.model.Plan;
import co.edu.uptc.model.Playlist;
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
        // Series serie1 = new Series("Stranger Things", Arrays.asList(fiction,
        // thriller),
        // "Serie de niños que descubren un nuevo mundo fantástico", 2018, 4, 190);
        // Series serie2 = new Series("Peaky Blinders", Arrays.asList(action, drama),
        // "Serie de mafiosos de época", 2013,
        // 7, 188);

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

                                            // validar que no sea negativo
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

                                        String fileName = "movies.json";
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
                                        String tittle = sc.nextLine();
                                        Series sr = new Series(tittle);

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

                                                System.out.print(
                                                        "Enter the duration of episode " + j + " (in minutes): ");
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
                    while (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*]).{8,}$")) {
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

                    System.out.println("Select a subscription plan:");
                    System.out.println("1. Basic Plan");
                    System.out.println("2. Standard Plan");
                    System.out.println("3. Premium Plan");
                    int planOption = sc.nextInt();
                    Plan plan = null;
                    switch (planOption) {
                        case 1:
                            plan = new Plan("Basic", 7.99, 30);
                            break;
                        case 2:
                            plan = new Plan("Standard", 9.99, 30);
                            break;
                        case 3:
                            plan = new Plan("Premium", 11.99, 30);
                            break;
                        default:
                            System.out.println("Select a valid option");
                            break;
                    }
                    System.out.println("Select a payment method:");
                    System.out.println("1. Credit Card");
                    System.out.println("2. PayPal");
                    int paymentMethodOption = sc.nextInt();
                    String paymentMethod = null;
                    switch (paymentMethodOption) {
                        case 1:
                            paymentMethod = "Credit Card";
                            fm.makePayment(plan, paymentMethod);
                            break;
                        case 2:
                            paymentMethod = "PayPal";
                            fm.makePayment(plan, paymentMethod);
                            break;
                        default:
                            System.out.println("Select a valid option");
                            break;
                    }
                    Payment payment = new Payment(plan.getPrice(), paymentMethod);
                    try {
                        fm.register(email, passwordConfirmation, plan, payment);
                        User user = new User(email, passwordConfirmation, plan, payment);
                        System.out.println("Enter the name you want to give to the invoice file:");
                        sc.nextLine();
                        String invoiceFileName = sc.nextLine();
                        fm.generarFactura(user, invoiceFileName);
                        System.out.println("User successfully registered and plan selected: " + plan.getName());
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
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

                        if (fm.login(email, password)) {
                            System.out.println("Successful login");
                            int opc2 = 10;
                            do {
                                System.out.println("Welcome User");
                                System.out.println("1. Search for Movie");
                                System.out.println("2. Playlist Management");
                                System.out.println("3. Search for serie");
                                System.out.println("0. Go Back");
                                try {
                                    opc2 = sc.nextInt();
                                    sc.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Please enter a valid option");
                                    sc.next(); // Discards incorrect entry
                                    continue;
                                }

                                switch (opc2) {
                                    case 1:
                                        System.out.println("Enter the title of the movie you want to search for: ");
                                        String title = sc.nextLine();
                                        Multimedia movie = mc.getMovieTittle(title);
                                        if (movie != null) {
                                            System.out.println(movie.toString());
                                        } else {
                                            System.out.println("Movie not found");
                                        }
                                        break;
                                    case 2:
                                        int playlistSubMenuOption = 0;
                                        do {
                                            System.out.println("Playlist Management Menu");
                                            System.out.println("1. Create Playlist");
                                            System.out.println("2. Change Playlist Name");
                                            System.out.println("3. Add Movies to Playlist");
                                            System.out.println("4. Add Series to Playlist");
                                            System.out.println("5. Delete Movies from Playlist");
                                            System.out.println("6. Delete Series from Playlist");
                                            System.out.println("7. Play Series");
                                            System.out.println("8. Play Movie");
                                            System.out.println("9. Delete Playlist");
                                            System.out.println("0. Go Back");
                                            try {
                                                playlistSubMenuOption = sc.nextInt();
                                                sc.nextLine();
                                            } catch (InputMismatchException e) {
                                                System.out.println("Please enter a valid option");
                                                sc.next(); // Descarta la entrada incorrecta
                                                continue;
                                            }

                                            switch (playlistSubMenuOption) {
                                                case 1:
                                                    System.out.println("Enter the name for your new playlist:");
                                                    String newPlaylistName = sc.nextLine();
                                                    userControl.createPlaylist(email, newPlaylistName);
                                                    break;

                                                case 2:
                                                    System.out.println("Enter the current name of the playlist:");
                                                    String currentPlaylistName = sc.nextLine();

                                                    // Buscar la playlist por el nombre actual
                                                    Playlist currentPlaylist = userControl.getPlaylistByName(email,
                                                            currentPlaylistName);

                                                    if (currentPlaylist != null) {
                                                        System.out.println("Enter the new name for your playlist:");
                                                        String modifiedPlaylistName = sc.nextLine();

                                                        // Modificar el nombre de la playlist
                                                        userControl.modifyPlaylist(email, currentPlaylistName,
                                                                modifiedPlaylistName);

                                                        System.out.println("Playlist name changed successfully.");
                                                    } else {
                                                        System.out.println(
                                                                "Playlist not found. Please enter a valid playlist name.");
                                                    }
                                                    break;
                                                case 3:
                                                    System.out.println("Enter the name of the playlist:");
                                                    String playlistName = sc.nextLine();
                                                    // Buscar la lista por su nombre
                                                    Playlist playlist = userControl.getPlaylistByName(email,
                                                            playlistName);

                                                    if (playlist != null) {
                                                        System.out.println(
                                                                "Enter the name of the movie to add to your playlist:");
                                                        String movieNameToAdd = sc.nextLine();

                                                        // Buscar la película por su nombre
                                                        Movies movieToAdd = mc.searchMoviesObject(movieNameToAdd);

                                                        if (movieToAdd != null) {
                                                            // Verificar si la película ya está en la playlist
                                                            if (playlist.getContent().contains(movieToAdd)) {
                                                                System.out.println("Movie is already in the playlist.");
                                                            } else {
                                                                // Agregar la película a la playlist
                                                                userControl.addMoviesToPlaylist(email, movieToAdd);
                                                                System.out.println(
                                                                        "Movie added to the playlist successfully.");
                                                            }
                                                        } else {
                                                            System.out.println("Movie not found");
                                                        }
                                                    } else {
                                                        System.out.println(
                                                                "Playlist not found. Please enter a valid playlist name.");
                                                    }
                                                    break;
                                                case 4:
                                                    System.out.println("Enter the name of the playlist:");
                                                    String playlistNameForSeries = sc.nextLine();

                                                    // Buscar la lista por su nombre
                                                    Playlist seriesPlaylist = userControl.getPlaylistByName(email,
                                                            playlistNameForSeries);

                                                    if (seriesPlaylist != null) {
                                                        System.out.println(
                                                                "Enter the name of the series to add to your playlist:");
                                                        String seriesNameToAdd = sc.nextLine();

                                                        // Buscar la serie por su nombre
                                                        Series seriesToAdd = src.searchSeriesObject(seriesNameToAdd);

                                                        if (seriesToAdd != null) {
                                                            // Verificar si la serie ya está en la playlist
                                                            if (seriesPlaylist.getContent().contains(seriesToAdd)) {
                                                                System.out
                                                                        .println("Series is already in the playlist.");
                                                            } else {
                                                                // Agregar la serie a la playlist
                                                                userControl.addSeriesToPlaylist(email, seriesToAdd);
                                                                System.out.println(
                                                                        "Series added to the playlist successfully.");
                                                            }
                                                        } else {
                                                            System.out.println("Series not found");
                                                        }
                                                    } else {
                                                        System.out.println(
                                                                "Playlist not found. Please enter a valid playlist name.");
                                                    }
                                                    break;
                                                case 5:
                                                    System.out.println("Enter the name of the playlist:");
                                                    String playlistNameForMovies = sc.nextLine();

                                                    // Buscar la lista por su nombre
                                                    Playlist moviePlaylist = userControl.getPlaylistByName(email,
                                                            playlistNameForMovies);

                                                    if (moviePlaylist != null) {
                                                        System.out.println(
                                                                "Enter the name of the movie to delete from your playlist:");
                                                        String movieNameToDelete = sc.nextLine();

                                                        // Buscar la película por su nombre
                                                        Movies movieToDelete = mc.searchMoviesObject(movieNameToDelete);

                                                        if (movieToDelete != null) {
                                                            // Verificar si la película está en la playlist antes de
                                                            // eliminarla
                                                            if (moviePlaylist.getContent().contains(movieToDelete)) {
                                                                userControl.deleteMoviesFromPlaylist(email,
                                                                        movieToDelete);
                                                                System.out.println(
                                                                        "Movie deleted from the playlist successfully.");
                                                            } else {
                                                                System.out.println("Movie is not in the playlist.");
                                                            }
                                                        } else {
                                                            System.out.println("Movie not found");
                                                        }
                                                    } else {
                                                        System.out.println(
                                                                "Playlist not found. Please enter a valid playlist name.");
                                                    }
                                                    break;

                                                case 6:
                                                    System.out.println("Enter the name of the playlist:");
                                                    String playlistNameForSeriesToDelete = sc.nextLine();

                                                    // Buscar la lista por su nombre
                                                    Playlist seriesPlaylistToDelete = userControl
                                                            .getPlaylistByName(email, playlistNameForSeriesToDelete);

                                                    if (seriesPlaylistToDelete != null) {
                                                        System.out.println(
                                                                "Enter the name of the series to delete from your playlist:");
                                                        String seriesNameToDelete = sc.nextLine();

                                                        // Buscar la serie por su nombre
                                                        Series seriesToDelete = src
                                                                .searchSeriesObject(seriesNameToDelete);

                                                        if (seriesToDelete != null) {
                                                            // Verificar si la serie está en la playlist antes de
                                                            // eliminarla
                                                            if (seriesPlaylistToDelete.getContent()
                                                                    .contains(seriesToDelete)) {
                                                                userControl.deleteSeriesFromPlaylist(email,
                                                                        seriesToDelete);
                                                                System.out.println(
                                                                        "Series deleted from the playlist successfully.");
                                                            } else {
                                                                System.out.println("Series is not in the playlist.");
                                                            }
                                                        } else {
                                                            System.out.println("Series not found");
                                                        }
                                                    } else {
                                                        System.out.println(
                                                                "Playlist not found. Please enter a valid playlist name.");
                                                    }
                                                    break;

                                                case 8:
                                                    int opc4 = 0;
                                                    int selectedMovie = 0;
                                                    do {
                                                        System.out.println("Play Movie");
                                                        System.out.println(
                                                                "Select the movie you want to reproduce or enter 0 to return to the previous menu");
                                                        System.out.println(
                                                                "Press 0 if you want to return to the previous menu");
                                                        mc.showMoviesTittles();
                                                        // Solucionar validacion, me devuelve dos veces al menu anterior
                                                        try {
                                                            selectedMovie = sc.nextInt();
                                                        } catch (InputMismatchException e) {
                                                            System.out.println("Input a valid option");
                                                            continue;
                                                        }

                                                        if (selectedMovie == 0) {
                                                            break; // Si el usuario selecciona 0, rompe el bucle y
                                                                   // vuelve al menú
                                                            // anterior
                                                        }
                                                        System.out.println("The movie will last "
                                                                + mc.getMovies().get(selectedMovie - 1).getDuration()
                                                                + " minutes");
                                                        System.out.println(
                                                                "PLAYING: " + mc.getMovies().get(selectedMovie - 1)
                                                                        .getTittle());
                                                        int Spaces = 50;
                                                        long movieDuration = mc.getMovies().get(selectedMovie - 1)
                                                                .getDuration()
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
                                                                Thread.sleep(movieDuration / Spaces); // Espera la
                                                                                                      // cantidad de
                                                                // tiempo calculada
                                                            } catch (InterruptedException e) {
                                                                e.printStackTrace();
                                                            }

                                                        }
                                                        System.out.println();

                                                        System.out.println(
                                                                "The movie "
                                                                        + mc.getMovies().get(selectedMovie - 1)
                                                                                .getTittle()
                                                                        + " has finished playing.");
                                                        System.out.println(
                                                                "Do you want to reproduce another movie? \n(0 for no, 1 for yes)");

                                                        // Solucionar validacion, me devuelve dos veces al menu anterior
                                                        // y no me
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

                                                case 7:
                                                    boolean playAnotherSerie;
                                                    do {
                                                        series = fm.getSeries();
                                                        for (int i = 0; i < series.size(); i++) {
                                                            System.out
                                                                    .println((i + 1) + ". " + series.get(i).getName());
                                                        }

                                                        System.out.println(
                                                                "Enter the number of the series you want to play, or 0 to return to the previous menu:");
                                                        int serieNumber = sc.nextInt();
                                                        if (serieNumber == 0) {
                                                            break;
                                                        }

                                                        // Subtract 1 because arrays in Java start at 0
                                                        String serieName = series.get(serieNumber - 1).getName();

                                                        List<String> playbackLog = fm.playSerie(serieName);
                                                        for (String log : playbackLog) {
                                                            System.out.println(log);
                                                            if (log.contains("Do you want to play the next episode?")) {
                                                                int answer = sc.nextInt();
                                                                if (answer == 0) {
                                                                    break;
                                                                }
                                                            }
                                                        }

                                                        System.out.println(
                                                                "Do you want to play another series? (1 for yes, 0 for no)");
                                                        playAnotherSerie = sc.nextInt() == 1;
                                                    } while (playAnotherSerie);

                                                    break;
                                                case 9:

                                                    System.out.println("Enter the name of the playlist to delete:");
                                                    String playlistToDelete = sc.nextLine();

                                                    // Buscar la playlist por nombre
                                                    Playlist playlistObjectToDelete = userControl
                                                            .getPlaylistByName(email, playlistToDelete);

                                                    if (playlistObjectToDelete != null) {
                                                        // Eliminar la lista de reproducción
                                                        userControl.deletePlaylist(email);
                                                        System.out.println("Playlist deleted successfully.");
                                                    } else {
                                                        System.out.println(
                                                                "Playlist not found. Please enter a valid playlist name.");
                                                    }
                                                    break;

                                                case 0:
                                                    System.out.println("Going back to the previous menu.");
                                                    break;
                                                default:
                                                    System.out.println("Select a valid option");
                                                    break;
                                            }
                                        } while (playlistSubMenuOption != 9);
                                        break;
                                    case 3:
                                        int opc4 = 0;
                                        int selectedSeries = 0;
                                        do {
                                            System.out.println("Search for Series");
                                            System.out.println(
                                                    "Select the series you want to view or enter 0 to return to the previous menu");
                                            System.out.println("Press 0 if you want to return to the previous menu");
                                            src.showSeriesTittles();

                                            try {
                                                selectedSeries = sc.nextInt();
                                            } catch (InputMismatchException e) {
                                                System.out.println("Input a valid option");
                                                sc.nextLine();
                                                continue;
                                            }

                                            if (selectedSeries == 0) {
                                                break; // If the user selects 0, break the loop and go back to the
                                                       // previous menu
                                            }

                                            // Add logic to display series details based on selectedSeries
                                            Series selectedSeriesObject = src.getSeries().get(selectedSeries - 1);
                                            System.out.println(selectedSeriesObject.toString());

                                            System.out.println(
                                                    "Do you want to search for another series? (0 for no, 1 for yes)");

                                            try {
                                                opc4 = sc.nextInt();
                                                sc.nextLine();
                                            } catch (InputMismatchException e) {
                                                System.out.println("Input a valid option");
                                                sc.nextLine();
                                                opc4 = 1;
                                            }
                                        } while (opc4 != 0);
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
                                series = fm.getSeries();
                                for (Series serie : series) {
                                    System.out.println("- " + serie);
                                }

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