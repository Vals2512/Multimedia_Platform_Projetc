package co.uptc.edu.model;

/**
 * The Chapter class represents an individual chapter of a series.
 * It includes information such as the name and duration of the chapter.
 */
public class Chapter {
    private String name;
    private int duration;

    /**
     * Constructor for the Chapter class.
     *
     * @param name     Name of the chapter.
     * @param duration Duration of the chapter.
     */
    public Chapter(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    /**
     * Gets the name of the chapter.
     *
     * @return Name of the chapter.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the chapter.
     *
     * @param name Name of the chapter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the duration of the chapter.
     *
     * @return Duration of the chapter.
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the duration of the chapter.
     *
     * @param duration Duration of the chapter.
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * Returns a string representation of the Chapter object.
     *
     * @return A string representation of the Chapter object.
     */
    @Override
    public String toString() {
        return "Chapter{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}