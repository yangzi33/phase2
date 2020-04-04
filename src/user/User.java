package user;

import event.EventManager;

public class User {

    public static String USER_UID_PRETEXT = "user";

    private String email;

    private String username;

    private String password;

    private String uid;

    public EventManager events = new EventManager();

    public User(String email, String username, String password, String uid) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.uid = uid;
    }

    /**
     * Changes the password for the user according to the user name and email
     *
     * @param email    - the user's email
     * @param username - the user name
     * @param password - the new password
     * @return - the message
     */
    public String changePassword(String email, String username, String password) {
        if (email.equals(this.email)) {
            if (username.equals(this.username)) {
                this.password = password;
                return "Password change success";
            } else {
                return "username is wrong";
            }
        } else {
            return "email is wrong";
        }
    }

    /**
     * Changes the user name according to the email
     *
     * @param email    - the user email
     * @param username - the new user name
     * @return - the message
     */
    public String changeUsername(String email, String username) {
        if (email.equals(this.email)) {
            this.username = username;
            return "Username change success";
        } else {
            return "email is wrong";
        }
    }

    /**
     * Gets the user's email address
     *
     * @return - the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the user's user name
     *
     * @return - the user's user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the user's password
     *
     * @return - the user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the user's uid
     *
     * @return - the user's uid
     */
    public String getUid() {
        return uid;
    }
}
