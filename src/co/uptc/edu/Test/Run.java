package co.uptc.edu.Test;

import java.util.InputMismatchException;
import java.util.Scanner;
import co.uptc.edu.control.AdminControl;
import co.uptc.edu.control.MoviesControl;
import co.uptc.edu.control.UsuarioControl;
import co.uptc.edu.model.Multimedia;
import co.uptc.edu.model.Usuario;

public class Run {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UsuarioControl usuarioControl = new UsuarioControl();
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
            System.out.println("5. Salir");
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
                        if (adc.loginAdmin(adminEmail, adminPassword)) {
                            System.out.println("Inicio de sesión exitoso");
                            tries = 0;
                            int opc1 = 0;
                            do {
                                System.out.println("Bienvenido administrador");
                                System.out.println("1. Buscar usuario registrado");
                                System.out.println("2. Eliminar usuario registrado");
                                System.out.println("3. Salir");
                                try {
                                    opc1 = sc.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Por favor, ingrese una opción válida");
                                    sc.next(); // descarta la entrada incorrecta
                                    continue;
                                }
                                switch (opc1) {
                                    case 1:
                                        System.out.println("Ingrese el correo del usuario que desea buscar: ");
                                        email = sc.next();
                                        Usuario user = usuarioControl.getUser(email);
                                        if (user != null) {
                                            System.out.println("Usuario encontrado");
                                            System.out.println("Correo: " + user.getEmail());
                                            System.out.println("Contraseña: " + user.getPassword());
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
                                        System.out.println("Saliendo.");
                                        leave = true;
                                        break;
                                    default:
                                        System.out.println("Ingrese una opción válida.");
                                        break;
                                }
                            } while (!leave && opc1 != 3);
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

                    if (usuarioControl.addUser(new Usuario(email, password), passwordConfirmation)) { // Se añade al
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

                case 5:
                    System.out.println("Gracias por usar la plataforma multimedia. Saliendo.");
                    break;

                default:
                    System.out.println("Ingrese una opción válida. Intente nuevamente");
                    break;
            }
        } while (opc != 5);
        sc.close();
    }
}