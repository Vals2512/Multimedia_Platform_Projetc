package co.edu.uptc.model;


import com.google.gson.annotations.SerializedName;

/**
 * The User class represents a user in the platform
 */
public class User {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("playlist")
    private Playlist playlist;
    private Plan plan;
    private Payment payment;

    public User() {
        playlist=new Playlist();
    }

    /**
     * Sets the user's playlist.
     *
     * @param playlists New playlist for the user.
     */
    public void setPlaylist(Playlist playlists) {
        this.playlist = playlists;
    }

    /**
     * Gets the user's playlist.
     *
     * @return Playlist associated with the user.
     */
    public Playlist getPlaylist() {
        return playlist;
    }

    /**
     * Constructor for the User class.
     *
     * @param email    User's email.
     * @param password User's password.
     */

public User(String email, String password, Plan plan, Payment payment) {
        this.email = email;
        this.password = password;
        this.plan = plan;
        this.payment = payment;
        this.playlist=new Playlist();
    }

    /**
     * Gets the user's email.
     *
     * @return User's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email.
     *
     * @param email New email for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's password.
     *
     * @return User's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password New password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return A string representation of the User object.
     */
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", playlist=" + playlist +
                '}';
    }
}