package co.uptc.edu.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The Series class represents a series as a type of multimedia.
 * It extends the Multimedia class and includes additional information such as
 * the number of seasons, chapters, and details about each chapter.
 */

public class Series extends Multimedia {

    private int seasons;
    private Multimedia multimedia;
    private List<Chapter> chapters;
    private String chapterName;
    private int chapterDuration;
    private Chapter chap;
    private int releaseYear;

    /**
     * Constructor for the Series class.
     *
     * @param title       Title of the series.
     * @param category    Category of the series.
     * @param details     Additional details about the series.
     * @param releaseYear Release year of the series.
     * @param seasons     Number of seasons in the series.
     */
    public Series(String tittle, String category, String details, int releaseYear, int seasons) {
        super(tittle, category, details, releaseYear);
        this.seasons = seasons;
        multimedia = new Multimedia(tittle, category, details, releaseYear);
        this.chapters = new ArrayList<>();
    }

    /**
     * Constructor for an individual chapter of the series.
     *
     * @param chapterName     Name of the chapter.
     * @param chapterDuration Duration of the chapter.
     */
    public Series(String chapterName, int chapterDuration) {
        this.chapterName = chapterName;
        this.chapterDuration = chapterDuration;
        chap = new Chapter(chapterName, chapterDuration);

    }

    /**
     * Gets the number of seasons in the series.
     *
     * @return Number of seasons in the series.
     */
    public int getSeasons() {
        return seasons;
    }

    /**
     * Sets the number of seasons in the series.
     *
     * @param seasons Number of seasons in the series.
     */
    public void setSeasons(int seasons) {
        this.seasons = seasons;
    }

    /**
     * Adds a chapter to the list of chapters in the series.
     *
     * @param chapter Chapter to be added.
     */
    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
    }

    /**
     * Gets the name of an individual chapter.
     *
     * @return Name of the chapter.
     */
    public String getChapterName() {
        return chapterName;
    }

    /**
     * Sets the name of an individual chapter.
     *
     * @param chapterName Name of the chapter.
     */
    public void setChapterName(String chapterName) {
        this.setChapterName(chapterName);
    }

    /**
     * Gets the duration of an individual chapter.
     *
     * @return Duration of the chapter.
     */
    public int getChapterDuration() {
        return chapterDuration;
    }

    /**
     * Sets the duration of an individual chapter.
     *
     * @param chapterDuration Duration of the chapter.
     */
    public void setChapterDuration(int chapterDuration) {
        this.setChapterDuration(chapterDuration);
    }

    /**
     * Returns a string representation of the Series object.
     *
     * @return A string representation of the Series object.
     */
    @Override
    public String toString() {
        return "Serie: " + multimedia + " seasons: " + seasons + chap;
    }

    /**
     * Gets the multimedia information associated with the series.
     *
     * @return Multimedia information of the series.
     */
    public Multimedia getMultimedia() {
        return multimedia;
    }

    /**
     * Sets the multimedia information associated with the series.
     *
     * @param multimedia Multimedia information of the series.
     */
    public void setMultimedia(Multimedia multimedia) {
        this.multimedia = multimedia;
    }

    /**
     * Gets the list of chapters in the series.
     *
     * @return List of chapters in the series.
     */
    public List<Chapter> getChapters() {
        return chapters;
    }

    /**
     * Sets the list of chapters in the series.
     *
     * @param chapters List of chapters in the series.
     */
    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    /**
     * Gets an individual chapter of the series.
     *
     * @return An individual chapter of the series.
     */
    public Chapter getChap() {
        return chap;
    }

    /**
     * Sets an individual chapter of the series.
     *
     * @param chap An individual chapter of the series.
     */
    public void setChap(Chapter chap) {
        this.chap = chap;
    }

    /**
     * Gets the release year of the series.
     *
     * @return Release year of the series.
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets the release year of the series.
     *
     * @param releaseYear Release year of the series.
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

}
