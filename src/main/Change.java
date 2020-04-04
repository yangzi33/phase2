package main;

import alert.AlertManager;
import event.Changing;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Change {
    private static final List<String> Change = Arrays.asList("change event", "change alert",
            "change memo", "change tag","change series", "leave");

    private static final List<String> EventChange = Arrays.asList("1.change event name", "2.change event start time",
            "3.change event end time", "4.postpone event", "leave");

    private static final List<String> MemoChange = Arrays.asList("1.change content","2.remove event", "3.add event",
            "4. delete memo","leave");

    private static final List<String> TagChange = Arrays.asList("1.change name","2.remove event", "3.add event",
            "4. delete tag","leave");

    private Changing changing;

    public void changeData() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose form the following options:");
        System.out.println(Change.toString());
        String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "change event":
                changeEvent();
            case "change alert":
                AlertManager.changeAlerts();
            case "change memo":
                changeMemo();
            case "change tag":
                changeTag();
            case "change series":
                break;
            // TODO: 2020-03-05.
            case "leave":
                Calendar.MenuOption();
            default:
                changeData();
        }
    }
    public void changeMemo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to change?");
        System.out.println("Please type the option number:");
        System.out.println(MemoChange.toString());
        String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "1":
                Calendar.loggedInUser.events.memos.changeMemoContent();
            case "2":
                Calendar.loggedInUser.events.memos.removeEvent();
                changeMemo();
            case "3":
                Calendar.loggedInUser.events.memos.addNewEvent();
                changeMemo();
            case "4":
                Calendar.loggedInUser.events.memos.deleteMemo();
                changeMemo();
            case "leave":
                changeData();
            default:
                changeMemo();
        }

    }

    public void changeEvent(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose form the following options:(please enter with space between)");
        System.out.println(EventChange.toString());
        String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "1":
                changing.changename();
            case "2":
                changing.changeStartTime();
            case "3":
                changing.changeEndTime();
            case "4":
                changing.postponeEvent();
            case "leave":
                changeData();
            default:
                changeEvent();
        }
    }

    public void changeTag(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What do you want to change?");
        System.out.println("Please type the option number:");
        System.out.println(TagChange.toString());
        String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "1":
                Calendar.loggedInUser.events.tags.changeTagContent();
                changeTag();
            case "2":
                Calendar.loggedInUser.events.tags.removeEvent();
                changeTag();
            case "3":
                Calendar.loggedInUser.events.tags.addNewEvent();
                changeTag();
            case "4":
                Calendar.loggedInUser.events.tags.deleteTag();
                changeTag();
            case "leave":
                changeData();
            default:
                changeMemo();
        }

    }

}