package event;

import features.*;


import java.util.Scanner;
import static java.lang.Integer.parseInt;

import main.Calendar;
import series.NewSeries;

public class Creating {

    public void CreateEvent(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input event name");
        String eventName = scanner.nextLine().trim();

        System.out.println("Please input event start time, in a format of DD-MM-YYYY HH:MM");
        String eventStartTime = scanner.nextLine().trim();
        Event.TimeFormateChecker(eventStartTime);

        System.out.println("Please input event end time, in a format of DD-MM-YYYY HH:MM");
        String eventEndTime = scanner.nextLine().trim();
        Event.TimeFormateChecker(eventEndTime);
        for (Event curr_event : Calendar.loggedInUser.events.allEvents) {
            if (curr_event.getEventName().equals(eventName) ) {
                if (curr_event.getEventStartTime().equals(eventStartTime)) {
                    if (curr_event.getEventEndTime().equals(eventEndTime)) {
                        System.out.println("Have same event,start time and end time already!");
                    }
                }
            }
        }
        int eventidNum = Calendar.loggedInUser.events.allEvents.size() + 1;
        String eventid = Event.EVENT_EVENTID_PRETEXT + eventidNum;

        Event newlyEvent = new Event(eventName, eventStartTime, eventEndTime, eventid);
        Calendar.loggedInUser.events.allEvents.add(newlyEvent);
        System.out.println("Event added!");
    }

    public void CreateMemo(EventManager mangaer){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the memo:");
        String content = scanner.nextLine().trim();
        Memo newlymemo = new Memo();
        newlymemo.setContent(content);
        mangaer.memos.allMemos.add(newlymemo);
        System.out.println("Please select event for the memo by putting event number" );
        System.out.println("-- Separate events by comma");
        System.out.println("-- Type 'newevent' if you want to create new event for the memo");
        mangaer.viewOptions(Calendar.loggedInUser.events.allEvents);
        String type = scanner.nextLine().trim();
        if (type.toLowerCase().equals("newevent") ){
            CreateEvent();
            int n = Calendar.loggedInUser.events.allEvents.size() - 1;
            newlymemo.addEventtoMemo(Calendar.loggedInUser.events.allEvents.get(n));
        }else{
            String[] result = type.split(",");
            for (String s : result) {
                int value = parseInt(s);
                newlymemo.addEventtoMemo(Calendar.loggedInUser.events.allEvents.get(value - 1));
            }
        }
    }

    public void CreateTag(EventManager manager){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the tag name:");
        String name = scanner.nextLine().trim();
        Tag tag = new Tag();
        tag.setName(name);
        manager.tags.allTags.add(tag);
        System.out.println("Please select event for the tag by putting event number" );
        System.out.println("-- Separate events by comma");
        System.out.println("-- Type 'newevent' if you want to create new event for the tag");
        manager.viewOptions(Calendar.loggedInUser.events.allEvents);
        String events = scanner.nextLine().trim();
        if (events.toLowerCase().equals("newevent")){
            CreateEvent();
            int n;
            n = Calendar.loggedInUser.events.allEvents.size() - 1;
            tag.addEvent(Calendar.loggedInUser.events.allEvents.get(n));
        }else{
            String[] result = events.split(",");
            for (String s : result) {
                int value = parseInt(s);
                tag.addEvent(Calendar.loggedInUser.events.allEvents.get(value - 1));
            }
        }

    }

    public void CreateSeries() {
        boolean validInput = false;

        while (!validInput) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your series' type: LINKED or REPEATING.");
            String typeInput = scanner.nextLine().toLowerCase();

            if (typeInput.equals("linked") || typeInput.equals("repeating")) {
                validInput = true;

                if (typeInput.equals("linked")){
                    NewSeries.linkedSeriesInput();
                }
                if (typeInput.equals("repeating")){
                    NewSeries.repeatingSeriesInput();
                }
            } else {
                System.out.println("Invalid series type. Please enter a correct type: [linked] or [repeating]");
            }
        }
    }
}
