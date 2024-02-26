package co.edu.uptc.model;
import java.util.ArrayList;
import java.util.List;

public class Season {
    private String name;
    private List<Chapter> chapters;

    

    public Season() {
        this.chapters = new ArrayList<>();
    }

    public void addChapters(Chapter chapter) {
        this.chapters.add(chapter);
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "Season: " + name + ", Chapters: " + chapters;
    }
}