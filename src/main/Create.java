package main;

import alert.AlertManager;
import event.Creating;
import event.Event;
import event.EventManager;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Create {
    private final List<String> Create = Arrays.asList("CreateEvent", "CreateAlert",
            "CreateMemo", "CreateTag", "CreateSeries", "Leave");
    private Creating creating;
    public void CreatingProcedure(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose form the following options:");
        System.out.println(Create.toString());
         String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "createevent":
                creating.CreateEvent();
                CreatingProcedure();
            case "createalert":
                AlertManager.createAlerts();
                CreatingProcedure();
            case "creatememo":
                creating.CreateMemo(Calendar.loggedInUser.events);
                CreatingProcedure();
            case "createtag":
                creating.CreateTag(Calendar.loggedInUser.events);
                CreatingProcedure();
            case "createseries":
                creating.CreateSeries();
                CreatingProcedure();
            case "leave":
                Calendar.MenuOption();
            default:
                CreatingProcedure();
        }
    }


}
