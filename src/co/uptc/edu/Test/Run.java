package co.uptc.edu.Test;

import java.util.InputMismatchException;
import java.util.Scanner;

import co.uptc.edu.Utilities.LoginUtilities;
import co.uptc.edu.control.AdminControl;
import co.uptc.edu.control.MoviesControl;
import co.uptc.edu.control.SeriesControl;
import co.uptc.edu.control.UserControl;
import co.uptc.edu.model.*;


public class Run {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserControl usuarioControl = new UserControl();
        MoviesControl mc = new MoviesControl();
        SeriesControl src = new SeriesControl();
        LoginUtilities lu=new LoginUtilities();
        String email = "";
        String password = "";

        int opc = 0;
        do {
            System.out.println("Bienvenido a la plataforma multimedia.");
            System.out.println("Seleccione una opción: ");
            System.out.println("1. Ingresar como admin");
            System.out.println("2. Registrarse");
            System.out.println("3. Entrar como usuario registrado");
            System.out.println("4. Ingresar como visitante");
            System.out.println("0. Salir");
            try {
                opc = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese una opción válida");
                sc.next(); // descarta la entrada incorrecta
                continue;
            }
            switch (opc) {
                case 1:
                    boolean leave = false; // Variable para volver al menpu principal cuando se vuelve true
                    int tries = 0;
                    while (!leave && tries < 3) {
                        System.out.println("Inicio de sesión de administrador");
                        System.out.print("Ingrese su usuario: ");
                        String adminEmail = sc.next();
                        System.out.print("Ingrese su contraseña: ");
                        String adminPassword = sc.next();
                        AdminControl adc = new AdminControl();
                        if (adc.loginAdminBackup(adminEmail, adminPassword)) {
                            System.out.println("Inicio de sesión exitoso");
                            tries = 0;
                            int opc1 = 0;
                            do {

                                System.out.println("""
                                        Bienvenido usuario administrador
                                        -----menu de usuarios-----------
                                        1. Buscar usuario registrado
                                        2. Eliminar usuario registrado
                                        -------menu de contenido--------
                                        3. Añadir pelicula
                                        4. Añadir serie
                                        5. Eliminar serie
                                        -------menu de administrador-----
                                        6. Añadir  usuario administrador
                                        
                                        0. Salir
                                        """);
                                try {

                                    opc1 = sc.nextInt();
                                    sc.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Por favor, ingrese una opción válida");
                                    sc.next(); // descarta la entrada incorrecta
                                    continue;
                                }
                                switch (opc1) {
                                    case 1:
                                        System.out.println("Ingrese el correo del usuario que desea buscar: ");
                                        email = sc.next();

                                        if (usuarioControl.getUser(email) != null) {
                                            System.out.println("Usuario encontrado");
                                            System.out.println("Correo: " + usuarioControl.getUser(email).getEmail());
                                            System.out.println("Contraseña: " + usuarioControl.getUser(email).getPassword());
                                        } else {
                                            System.out.println("Usuario no encontrado");
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Ingrese el correo del usuario que desea eliminar: ");
                                        email = sc.next();
                                        if (usuarioControl.deleteUser(email)) {
                                            System.out.println("Usuario eliminado exitosamente");
                                        } else {
                                            System.out.println("Error al eliminar el usuario");
                                        }
                                        break;

                                    case 3:
                                        System.out.println("Ingrese el id interno de la pelicula");
                                        String id= sc.nextLine();
                                        System.out.println("Ingrese el título de la película que desea añadir: ");
                                        String tittle = sc.nextLine();
                                        System.out.println("Ingrese la categoría de la película que desea añadir: ");
                                        String category = sc.nextLine();
                                        System.out.println("Ingrese los detalles de la película que desea añadir: ");
                                        String details = sc.nextLine();
                                        System.out.println("Ingrese el año de lanzamiento de la película que desea añadir: ");
                                        int releaseYear = sc.nextInt();
                                        System.out.println("Ingrese la duración de la película que desea añadir (en minutos): ");
                                        int duration = sc.nextInt();
                                        if (mc.addMovie(new Movies(id,tittle, category, details, releaseYear, duration))) {
                                            System.out.println("Película añadida exitosamente");
                                        } else {
                                            System.out.println("Error al añadir la película");
                                        }
                                    break;
                                    case 4:
                                        System.out.println("Ingrese el id interno de la serie");
                                        id = sc.nextLine();
                                        System.out.println("Ingrese el título de la serie que desea añadir: ");
                                        tittle = sc.nextLine();
                                        System.out.println("Ingrese la categoría de la serie que desea añadir: ");
                                        category = sc.nextLine();
                                        System.out.println("Ingrese los detalles de la serie que desea añadir: ");
                                        details = sc.nextLine();
                                        System.out.println("Ingrese el año de lanzamiento de la serie que desea añadir: ");
                                        releaseYear = sc.nextInt();
                                        System.out.println("Ingrese la cantidad de temporadas de la serie que desea añadir: ");
                                        int seasons = sc.nextInt();

                                        Series newSerie = new Series(id,tittle, category, details, releaseYear, seasons);
                                        System.out.println("Ingrese la cantidad de capítulos:");
                                        int cantidadCapitulos = sc.nextInt();
                                        sc.nextLine();  // Consumir la nueva línea

                                        for (int i = 1; i <= cantidadCapitulos; i++) {
                                            System.out.println("Ingrese el nombre del capítulo " + i + ":");
                                            String nombreCapitulo = sc.nextLine();

                                            System.out.println("Ingrese la duración del capítulo " + i + " (en minutos):");
                                            int duracionCapitulo = sc.nextInt();
                                            sc.nextLine();  // Consumir la nueva línea

                                            Chapter capitulo = new Chapter(nombreCapitulo, duracionCapitulo);
                                            newSerie.addChapter(capitulo);
                                        }

                                        if (src.addSerie(newSerie)) {  // Aquí está la corrección
                                            System.out.println("Serie añadida exitosamente");
                                        } else {
                                            System.out.println("Error al añadir la serie");
                                        }
                                    break;
                                    case 5:
                                        int opc2 = 0;
                                        do {
                                            System.out.println(src.showSeries());
                                            System.out.println("Ingrese el título de la serie que quiere remover");
                                            String title = sc.nextLine();

                                            if (src.deleteSeries(title)) {
                                                System.out.println("Serie eliminada exitosamente");
                                            } else {
                                                System.out.println("Serie no encontrada");
                                            }

                                            try {
                                                do {
                                                    System.out.println("¿Desea repetir la acción?\n1. Sí\n2. No");
                                                    opc2 = sc.nextInt();
                                                    sc.nextLine();

                                                    if (opc2 != 1 && opc2 != 2) {
                                                        System.out.println("Opción no válida");
                                                    }
                                                } while (opc2 != 1 && opc2 != 2);
                                            } catch (InputMismatchException e) {
                                                System.out.println("Opción no válida");
                                                sc.nextLine(); // Limpiar el buffer de entrada
                                            }

                                        } while (opc2 != 2);
                                         break;

                                    case 6:
                                        System.out.println("Agregacion de administrador\ningrese el nickname del usuario");
                                        String name=sc.nextLine();
                                        System.out.println("ingrese el correo");
                                        email = sc.next();
                                        while (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                                            System.out.println("Correo inválido. Por favor, ingrese un correo válido: ");
                                            email = sc.next();
                                        }
                                        System.out.print("Ingrese su contraseña: ");
                                        password = sc.next();
                                        while (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")) { // Linea para validar que la
                                            // contraseña contenga la
                                            // seguridad necesaria
                                            System.out.println(
                                                    "Contraseña inválida. Por favor, ingrese una contraseña que tenga al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número: ");
                                            password = sc.next();
                                        }
                                        String passwordConfirmation = "";
                                        do {
                                            System.out.print("Confirme su contraseña: ");
                                            passwordConfirmation = sc.next();
                                            if (!password.equals(passwordConfirmation)) {
                                                System.out.println("Las contraseñas no coinciden. Por favor, intente de nuevo.");
                                            }
                                        } while (!password.equals(passwordConfirmation));
                                        boolean passwordBoolean=password.equals(passwordConfirmation);
                                        if(adc.addAdmin(name,password,email,passwordBoolean)){
                                            System.out.println("administrador añadido");
                                        }else{
                                            System.out.println("administrador no añadido");
                                        }
                                        break;
                                    case 0:
                                        System.out.println("Saliendo.");
                                        leave = true;
                                        break;
                                    default:
                                        System.out.println("Ingrese una opción válida.");
                                        break;
                                }
                            } while (!leave && opc1 != 5);
                        } else {
                            tries++;
                            System.out.println("Error al iniciar sesión. Intento " + tries + " de 3");
                        }
                    }
                    if (tries == 3) {
                        System.out.println("Ha agotado sus intentos. Volviendo al menú principal.");
                        break;
                    }
                    break;

                case 2:
                    System.out.println("Registro de usuario");
                    System.out.print("Ingrese su correo: ");
                    email = sc.next();
                    while (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) { // Linea para validar que el
                                                                                      // correo contenga la seguridad
                                                                                      // necesaria
                        System.out.println("Correo inválido. Por favor, ingrese un correo válido: ");
                        email = sc.next();
                    }
                    System.out.print("Ingrese su contraseña: ");
                    password = sc.next();
                    while (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")) { // Linea para validar que la
                                                                                            // contraseña contenga la
                                                                                            // seguridad necesaria
                        System.out.println(
                                "Contraseña inválida. Por favor, ingrese una contraseña que tenga al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número: ");
                        password = sc.next();
                    }
                    String passwordConfirmation = "";
                    do {
                        System.out.print("Confirme su contraseña: ");
                        passwordConfirmation = sc.next();
                        if (!password.equals(passwordConfirmation)) {
                            System.out.println("Las contraseñas no coinciden. Por favor, intente de nuevo.");
                        }
                    } while (!password.equals(passwordConfirmation));

                    if (usuarioControl.addUser(new User(email, password), passwordConfirmation)) { // Se añade al
                                                                                                      // arraylist de
                                                                                                      // usuarios
                                                                                                      // validando que
                                                                                                      // los datos
                                                                                                      // coincidan
                        System.out.println("Usuario registrado exitosamente");
                    } else {
                        System.out.println("Error al registrar el usuario");
                    }
                    break;

                case 3:
                    String continuar = "";
                    do {
                        System.out.println("Inicio de sesión");
                        System.out.print("Ingrese su correo: ");
                        email = sc.next();
                        System.out.print("Ingrese su contraseña: ");
                        password = sc.next();

                        if (usuarioControl.login(email, password)) {
                            System.out.println("Inicio de sesión exitoso");
                            int opc2=0;
                            do {
                                System.out.println("Bienvenido Usuario");
                                System.out.println("1. Buscar Pelicula");
                                System.out.println("2. Mostrar Peliculas Disponibles");
                                System.out.println("3. Volver");
                                try {
                                    opc2=sc.nextInt();
                                    sc.nextLine();
                                } catch (InputMismatchException e) {
                                    System.out.println("Por favor, ingrese una opción válida");
                                    sc.next(); // descarta la entrada incorrecta
                                    continue;
                                }
                                switch (opc2) {
                                    case 1:

                                        System.out.println("Ingrese el título de la película que desea buscar: ");
                                        String tittle = sc.nextLine();
                                        Multimedia movie = mc.getMovieTittle(tittle);
                                        if (movie != null) {
                                            System.out.println(movie.toString());
                                        } else {
                                            System.out.println("Película no encontrada");
                                        }
                                    break;
                                    case 2:

                                        break;
                                    default:
                                    System.out.println("Seleccione una opci+on válida");
                                    break;
                                }
                                
                            } while (opc2!=3);



                            break;
                        } else {
                            System.out.println("Error al iniciar sesión");
                        }

                        System.out.println("¿Desea intentarlo de nuevo? (s/n)");
                        continuar = sc.next();
                    } while (continuar.equalsIgnoreCase("s"));

                    break;

                case 4:

                    break;

                case 0:
                    System.out.println("Gracias por usar la plataforma multimedia. Saliendo.");
                    break;

                default:
                    System.out.println("Ingrese una opción válida. Intente nuevamente");
                    break;
            }
        } while (opc != 0);
        sc.close();
    }
}