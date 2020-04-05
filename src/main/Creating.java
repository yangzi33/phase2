package main;

import alert.AlertManager;
import event.Event;
import feature.Memo;
import feature.Tag;
import series.NewSeries;
import user.CalendarManager;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;


public class Creating {
    public List<String> creatingOptions = Arrays.asList("createEvent", "createMemo", "createTag", "createAlert",
            "createSeries", "leave");
    CalendarManager calender;

    public Creating(CalendarManager c){
        this.calender = c;
    }

    public void creatingProcedure(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please choose form the following options:");
        System.out.println(creatingOptions.toString());
        String userInput = scanner.nextLine();
        switch (userInput.toLowerCase()) {
            case "createevent":
                CreateEvent(calender);
                break;
            case "creatememo":
                CreateMemo(calender);
                break;
            case "createtag":
                CreateTag(calender);
                break;
            case "createalert":
                AlertManager.createAlerts(calender);
                break;
            case "createseries":
                CreateSeries(calender);
                break;
            case "leave":
                break;
            default:
                // We don't need to do anything here
        }

    }

    public void CreateEvent(CalendarManager calendarManager){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input event name");
        String eventName = scanner.nextLine().trim();

        System.out.println("Please input event start time, in a format of DD-MM-YYYY HH:MM");
        String eventStartTime = scanner.nextLine().trim();
        Event.TimeFormateCHecker(eventStartTime);

        System.out.println("Please input event end time, in a format of DD-MM-YYYY HH:MM");
        String eventEndTime = scanner.nextLine().trim();
        Event.TimeFormateCHecker(eventEndTime);
        if (!calendarManager.eventManager.allEvents.isEmpty()){
            for (Event curr_event : calendarManager.eventManager.allEvents) {
                if (curr_event.getEventName().equals(eventName) ) {
                    if (curr_event.getEventStartTime().equals(eventStartTime)) {
                        if (curr_event.getEventEndTime().equals(eventEndTime)) {
                            System.out.println("Have same event,start time and end time already!");
                        }
                    }
                }
            }
        }
        int eventidNum = calendarManager.eventManager.allEvents.size() + 1;
        String eventid = "event" + eventidNum;
        Event newlyEvent = new Event(eventName, eventStartTime, eventEndTime, eventid);
        calendarManager.eventManager.allEvents.add(newlyEvent);
        System.out.println("event.Event added!");
    }

    public void CreateMemo(CalendarManager calendarManager){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the memo:");
        String content = scanner.nextLine().trim();
        Memo newlymemo = new Memo();
        newlymemo.setContent(content);
        calendarManager.memoManager.allMemos.add(newlymemo);
        System.out.println("Please select event for the memo by putting event number" );
        System.out.println("-- Separate events by comma");
        System.out.println("-- Type 'newevent' if you want to create new event for the memo");
        calendarManager.eventManager.viewOptions(calendarManager.eventManager.allEvents);
        String type = scanner.nextLine().trim();
        if (type.toLowerCase().equals("newevent") ){
            CreateEvent(calendarManager);
            int n = calendarManager.eventManager.allEvents.size() - 1;
            newlymemo.addEventtoMemo(calendarManager.eventManager.allEvents.get(n));
        }else{
            String[] result = type.split(",");
            for (String s : result) {
                int value = parseInt(s);
                newlymemo.addEventtoMemo(calendarManager.eventManager.allEvents.get(value - 1));
            }
        }
    }

    public void CreateTag(CalendarManager calendarManager){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the tag name:");
        String name = scanner.nextLine().trim();
        Tag tag = new Tag();
        tag.setName(name);
        calendarManager.tagManager.allTags.add(tag);
        System.out.println("Please select event for the tag by putting event number" );
        System.out.println("-- Separate events by comma");
        System.out.println("-- Type 'newevent' if you want to create new event for the tag");
        calendarManager.eventManager.viewOptions(calendarManager.eventManager.allEvents);
        String events = scanner.nextLine().trim();
        if (events.toLowerCase().equals("newevent")){
            CreateEvent(calendarManager);
            int n;
            n = calendarManager.eventManager.allEvents.size() - 1;
            tag.addEvent(calendarManager.eventManager.allEvents.get(n));
        }else{
            String[] result = events.split(",");
            for (String s : result) {
                int value = parseInt(s);
                tag.addEvent(calendarManager.eventManager.allEvents.get(value - 1));
            }
        }

    }

    public void CreateSeries(CalendarManager calendarManager) {
        NewSeries newSeries = new NewSeries(calendarManager);
        boolean validInput = false;

        while (!validInput) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your series' type: LINKED or REPEATING.");
            String typeInput = scanner.nextLine().toLowerCase();

            if (typeInput.equals("linked") || typeInput.equals("repeating")) {
                validInput = true;

                if (typeInput.equals("linked")){
                    newSeries.linkedSeriesInput();
                }
                if (typeInput.equals("repeating")){
                    newSeries.repeatingSeriesInput();
                }
            } else {
                System.out.println("Invalid series type. Please enter a correct type: [linked] or [repeating]");
            }
        }
    }
}
