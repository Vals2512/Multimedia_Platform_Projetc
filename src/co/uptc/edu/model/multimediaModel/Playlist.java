package co.uptc.edu.model.multimediaModel;

import java.util.ArrayList;

public class Playlist {
    private String name;
    private ArrayList<Object> content;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Object> getContent() {
        return content;
    }
    public void setContent(ArrayList<Object> content) {
        this.content = content;
    }
    
    @Override
    public String toString() {
        return "Playlist [name=" + name + ", content=" + content + "]";
    }
    
}
