package co.uptc.edu.control;
import co.uptc.edu.model.Admin;

public class AdminControl {
    private static final String usernameAdmin = "admin";
    private static final String passwordAdmin = "admin123";

    public boolean loginAdmin(String username, String password) {
        Admin admin = new Admin(usernameAdmin, passwordAdmin);
        return username.equals(admin.getUsername()) && password.equals(admin.getPassword());
    }
}
