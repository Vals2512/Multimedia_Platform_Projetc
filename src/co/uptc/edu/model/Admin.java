package co.uptc.edu.model;

/**
 * The Admin class represents an administrator in the platform.
 */
public class Admin {
    private String username, password = "", email;

    /**
     * Constructor for the Admin class.
     *
     * @param username Username of the administrator.
     * @param password Password of the administrator.
     * @param email    Email address of the administrator.
     */
    public Admin(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Gets the email address of the administrator.
     *
     * @return Email address of the administrator.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the administrator.
     *
     * @param email Email address of the administrator.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the username of the administrator.
     *
     * @return Username of the administrator.
     */

    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the administrator.
     *
     * @param username Username of the administrator.
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the administrator.
     *
     * @return Password of the administrator.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the administrator.
     *
     * @param password Password of the administrator.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
