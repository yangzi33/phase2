package main;

import alert.AlertManager;
import event.EventManager;
import event.Viewing;
import features.MemoManager;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class View {
    private static final List<String> View = Arrays.asList("ByTime", "ByMemo",
            "ByTag", "ByAlert", "ViewallEvents", "Leave");
    private static final List<String> Time = Arrays.asList(
            "Past Event", "Current Event", "Future Event", "Leave");

    public void viewProcedure(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose form the following options:");
        System.out.println(View.toString());
         String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "bytime":
                viewByTime();
                viewProcedure();
            case "bymemo":
                Calendar.loggedInUser.events.memos.viewMemos();
                viewProcedure();
            case "bytag":
                Calendar.loggedInUser.events.tags.viewTags();
                viewProcedure();
            case "byalert":
                AlertManager.viewAlerts();
                viewProcedure();
            case "viewallevents":
                Calendar.loggedInUser.events.viewAllEvents();
                viewProcedure();
            case "leave":
                Calendar.MenuOption();
            default:
                viewProcedure();
        }
    }

    public static void viewByTime(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose form the following options:");
        System.out.println(Time.toString());
        String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "past event":
                Viewing.pastEvents();
                viewByTime();
            case "current event":
                Viewing.currentEvent();
                viewByTime();
            case "future event":
                Viewing.futurEvents();
                viewByTime();
            case "leave":
                Calendar.MenuOption();
            default:
                viewByTime();
        }
    }
}
