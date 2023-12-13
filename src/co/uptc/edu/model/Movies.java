package co.uptc.edu.model;

/**
 * The Movies class represents a movie as a type of multimedia.
 * It extends the Multimedia class and includes additional information
 * such as the duration of the movie.
 */
public class Movies extends Multimedia {

    private int duration;
    private Multimedia multimedia;

    /**
     * Default constructor for the Movies class.
     */
    public Movies() {

    }

    /**
     * Constructor for the Movies class with specified parameters.
     *
     * @param title       Title of the movie.
     * @param category    Category of the movie.
     * @param details     Additional details about the movie.
     * @param releaseYear Release year of the movie.
     * @param duration    Duration of the movie.
     */
    public Movies(String tittle, String category, String details, int releaseYear, int duration) {
        super(tittle, category, details, releaseYear);
        this.duration = duration;
        multimedia = new Multimedia(tittle, category, details, releaseYear);
    }

    /**
     * Gets the duration of the movie.
     *
     * @return Duration of the movie.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the movie.
     *
     * @param duration Duration of the movie.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Returns a string representation of the Movies object.
     *
     * @return A string representation of the Movies object.
     */
    @Override
    public String toString() {
        return multimedia + " duration:" + duration;
    }

}
