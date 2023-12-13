package co.uptc.edu.model;

/**
 * The MultimediaAction abstract class defines common actions that can be
 * performed on multimedia.
 * Classes extending this abstract class must implement these actions based on
 * their specific multimedia type.
 */

public abstract class MultimediaAction {
    /**
     * Abstract method to play the multimedia.
     * Subclasses must implement this method to define the play.
     */
    public abstract void play();

    /**
     * Abstract method to restart the multimedia.
     * Subclasses must implement this method to define the restart playing.
     */
    public abstract void restart();

    /**
     * Abstract method to continue playing the multimedia.
     * Subclasses must implement this method to define the continue playing.
     */
    public abstract void continuePlaying();

    /**
     * Abstract method to stop playing the multimedia.
     * Subclasses must implement this method to define the stop playing.
     */
    public abstract void stopPlay();
}
