package co.uptc.edu.model;

import java.util.ArrayList;

public class Playlist {
    private String name;

    public ArrayList<Object> getContent() {
        return content;
    }

    private ArrayList<Object> content;

    public Playlist() {
    }

    public Playlist(String name, ArrayList<Object> content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "name='" + name + '\'' +
                ", content=" + content +
                '}';
    }
}
