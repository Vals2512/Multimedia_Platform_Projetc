package co.uptc.edu.control;

import java.util.ArrayList;

import co.uptc.edu.model.User;
import co.uptc.edu.utilities.UserUtilities;

public class UserControl {
    //arraylist Users
    private ArrayList<User> users;
    //new arraylist for administrator role users
    private ArrayList<User> admins;
    private UserUtilities uUtilities;
    public UserControl(){
        uUtilities=new UserUtilities();
        users = new ArrayList<>();
        admins=new ArrayList<>();
    }
    
    public int searchUser(String email){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return i;
            }   
        }
        return -1;
    }

    public boolean addUser(String email, String password, String id, String firstName, String lastName,String role, String passwordConfirmation){
        if (searchUser(email) == -1 && password.equals(passwordConfirmation)&& uUtilities.validPassword(passwordConfirmation)) {
          users.add(new User(email, passwordConfirmation, id, firstName, lastName, role));
           return true;
        }
        return false;
    }

    public boolean deleteUser(String email){
        int userIndex = searchUser(email);
        if (userIndex != -1) {
            users.remove(userIndex);
            return true;
        }
        return false;
    }

    public User getUser(String email){
        int userIndex = searchUser(email);
        if (userIndex != -1) {
            return users.get(userIndex);
        }
        return null;
    }

    public boolean login(String email, String password){
        User user = getUser(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
    public ArrayList<User> getUsers() {
        return users;
    }  
    public ArrayList<User> getAdmins() {
        return admins;
    }
}