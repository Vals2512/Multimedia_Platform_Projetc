package co.uptc.edu.model;

import java.util.ArrayList;

public class Usuario {
    private String email;

    public Usuario() {
    }

    private String password;
    private ArrayList<Playlist> playlists;

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public Usuario(String email, String password) {
        playlists=new ArrayList<>();
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


}