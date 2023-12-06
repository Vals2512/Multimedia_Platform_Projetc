package co.uptc.edu.model;

public class Chapter {
    private String name;
    private int duration;


    public Chapter(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getDuration() {
        return duration;
    }


    public void setDuration(int duration) {
        this.duration = duration;
    } 
    
    @Override
    public String toString() {
        return "Chapter{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}