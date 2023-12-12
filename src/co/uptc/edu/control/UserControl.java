package co.uptc.edu.control;

import java.util.ArrayList;

import co.uptc.edu.model.User;

public class UserControl {
    private ArrayList<User> usuarios;

    public UserControl(){
        usuarios = new ArrayList<>();
    }
    
    public int searchUser(String email){
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getEmail().equals(email)) {
                return i;
            }   
        }
        return -1;
    }

    public boolean addUser(User usuario, String passwordConfirmation){
        if (searchUser(usuario.getEmail()) == -1 && usuario.getPassword().equals(passwordConfirmation)) {
            usuarios.add(usuario);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String email){
        int userIndex = searchUser(email);
        if (userIndex != -1) {
            usuarios.remove(userIndex);
            return true;
        }
        return false;
    }

    public User getUser(String email){
        int userIndex = searchUser(email);
        if (userIndex != -1) {
            return usuarios.get(userIndex);
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
}