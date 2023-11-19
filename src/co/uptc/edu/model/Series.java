package co.uptc.edu.model;

public class Series extends Multimedia {

    private int seasons;
    private float chapterDuration;
    private String chapterTittle;

    public Series(String tittle, String category, String details, int releaseYear, int seasons, float chapterDuration, String chapterTittle) {
        super(tittle, category, details, releaseYear);
        this.seasons = seasons;
        this.chapterDuration = chapterDuration;
        this.chapterTittle = chapterTittle;
    }

    public int getSeasons() {
        return seasons;
    }

    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    public float getChapterDuration() {
        return chapterDuration;
    }

    public void setChapterDuration(float chapterDuration) {
        this.chapterDuration = chapterDuration;
    }

    public String getChapterTittle() {
        return chapterTittle;
    }

    public void setChapterTittle(String chapterTittle) {
        this.chapterTittle = chapterTittle;
    }
    

    @Override
    public String toString() {
        return "Series [seasons=" + seasons + ", chapterDuration=" + chapterDuration + "]";
    }

}
