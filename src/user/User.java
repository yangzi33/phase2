package user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class User {
    public static String USER_UID_PRETEXT = "user";

    private static String USER_DATA_FILE =  "./data/userData.txt";

    private String email;

    private String username;

    private String password;

    private String uid;

    public ArrayList<CalendarManager> calendars = new ArrayList<>();

    public User(String email, String username, String password, String uid) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.uid = uid;
    }

    /**
     * Reads the text user data and store them into the users list.
     *
     * @return - the list of all the users stored in the user data txt file.
     */
    public static ArrayList<User> readUserData() {
        ArrayList<User> users = new ArrayList<>();
        File userFile = new File(USER_DATA_FILE);
        if (userFile.exists()) {
            try {
                Scanner scanner = new Scanner(userFile);
                while (scanner.hasNextLine()) {
                    String currLine = scanner.nextLine().trim();
                    String[] attributes = currLine.split(",");
                    if (attributes.length == 4) {
                        String email = attributes[0].trim();
                        String username = attributes[1].trim();
                        String password = attributes[2].trim();
                        String uid = attributes[3].trim();

                        User currUser = new User(email, username, password, uid);
                        users.add(currUser);
                    } else {
                        System.out.println("Invalid Line:");
                        System.out.println(currLine);
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("userData.txt file not found. No user is stored.");
        }
        return users;
    }

    /**
     * Writes the users list into the user data text file.
     *
     * @param allUsers - the list of all users to save into the user data txt file
     * @return - true if the write was successful, false otherwise.
     */
    public static boolean writeUserData(ArrayList<User> allUsers) {
        try {
            File userFile = new File(USER_DATA_FILE);
            if (!userFile.exists()) {
                if (!userFile.createNewFile()) {
                    return false;
                }
            }
            FileWriter writer = new FileWriter(userFile);
            for (User currUser : allUsers) {
                List<String> currLineLst = Arrays.asList(
                        currUser.getEmail(),
                        currUser.getUsername(),
                        currUser.getPassword(),
                        currUser.getUid()
                );

                writer.write(String.join(",", currLineLst) +
                        System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
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

