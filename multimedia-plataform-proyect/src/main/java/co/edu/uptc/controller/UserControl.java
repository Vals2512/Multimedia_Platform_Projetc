package co.edu.uptc.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;

import co.edu.uptc.model.*;
import co.edu.uptc.util.FileManager;
import com.google.gson.reflect.TypeToken;

public class UserControl {
    private ArrayList<User> users;
    private String email,password;
    private final static String FILE="users";
    private final static Type FILETYPE=new TypeToken<ArrayList<User>>(){}.getType();
    private FileManager fm;
    public UserControl() {
        users = new ArrayList<>();

        fm=new FileManager();
        users= fm.readFile(FILE,FILETYPE);if(users==null){
            fm.createFile(FILE);
        }

    }

    public int searchUser(String email) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return i;
            }
        }
        return -1;
    }

    public void addSeriesToPlaylist(String email, Series series) {
        searchUserObject(email).getPlaylist().getContent().add(series);
    }

    public void addMoviesToPlaylist(String email, Movies movies) {
        searchUserObject(email).getPlaylist().getContent().add(movies);
    }

    public void clearPlaylist(String email) {
        searchUserObject(email).getPlaylist().getContent().clear();
    }

    public User searchUserObject(String email) {
        User userFound = null;
        for (User uIt :
                users) {
            if (uIt.getEmail().equals(email)) {
                userFound = uIt;
            }
        }
        return userFound;
    }

    public void addUser(String email, String password, Plan plan, Payment payment) {
        User user=new User();

        user.setPassword(password);
        user.setEmail(email);
        // System.out.println(user);
        user.setPlan(plan);
        user.setPayment(payment);
        if (searchUser(user.getEmail()) == -1) {
            users.add(user);
            Type type= new TypeToken<ArrayList<User>>(){}.getType();

            fm.saveObjectToFile(FILE, users, type);
        }
    }

    public boolean deleteUser(String email) {
        int userIndex = searchUser(email);
        if (userIndex != -1) {
            users.remove(userIndex);
            return fm.saveObjectToFile("users", users, new TypeToken<ArrayList<User>>(){}.getType());

        }
        return false;
    }

    public User getUser(String email) {
        int userIndex = searchUser(email);
        if (userIndex != -1) {
            return users.get(userIndex);
        }
        return null;
    }

    public boolean login(String email, String password) {
        User user = getUser(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}