package co.uptc.edu.Test;
import java.util.Scanner;

import co.uptc.edu.control.UsuarioDao;
import co.uptc.edu.model.Usuario;

public class Run {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UsuarioDao usuarioDao = new UsuarioDao();

        int opc=0;
        do {
        System.out.println("Bienvenido a la plataforma multimedia.");
        System.out.println("Seleccione una opción: ");
        System.out.println("1. Ingresar como admin");
        System.out.println("2. Registrarse");
        System.out.println("3. Entrar como usuario registrado");
        System.out.println("4. Ingresar como visitante");
        System.out.println("5. Salir");
            opc=sc.nextInt();
            switch (opc) {
            case 2:
                System.out.println("Registro de usuario");
                System.out.print("Ingrese su correo: ");
                String email = sc.next();
            while (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                System.out.println("Correo inválido. Por favor, ingrese un correo válido: ");
                email = sc.next();
            }
                System.out.print("Ingrese su contraseña: ");
                String password = sc.next();
            while (!password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$")) {
                System.out.println("Contraseña inválida. Por favor, ingrese una contraseña que tenga al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número: ");
                password = sc.next();
            }
                System.out.print("Confirme su contraseña: ");
                String passwordConfirmation = sc.next();

            if (usuarioDao.addUser(new Usuario(email, password), passwordConfirmation)) {
                System.out.println("Usuario registrado exitosamente");
            } else {
                System.out.println("Error al registrar el usuario");
            }
            break;
            
            case 3:
            String continuar;
            do {
                System.out.println("Inicio de sesión");
                System.out.print("Ingrese su correo: ");
                email = sc.next();
                System.out.print("Ingrese su contraseña: ");
                password = sc.next();
        
                if (usuarioDao.login(email, password)) {
                    System.out.println("Inicio de sesión exitoso");
                    break;
                } else {
                    System.out.println("Error al iniciar sesión");
                }
        
                System.out.println("¿Desea intentarlo de nuevo? (s/n)");
                continuar = sc.next();
            } while (continuar.equalsIgnoreCase("s"));
            
            break;
            // case 5:
            //     System.out.println("Ingrese el correo del usuario que desea buscar: ");
            //     email = sc.next();
            //     Usuario user = usuarioDao.getUser(email);
            //     if (user != null) {
            //         System.out.println("Usuario encontrado");
            //         System.out.println("Correo: " + user.getEmail());
            //         System.out.println("Contraseña: " + user.getPassword());
            //     } else {
            //         System.out.println("Usuario no encontrado");
            //     }

            // break;
            case 5:

                System.out.println("Gracias por usar la plataforma multimedia. Saliendo.");
            break;
            default:
                System.out.println("Ingrese una opción válida. Intente nuevamente");
            break;
        }
        } while (opc!=5);
        
    }
}