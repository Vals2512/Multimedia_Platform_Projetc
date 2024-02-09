package co.edu.uptc.model;

import java.util.ArrayList;
import java.util.List;

public class Series extends Multimedia {

    private int seasons;
    private Multimedia multimedia;
    private List<Chapter> chapters;
    private String chapterName;
    private int chapterDuration;
    private Chapter chap;
    private int releaseYear;

    
    
    public Series() {
    }

    public Series(String tittle, String details, int releaseYear) {
        super(tittle, details, releaseYear);
    }

    public Series(String tittle, List<Category> categories, String details, int releaseYear) {
        super(tittle, categories, details, releaseYear);
    }

    public Series(int seasons, Multimedia multimedia, List<Chapter> chapters, String chapterName, int chapterDuration,
            Chapter chap, int releaseYear) {
        this.seasons = seasons;
        this.multimedia = multimedia;
        this.chapters = chapters;
        this.chapterName = chapterName;
        this.chapterDuration = chapterDuration;
        this.chap = chap;
        this.releaseYear = releaseYear;
    }

    public Series(String tittle, String details, int releaseYear, int seasons, Multimedia multimedia,
            List<Chapter> chapters, String chapterName, int chapterDuration, Chapter chap, int releaseYear2) {
        super(tittle, details, releaseYear);
        this.seasons = seasons;
        this.multimedia = multimedia;
        this.chapters = chapters;
        this.chapterName = chapterName;
        this.chapterDuration = chapterDuration;
        this.chap = chap;
        releaseYear = releaseYear2;
    }

    public Series(String tittle, List<Category> categories, String details, int releaseYear, int seasons,
            Multimedia multimedia, List<Chapter> chapters, String chapterName, int chapterDuration, Chapter chap,
            int releaseYear2) {
        super(tittle, categories, details, releaseYear);
        this.seasons = seasons;
        this.multimedia = multimedia;
        this.chapters = chapters;
        this.chapterName = chapterName;
        this.chapterDuration = chapterDuration;
        this.chap = chap;
        releaseYear = releaseYear2;
    }

    public Series(String tittle, List<Category> categories, String details, int releaseYear, int seasons, int chapterDuration) {
        super(tittle, categories, details, releaseYear);
        this.seasons = seasons;
        multimedia = new Multimedia(tittle, categories, details, releaseYear);
        this.chapters = new ArrayList<>();
    }

    public Series(String tittle, String details, int releaseYear, int seasons, int chapterDuration) {
        super(tittle, details, releaseYear);
        this.seasons = seasons;
        multimedia = new Multimedia(tittle, details, releaseYear);
        this.chapters = new ArrayList<>();
    }

    public Series(String chapterName, int chapterDuration) {
        this.chapterName = chapterName;
        this.chapterDuration = chapterDuration;
        chap = new Chapter(chapterName, chapterDuration);

    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.setChapterName(chapterName);
    }

    public int getChapterDuration() {
        return chapterDuration;
    }

    public void setChapterDuration(int chapterDuration) {
        this.setChapterDuration(chapterDuration);
    }

    @Override
    public String toString() {
        return "Serie: " + multimedia + " seasons: " + seasons + chapterDuration;
    }

    public Multimedia getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(Multimedia multimedia) {
        this.multimedia = multimedia;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public Chapter getChap() {
        return chap;
    }

    public void setChap(Chapter chap) {
        this.chap = chap;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

}
