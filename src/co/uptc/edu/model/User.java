package co.uptc.edu.model;

import java.util.ArrayList;

public class User {
    private String email;
    private String password;
    private ArrayList<Playlist> playlists;

    public User() {
    playlists=new ArrayList<>();
    }




    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return "Usuario{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", playlists=" + playlists +
                '}';
    }
}