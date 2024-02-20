package co.edu.uptc.model;

public class Chapter {
    private String name;
    private int duration;  //en minutos


    public Chapter(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    
    public String getName() {
        return name;
    }



    public int getDuration() {
        return duration;
    }


    
    @Override
    public String toString() {
        return "name: " + name + ", duration: " + duration + " minutes";
    }
}