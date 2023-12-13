package co.uptc.edu.model;

/**
 * The Multimedia class represents a generic multimedia item with basic
 * information
 * such as title, category, details, and release year.
 */
public class Multimedia {

    private String tittle;
    private String category;
    private String details;
    private int releaseYear;

    /**
     * Default constructor for the Multimedia class.
     */
    public Multimedia() {
    }

    /**
     * Constructor for the Multimedia class with specified parameters.
     *
     * @param title       Title of the multimedia.
     * @param category    Category of the multimedia.
     * @param details     Additional details about the multimedia.
     * @param releaseYear Release year of the multimedia.
     */
    public Multimedia(String tittle, String category, String details, int releaseYear) {
        this.tittle = tittle;
        this.category = category;
        this.details = details;
        this.releaseYear = releaseYear;

    }

    /**
     * Gets the title of the multimedia.
     *
     * @return Title of the multimedia.
     */
    public String getTittle() {
        return tittle;
    }

    /**
     * Sets the title of the multimedia.
     *
     * @param title Title of the multimedia.
     */
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    /**
     * Gets the category of the multimedia.
     *
     * @return Category of the multimedia.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category of the multimedia.
     *
     * @param category Category of the multimedia.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the additional details about the multimedia.
     *
     * @return Details of the multimedia.
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets the additional details about the multimedia.
     *
     * @param details Details of the multimedia.
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Gets the release year of the multimedia.
     *
     * @return Release year of the multimedia.
     */
    public int getReleaseYear() {
        return releaseYear;
    }

    /**
     * Sets the release year of the multimedia.
     *
     * @param releaseYear Release year of the multimedia.
     */
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    /**
     * Returns a string representation of the Multimedia object.
     *
     * @return A string representation of the Multimedia object.
     */
    @Override
    public String toString() {
        return "Tittle: " + tittle + ", category: " + category + ", details: " + details + ", release year: "
                + releaseYear;
    }

}
