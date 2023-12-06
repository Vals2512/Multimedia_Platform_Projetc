package co.uptc.edu.Test;

import java.util.InputMismatchException;
import java.util.Scanner;

import co.uptc.edu.Utilities.LoginUtilities;
import co.uptc.edu.control.AdminControl;
import co.uptc.edu.control.MoviesControl;
import co.uptc.edu.control.SeriesControl;
import co.uptc.edu.control.UserControl;
import co.uptc.edu.model.Chapter;
import co.uptc.edu.model.Movies;
import co.uptc.edu.model.Multimedia;
import co.uptc.edu.model.Series;
import co.uptc.edu.model.User;

public class Run {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserControl userControl = new UserControl();
        MoviesControl mc = new MoviesControl();
        SeriesControl src = new SeriesControl();
        LoginUtilities lu=new LoginUtilities();
        String email = "";
        String password = "";

        int opc = 0;
        do {
            System.out.println("Welcome to the multimedy platform.");
            System.out.println("Select an option: ");
            System.out.println("1. Admin login");
            System.out.println("2. Register");
            System.out.println("3. Login as registered user");
            System.out.println("4. Login as visitor");
            System.out.println("5. Leave");
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

                                System.out.println("""
                                        Welcome admin user
                                        1. Search registered user
                                        2. Delete registered user
                                        3. Add movie
                                        4. Add series
                                        5. Add admin user
                                        0. Leave
                                        """);
                                try {

                                    opc1 = sc.nextInt();
                                    sc.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Please, input a valid option");
                                    sc.next(); // discard incorrect entry
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
                                        String tittle = sc.nextLine();
                                        System.out.println("Input the category of the movie you want to add: ");
                                        String category = sc.nextLine();
                                        System.out.println("Input the details of the movie you want to add: ");
                                        String details = sc.nextLine();
                                        System.out.println("Input the release year of the movie you want to add: ");
                                        int releaseYear = sc.nextInt();
                                        System.out.println("Input the duration of the movie you want to add (in minutes): ");
                                        int duration = sc.nextInt();
                                        if (mc.addMovie(new Movies(tittle, category, details, releaseYear, duration))) {
                                            System.out.println("Movie added successfully");
                                        } else {
                                            System.out.println("Error adding movie");
                                        }
                                    break;
                                    case 4:
                                        System.out.println("Input the tittle of the serie you want to add: ");
                                        tittle = sc.nextLine();
                                        System.out.println("Input the category of the serie you want to add: ");
                                        category = sc.nextLine();
                                        System.out.println("Input the details of the serie you want to add: ");
                                        details = sc.nextLine();
                                        System.out.println("Input the release year of the serie you want to add:: ");
                                        releaseYear = sc.nextInt();
                                        System.out.println("Input the quantity of seasons of the serie ypu want to add: ");
                                        int seasons = sc.nextInt();

                                        Series newSerie = new Series(tittle, category, details, releaseYear, seasons);
                                        System.out.println("Input the quantity of chapters:");
                                        int cantidadCapitulos = sc.nextInt();
                                        sc.nextLine();  // Consume the new line

                                        for (int i = 1; i <= cantidadCapitulos; i++) {
                                            System.out.println("Input chapter´s name: " + i + ":");
                                            String chapterName = sc.nextLine();

                                            System.out.println("Input the duration of the chapter " + i + " (in minutes):");
                                            int chapterLenght = sc.nextInt();
                                            sc.nextLine();  // Consume the new line

                                            Chapter chapter = new Chapter(chapterName, chapterLenght);
                                            newSerie.addChapter(chapter);
                                        }

                                        if (src.addSerie(newSerie)) {  // Here is the correction
                                            System.out.println("Serie addedd successfully");
                                        } else {
                                            System.out.println("Error adding serie");
                                        }
                                    break;
                                    case 5:
                                    System.out.println("Adding administrator\nEnter the user's nickname:");
                                    String name = sc.nextLine();
                                    System.out.println("Enter the email:");
                                    email = sc.next();
                                    while (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                                        System.out.println("Invalid email. Please enter a valid email:");
                                        email = sc.next();
                                    }
                                    System.out.print("Enter your password: ");
                                    password = sc.next();
                                    while (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")) {
                                        System.out.println("Invalid password. Please enter a password with at least 8 characters, one uppercase letter, one lowercase letter, and one number:");
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
                                    boolean passwordBoolean = password.equals(passwordConfirmation);
                                    if (adc.addAdmin(name, password, email, passwordBoolean)) {
                                        System.out.println("Administrator added.");
                                    } else {
                                        System.out.println("Administrator not added.");
                                    }
                                    break;
                                
                                case 0:
                                    System.out.println("Exiting.");
                                    leave = true;
                                    break;
                                
                                default:
                                    System.out.println("Enter a valid option.");
                                    break;
                                }
                                
                            } while (!leave && opc1 != 5);
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
                    while (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { // Line to validate that the email contains the necessary security
                        System.out.println("Invalid email. Please enter a valid email: ");
                        email = sc.next();
                    }
                    System.out.print("Enter your password: ");
                    password = sc.next();
                    while (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")) { // Line to validate that the password contains the necessary security
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
                
                    if (userControl.addUser(new User(email, password), passwordConfirmation)) { // Added to the array list of users, validating that the data matches
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
                            int opc2 = 0;
                        do {
                        System.out.println("Welcome User");
                        System.out.println("1. Search for Movie");
                        System.out.println("2. Show Available Movies");
                        System.out.println("3. Go Back");
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
                        String tittle = sc.nextLine();
                        Multimedia movie = mc.getMovieTittle(tittle);
                        if (movie != null) {
                            System.out.println(movie.toString());
                        } else {
                            System.out.println("Movie not found");
                        }
                        break;
                    case 2:

                        break;
                    default:
                        System.out.println("Select a valid option");
                        break;
                }

            } while (opc2 != 3);

                    break;
                } else {
                    System.out.println("Error logging in");
                }

                    System.out.println("Do you want to try again? (y/n)");
                    continueOption = sc.next();
                } while (continueOption.equalsIgnoreCase("y"));

                break;


                case 4:

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
}