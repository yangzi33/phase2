package main;

import user.CalendarManager;
import user.User;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public CalendarManager calendar;
    public User user;
    private static final List<String> MENU = Arrays.asList("Create", "View",
            "Search", "Delete", "Leave");

    public Menu(CalendarManager c, User u){
        calendar = c;
        user = u;
    }

    public void MenuOption(){
        System.out.println("What do you want to do:");
        System.out.println(MENU.toString());
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        Creating create = new Creating(calendar);
        Viewing view = new Viewing(calendar);
        switch (userInput.toLowerCase()){
            case "create":
                create.creatingProcedure();
                MenuOption();
            case "view":
                view.viewProcedure();
                MenuOption();
            case "search":
            case"delete":
            case "leave":
                int num = user.calendars.indexOf(calendar)+1;
                ReadWriteCSV.writeCalenderData(user,"./data/"+
                                user.getUid()+ "_calendarData.txt");
                main.mainmenu();
            default:
                MenuOption();


        }

    }
}
