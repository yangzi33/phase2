package user;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserManager {

    private static String USER_DATA_FILE = "./data/userData.txt";

    /**
     * Reads the text user data and store them into the users list.
     *
     * @return - the list of all the users stored in the user data txt file.
     */
    public ArrayList<User> readUserData() {
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
    public boolean writeUserData(ArrayList<User> allUsers) {
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
}
