package co.edu.uptc.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;

import co.edu.uptc.model.Movies;
import co.edu.uptc.model.Series;
import co.edu.uptc.model.User;
import co.edu.uptc.util.FileManager;
import com.google.gson.reflect.TypeToken;

public class UserControl {
    private ArrayList<User> users;
    private final static String FILE="users";
    private FileManager fm;
    public UserControl() {
        users = new ArrayList<>();
        fm=new FileManager();
        fm.createFile(FILE);
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

    public boolean addUser(User user, String passwordConfirmation) {
        if (searchUser(user.getEmail()) == -1 && user.getPassword().equals(passwordConfirmation)) {
            users.add(user);
            Type type= new TypeToken<ArrayList<User>>(){}.getType();
            fm.saveObjectToFile(FILE,users,type);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String email) {
        int userIndex = searchUser(email);
        if (userIndex != -1) {
            users.remove(userIndex);
            return true;
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
}