package email;

/**
 * Contains methods for storing and retrieving login details
 */
public class Credentials {
    private String username;
    private String password;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Set the username and password for this object.
     *
     * @param username String to be set as the username
     * @param password char[] to be set as the password
     */
    public void setCredentials(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    /**
     * Returns the username
     *
     * @return Return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     *
     * @param username String to be set as the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the password
     *
     * @return Return the password as a String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password
     *
     * @param password char[] to be set as the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
