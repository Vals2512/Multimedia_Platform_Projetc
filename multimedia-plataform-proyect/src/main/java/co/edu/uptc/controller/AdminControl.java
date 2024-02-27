package co.edu.uptc.controller;
import co.edu.uptc.model.Admin;

import java.util.ArrayList;

public class AdminControl {
    private static final String usernameAdmin = "admin";
    private static final String passwordAdmin = "admin123",adminEmail="admin123@gmail.com";
    private ArrayList<Admin> admins;

    public AdminControl() {
        admins=new ArrayList<>();
    }

    public boolean loginAdminBackup(
            String username, String password) {
        Admin admin = new Admin(usernameAdmin, passwordAdmin,adminEmail);
        return username.equals(admin.getUsername()) && password.equals(admin.getPassword());
    }
    public boolean addAdmin(String name,String password,String email,boolean matches){
        Admin adminAdded=new Admin(name,password,email);
        if(matches){
        admins.add(adminAdded);
        return true;
        }
        return false;
    }


    public ArrayList<Admin> getAdmins() {
        return admins;
    }


}
