package main;

import alert.AlertManager;
import event.EventManager;
import event.Searching;
import features.MemoManager;
import features.TagManager;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Search {
    private List<String> Search = Arrays.asList("event name", "memo",
            "tag", "series name", "start time","end time", "Leave");
    private Searching searching;

    public void SearchProcedure(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose form the following options:");
        System.out.println(Search.toString());
        String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "event name":
                searching.searchByName();
            case "memo":
                Calendar.loggedInUser.events.memos.findMemo();
                SearchProcedure();
            case "tag":
                Calendar.loggedInUser.events.tags.findTag();
                SearchProcedure();
            case "start time":
                searching.searchByStartTime();
            case  "end time":
                searching.searchByEndTime();
            case  "current alert":
                AlertManager.displayAlerts(AlertManager.checkCurrentAlerts());
            case "leave":
                Calendar.MenuOption();
            case "series name":
//                Searching.searchBySeriesName();
            default:
                SearchProcedure();
        }
    }



}
