package co.edu.uptc.model;
import java.util.ArrayList;
import java.util.List;

public class Season {
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
}
