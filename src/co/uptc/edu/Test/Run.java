package co.uptc.edu.Test;

import java.util.InputMismatchException;
import java.util.Scanner;
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
                sc.next(); // discard incorrect entry
                continue;
            }
            switch (opc) {
                case 1:
                    boolean leave = false; //Variable to return to main menu when true
                    int tries = 0;
                    while (!leave && tries < 3) {
                        System.out.println("Admin login");
                        System.out.print("Input your username: ");
                        String adminEmail = sc.next();
                        System.out.print("IInput your password: ");
                        String adminPassword = sc.next();
                        AdminControl adc = new AdminControl();
                        if (adc.loginAdmin(adminEmail, adminPassword)) {
                            System.out.println("Successful login");
                            tries = 0;
                            int opc1 = 0;
                            do {
                                System.out.println("Welcome admin");
                                System.out.println("1. Search registered user");
                                System.out.println("2. Delete registered user");
                                System.out.println("3. Add movies");
                                System.out.println("4. Add series");
                                System.out.println("5. Leave");
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
                                        User user = userControl.getUser(email);
                                        if (user != null) {
                                            System.out.println("User found");
                                            System.out.println("Email: " + user.getEmail());
                                            System.out.println("Password: " + user.getPassword());
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
                                        int chaptersQuantity = sc.nextInt();
                                        sc.nextLine();  // Consume the new line

                                        for (int i = 1; i <= chaptersQuantity; i++) {
                                            System.out.println("Input chapter name: " + i + ":");
                                            String chapterName = sc.nextLine();

                                            System.out.println("Input the duration of the chapter " + i + " (in minutes):");
                                            int chapterDuration = sc.nextInt();
                                            sc.nextLine();  // Consume the new line

                                            Chapter chapter = new Chapter(chapterName, chapterDuration);
                                            newSerie.addChapter(chapter);
                                        }

                                        if (src.addSerie(newSerie)) {  // Aquí está la corrección
                                            System.out.println("Serie addedd successfully");
                                        } else {
                                            System.out.println("Error adding serie");
                                        }
                                    break;

                                    case 5:
                                        System.out.println("Coming out.");
                                        leave = true;
                                        break;
                                    default:
                                        System.out.println("Input a valid option.");
                                        break;
                                }
                            } while (!leave && opc1 != 5);
                        } else {
                            tries++;
                            System.out.println("Failed to login. Attempt " + tries + " of 3");
                        }
                    }
                    if (tries == 3) {
                        System.out.println("You have no more attempts. Returning to the main menu.");
                        break;
                    }
                    break;

                case 2:
                    System.out.println("User register");
                    System.out.print("Input your email: ");
                    email = sc.next();
                    while (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { // Line to validate that the email contains the necessary security
                        System.out.println("Invalid email. Please, input a valid email: ");
                        email = sc.next();
                    }
                    System.out.print("Input your password: ");
                    password = sc.next();
                    while (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")) { // Line to validate that the password contains the necessary security
                        System.out.println("Invalid password. Please enter a password that has at least 8 characters, one uppercase letter, one lowercase letter, and one number: ");
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

                    if (userControl.addUser(new User(email, password), passwordConfirmation)) { // It is added to the arraylist of users validating that the data matches
                        System.out.println("User registered successfully");
                    } else {
                        System.out.println("Error adding user");
                    }
                    break;

                case 3:
                    String continuar = "";
                    do {
                        System.out.println("Login");
                        System.out.print("Input your email: ");
                        email = sc.next();
                        System.out.print("Input your password: ");
                        password = sc.next();

                        if (userControl.login(email, password)) {
                            System.out.println("Login successful");
                            int opc2=0;
                            do {
                                System.out.println("Welcome user");
                                System.out.println("1. Search movie by title");
                                System.out.println("2. Return");
                                try {
                                    opc2=sc.nextInt();
                                    sc.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Please, input a valid option");
                                    sc.next(); // discard incorrect entry
                                    continue;
                                }
                                switch (opc2) {
                                    case 1:
                                        System.out.println("Input the tittle of the movie you want to search: ");
                                        String tittle = sc.nextLine();
                                        Multimedia movie = mc.getMovieTittle(tittle);
                                        if (movie != null) {
                                            System.out.println(movie.toString());
                                        } else {
                                            System.out.println("Movie not found");
                                        }
                                    break;
                                
                                    default:
                                    System.out.println("Please, input a valid option");
                                    break;
                                }
                                
                            } while (opc2!=2);



                            break;
                        } else {
                            System.out.println("Login error. Please, try again.");
                        }

                        System.out.println("Do you want to try again? (y/n)");
                        continuar = sc.next();
                    } while (continuar.equalsIgnoreCase("y"));

                    break;

                case 4:

                    break;

                case 5:
                    System.out.println("Thank you for using the multimedia platform. Coming out.");
                    break;

                default:
                    System.out.println("Input a valid option. Please, try again.");
                    break;
            }
        } while (opc != 5);
        sc.close();
    }
}