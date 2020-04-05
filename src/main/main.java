package main;

import user.CalendarManager;
import user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class main {
    //login, register, logout
    // =============== Constants ===============
    private static final List<String> USER_OPTIONS = Arrays.asList(
            "Logout", "Calendar", "Settings", "Exit");
    private static final List<String> MENU = Arrays.asList("Create", "View",
            "Search", "Delete event.Event", "Leave");

    // =============== Local Storage ===============
    public static User loggedInUser = null;
    public static CalendarManager currCalendar = null;

    private static ArrayList<User> allUsers = new ArrayList<>();

    private static void loginOrRegister(Scanner scanner) {
        boolean success = false;

        // Do not let user pass until they've successfully logged in or registered.
        while (!success || loggedInUser == null) {
            System.out.println("Please LogIn / Register / Exit");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("login")) {
                // login
                success = login(scanner);
            } else if (input.equalsIgnoreCase("register")) {
                // register and save new user list
                success = register(scanner);
                if (success) {
                    if (!User.writeUserData(allUsers)) {
                        System.out.println("Warning: Failed to write data. " +
                                "This could cause data losses.");
                    }
                }
            }else if(input.equalsIgnoreCase("exit")){
                System.exit(1);
            } else{
                System.out.println("Invalid Input! Please input [login] / [register] / [Exit]");
            }
        }

        System.out.println("Login Success! Welcome, " + loggedInUser.getUsername());
    }

    private static boolean login(Scanner scanner) {
        System.out.println("Please input User Name");
        String userName = scanner.nextLine().trim();
        System.out.println("Please input Password");
        String password = scanner.nextLine().trim();

        // There might be cases where two users have the same name but
        // different uid. We have to check all of them.
        boolean foundUser = false, loggedIn = false;
        for (User currUser : allUsers) {
            if (currUser.getUsername().equals(userName)) {
                foundUser = true;
                if (currUser.getPassword().equals(password)) {
                    loggedInUser = currUser;
                    loggedIn = true;
                }
            }
        }

        if (!loggedIn) {
            if (!foundUser) {
                System.out.println(userName + " not found. Please try again.");
            } else {
                System.out.println("Invalid Password. Please try again.");
            }
            return false;
        }
        return true;
    }
    private static boolean register(Scanner scanner) {
        System.out.println("Please input Email");
        String email = scanner.nextLine().trim();
        if (email.contains(",")) {
            System.out.println("Email cannot contain comma [,]!");
            return false;
        }

        System.out.println("Please input User Name");
        String userName = scanner.nextLine().trim();
        if (userName.contains(",")) {
            System.out.println("User Name cannot contain comma [,]!");
            return false;
        }

        System.out.println("Please input Password");
        String password = scanner.nextLine().trim();
        if (password.contains(",")) {
            System.out.println("Password cannot contain comma [,]!");
            return false;
        }

        int uidNum = allUsers.size() + 1;
        String uid = User.USER_UID_PRETEXT + uidNum;

        loggedInUser = new User(email, userName, password, uid);
        allUsers.add(loggedInUser);
        return true;
    }

    /**
     * Log out the current user
     */
    private static void logOut() {
        loggedInUser = null;
        currCalendar = null;
    }


    public static void choosingCanlender(User user){
        ReadWriteCSV.readCalendarData("./data/"+
                loggedInUser.getUid()+"_calendarData.txt", loggedInUser);
        if(user.calendars.size() == 0){
            System.out.println("You haven't build any calender yet!");
            System.out.println("Do you want to build one? Type Y/N please.");
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            if(userInput.equalsIgnoreCase("y")){
                buildCanlender(loggedInUser);
                currCalendar = loggedInUser.calendars.get(0);
                Menu menu = new Menu(currCalendar, loggedInUser);
                menu.MenuOption();
            }
            else{
                mainmenu();
            }
        }
        else{
            System.out.println("Please choose the calender you want to enter by typing the number: ");
            for(int i = 0; i < user.calendars.size(); i++){
                int n = i+1;
                System.out.println("Calender" + n);
            }
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            int num = parseInt(userInput) - 1;
            CalendarManager calender = user.calendars.get(num);
            currCalendar = calender;
            Menu menu = new Menu(currCalendar, loggedInUser);
            menu.MenuOption();
        }
    }

    public static void buildCanlender(User user){
        int size = user.calendars.size();
        String str = Integer.toString(size);
        CalendarManager newCalendar = new CalendarManager("1");
        user.calendars.add(newCalendar);
    }

    public static void mainmenu(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose form the following options:");
        System.out.println(USER_OPTIONS.toString());
        String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "logout":
                logOut();
                loginOrRegister(scanner);
            case "calendar":
                choosingCanlender(loggedInUser);
            case "exit":
                logOut();
                System.exit(0);
            default:
                // We don't need to do anything here
        }
    }
    public static void main(String[] args) {
        // On startup, read all the stored Users / Events
        System.out.println("Initializing ...");
        allUsers = User.readUserData();
        System.out.println("Initialization Done");
        String userInput = "";
        while (!userInput.equalsIgnoreCase("exit")) {
            Scanner scanner = new Scanner(System.in);

            // 1. Prompt user for Login / Register
            if (loggedInUser == null) {
                // DEBUG: if we input exit here the program won't exit
                // will look at this another time..
                loginOrRegister(scanner);
            }

            // 2. If the user is already logged in, display the various options.
            mainmenu();

        }
    }

}


