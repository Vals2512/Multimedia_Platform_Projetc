package co.uptc.edu.model;

import java.util.ArrayList;

import co.uptc.edu.model.multimediaModel.Playlist;

public class User {
    private String email,password,id,firstName,lastName,role;
    private ArrayList<Playlist> playlists;
    
    
   
    public User(String email, String password, String id, String firstName, String lastName,String role) {
        this.playlists=new ArrayList<>();
        this.role=role;
        this.email = email;
        this.password = password;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    } 
        public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
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
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}