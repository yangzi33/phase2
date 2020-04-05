package main;

import alert.AlertManager;
import event.Event;
import user.CalendarManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Viewing {
    public CalendarManager calendarManager;
    private static final List<String> View = Arrays.asList("ByTime", "ByMemo",
            "ByTag", "ByAlert", "ViewallEvents", "Leave");
    private static final List<String> Time = Arrays.asList(
            "Past event.Event", "Current event.Event", "Future event.Event", "Leave");
    Viewing(CalendarManager c){calendarManager = c;}


    public void viewProcedure(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose form the following options:");
        System.out.println(View.toString());
        String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "bytime":
                viewByTime();
                break;
            case "bymemo":
                calendarManager.memoManager.viewMemos();
                break;
            case "bytag":
                calendarManager.tagManager.viewTags();
                break;
            case "byalert":
                AlertManager.viewAlerts(calendarManager);
                break;
            case "viewallevents":
                calendarManager.eventManager.viewAllEvents();
            case "leave":
                break;
            default:
                break;
        }
    }

    public void viewByTime(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose form the following options:");
        System.out.println(Time.toString());
        String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "past event":
                pastEvents();
                viewByTime();
            case "current event":
                currentEvent();
                viewByTime();
            case "future event":
                futurEvents();
                viewByTime();
            case "leave":
                viewProcedure();
            default:
                viewByTime();
        }
    }

    public Date stringTimeConvert(String s){
        String pattern = "MM-dd-yyyy kk:mm";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Wrong format!");
        }
        return null;
    }
    public void printEvents(ArrayList<Event> list){
        int n = list.size();
        if(n  == 0){
            System.out.println("No event!");
        }else {
            for (int i = 0; i < n; i++) {
                int number = i + 1;
                System.out.println("event.Event" + number + ": " + list.get(i).getEventName());
                System.out.println("Start at: " + list.get(i).getEventStartTime());
                System.out.println("End at: " + list.get(i).getEventEndTime());
                System.out.println("-----------------------------------------------------");
            }
        }
    }

    public void pastEvents(){
        ArrayList<Event> event = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter time ");
        String time = scanner.nextLine().trim();
        if (Event.TimeFormateCHecker(time)){
            for(Event events : calendarManager.eventManager.allEvents){
                if( stringTimeConvert(time).after(stringTimeConvert(events.getEventEndTime()))) {
                    event.add(events);
            } }
        }
        printEvents(event);
    }

    public void futurEvents() {
        ArrayList<Event> event = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the time ");
        String time = scanner.nextLine().trim();
        if (Event.TimeFormateCHecker(time)){
            for (Event events : calendarManager.eventManager.allEvents) {
                if (stringTimeConvert(time).before(stringTimeConvert(events.getEventEndTime()))) {
                    event.add(events);
                }
            }
        }
        printEvents(event);
    }
    public void currentEvent(){
        ArrayList<Event> event = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the time ");
        String time = scanner.nextLine().trim();
        if (Event.TimeFormateCHecker(time)){
            for (Event events : calendarManager.eventManager.allEvents) {
                if((stringTimeConvert(time).after(stringTimeConvert(events.getEventEndTime())))
                && (stringTimeConvert(time).before(stringTimeConvert(events.getEventEndTime())))){
                    event.add(events);
            }}}
        printEvents(event);
    }


}
