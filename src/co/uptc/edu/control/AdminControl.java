package co.uptc.edu.control;
import co.uptc.edu.model.Admin;

import java.util.ArrayList;

public class AdminControl {
    private static final String usernameAdmin = "admin";
    private static final String passwordAdmin = "admin123";
    private ArrayList<Admin> admins;

    public AdminControl() {
        admins=new ArrayList<>();
    }

    public boolean loginAdminBackup(
            String username, String password) {
        Admin admin = new Admin(usernameAdmin, passwordAdmin);
        return username.equals(admin.getUsername()) && password.equals(admin.getPassword());
    }
    public boolean addAdmin(String name,String password,boolean matches){
        if(matches){

        }
        return false;
    }
}
