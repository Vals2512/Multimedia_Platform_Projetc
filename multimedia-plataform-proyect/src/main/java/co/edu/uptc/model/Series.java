package co.edu.uptc.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Series extends Multimedia {
    // private Multimedia multimedia;
    // private List<Chapter> chapters;
    // private String chapterName;
    // private int chapterDuration;
    // private Chapter chap;
    // private int releaseYear;
    // @SerializedName("name")
    private String name;
    // @SerializedName("seasons")
    private List<Season> seasons;

    
    
    public Series() {
    }

    public Series(String name){
        this.name = name;
        this.seasons = new ArrayList<>();
    }

    
    public void addSeason(Season season) {
        this.seasons.add(season);
    }
    
    public List<Season> getSeasons(){
        return this.seasons;
    }
    
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Series{" +
                "name='" + name + '\'' +
                ", seasons=" + seasons +
                '}';
    }
}
    // public Series(String tittle, String details, int releaseYear) {
    //     super(tittle, details, releaseYear);
    // }

    // public Series(String tittle, List<Category> categories, String details, int releaseYear) {
    //     super(tittle, categories, details, releaseYear);
    // }


    // public Series(String chapterName, int chapterDuration) {
    //     this.chapterName = chapterName;
    //     this.chapterDuration = chapterDuration;
    //     chap = new Chapter(chapterName, chapterDuration);

    // }

    // public void addChapter(Chapter chapter) {
    //     chapters.add(chapter);
    // }

    // public String getChapterName() {
    //     return chapterName;
    // }

    // public void setChapterName(String chapterName) {
    //     this.setChapterName(chapterName);
    // }

    // public int getChapterDuration() {
    //     return chapterDuration;
    // }

    // public void setChapterDuration(int chapterDuration) {
    //     this.setChapterDuration(chapterDuration);
    // }

    
    // public Multimedia getMultimedia() {
    //     return multimedia;
    // }

    // public void setMultimedia(Multimedia multimedia) {
    //     this.multimedia = multimedia;
    // }
    
    // public List<Chapter> getChapters() {
    //     return chapters;
    // }

    // public void setChapters(List<Chapter> chapters) {
    //     this.chapters = chapters;
    // }
    
    // public Chapter getChap() {
    //     return chap;
    // }
    
    // public void setChap(Chapter chap) {
    //     this.chap = chap;
    // }

    // public int getReleaseYear() {
    //     return releaseYear;
    // }

    // public void setReleaseYear(int releaseYear) {
    //     this.releaseYear = releaseYear;
    // }

