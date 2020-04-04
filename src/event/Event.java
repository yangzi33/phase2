package event;

import main.Calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static event.EventManager.allEvents;


public class Event {
    /*
     * An events.Event
     * Detailed descriptions required here :)
     * */
    public static String EVENT_EVENTID_PRETEXT = "event";

    private static String EVENT_TIME_PATTERN = "dd-MM-yyyy HH:mm";

    protected String eventId;

    private String eventName;

    private  String startTime;

    private String endTime;


    public Event(String eventName, String startTime, String endTime, String eventId) {
        this.eventName = eventName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.eventId = eventId;
    }

    /**
     * delete the event from allEvents.
     *
     * @param event- the event that want to be deleted.
     */
    public void deleteEvent(Event event){
        allEvents.remove(event);
    }

    /**
     * Change event's name by event's old name
     *
     * @param oldname - event's name
     * @param newname- event's new name
     */
    public void changeEventName(String oldname, String newname){
        for (Event curr_event : allEvents) {
            if (curr_event.eventName.equals(oldname)) {
                curr_event.eventName = newname;
                System.out.println("Event name changed success!");
            }
        }
        System.out.println("Event not found");
    }

    /**
     * Change event's start time by event's name and time
     *
     * @param eventName - event's name
     * @param oldtime - the time of event want to be changed
     * @param newtime - the time of event that want to to change to
     */
    public void changeEventStartTime(String eventName, String oldtime, String newtime){
        if (TimeFormateChecker(newtime) && TimeFormateChecker(oldtime)){
            for (Event curr_event : allEvents) {
                if (curr_event.eventName.equals(eventName)){
                    if (curr_event.startTime != null && curr_event.startTime.equals(oldtime)){
                        curr_event.startTime = newtime;
                        System.out.println("Event start time change success.");
                    }
                    else {
                        System.out.println("this event doesn't happen in this time");
                    }
                }
            }
        }
        System.out.println( "Event not found");
    }

    /**
     * Change event's duration by event's name and time
     *
     * @param eventName - event's name
     * @param time - the time of event want to be changed
     * @param newendtime - the new duration of event that want to to change to
     */
    public void changeEventEndTime(String eventName, String time, String newendtime) {
        if (TimeFormateChecker(eventName) && TimeFormateChecker(newendtime)) {
            for (Event curr_event : allEvents) {
                if (curr_event.eventName.equals(eventName)) {
                    if (curr_event.endTime != null && curr_event.endTime.equals(time)) {
                        curr_event.endTime = newendtime;
                        System.out.println("Event duration change success.");
                    } else {
                        System.out.println("this event doesn't happen in this time");
                    }
                }
            }
        }
        System.out.println("Event not found");
    }

    /**
     * to remove event from user's input.
     */
    public void removeEvent() {
        Searching searching = new Searching();
        System.out.println("Please enter the event name you want to delete");
        Scanner scanner = new Scanner(System.in);
        String eventName = scanner.nextLine().trim();
        ArrayList<Event> remove = searching.givennamesearch(allEvents,eventName);
        if (remove.size() != 1) {
            remove = searching.searchByStartTime(remove);
            assert remove != null;
            if (remove.size() != 1) {
                remove = searching.searchByEndTime(remove);
            }
        }
        System.out.println("Are you sure you want to delete" + eventName + "? Y/N");
        String answer = scanner.nextLine().trim();
        if (answer.equals("Y")) {
            assert remove != null;
            deleteEvent(remove.get(0));
            System.out.println("Event deleted success!");
        }else if (answer.equals("N")) {
            System.out.println("exit!");
            Calendar.MenuOption();
        }else{
            System.out.println("Wrong format!");
            removeEvent();
        }
    }
    /** Get events.Event's Name
     *
     * @return eventName
     */
    public String getEventName() {
        return this.eventName;
    }

    /** Get event's duration
     *
     * @return duration
     */
    public String getEventEndTime() {
        return endTime != null ? endTime : "";
    }

    /**Get event's starttime.
     *
     * @return startTime.
     */
    public  String getEventStartTime() {
        return startTime != null ? startTime : "";
    }

    /** Get events.Event's ID
     *
     * @return eventId
     */
    public String getEventId() {
        return this.eventId;
    }

    public ArrayList<String> showEvent(){
        ArrayList<String> information = new ArrayList<>();
        information.add(this.eventName);
        information.add(startTime != null ? startTime : "");
        information.add(endTime != null ? endTime : "");
        information.add(eventId);
        return information;
    }

    /**
     * want to check if input is in a date format.
     *
     * @param time - the time want to check
     * @return true if the string can convert to date format, otherwise false.
     */
    public static boolean TimeFormateChecker(String time) {
        boolean answer = false;
        SimpleDateFormat format = new SimpleDateFormat(EVENT_TIME_PATTERN);
        format.setLenient(false);
        try {
            format.parse(time);
            answer = true;
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error! Please follow the format " + EVENT_TIME_PATTERN);
        }
        return answer;
    }

    /**
     * Postpone the event with the event name and old time to the new time,
     * maintaining the duration of the event
     *
     * @param eventName - the event name to postpone
     * @param oldtime - the old time of the event, for validation
     * @param newtime - the new start time of the event
     */
    public void postponeTo(String eventName, String oldtime, String newtime) {
        if (TimeFormateChecker(newtime) && TimeFormateChecker(oldtime)){
            SimpleDateFormat format = new SimpleDateFormat(EVENT_TIME_PATTERN);
            try {
                Date newDate = format.parse(newtime);
                for (Event curr_event : allEvents) {
                    if (curr_event.eventName.equals(eventName)){
                        if (curr_event.startTime != null && curr_event.endTime != null &&
                                curr_event.startTime.equals(oldtime)){
                            Date startDate = format.parse(curr_event.startTime);
                            Date endDate = format.parse(curr_event.endTime);
                            long duration = startDate.getTime() - newDate.getTime();
                            Date newEndDate = new Date(duration + endDate.getTime());
                            curr_event.startTime = newtime;
                            curr_event.endTime = format.format(newEndDate);
                            System.out.println("Event postpone success.");
                        }
                        else {
                            System.out.println("This event doesn't happen in this time");
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        System.out.println( "Event not found");
    }

    /**
     * Postpone the event's start time indefinitely.
     * Note: This will make the start time and end time of the event to null.
     *
     * @param eventName - the name of the event
     * @param oldtime - the old start time of the event, for validation
     */
    public void postponeIndefinitely(String eventName, String oldtime) {
        for (Event curr_event : allEvents) {
            if (curr_event.eventName.equals(eventName)){
                if (curr_event.startTime != null && curr_event.startTime.equals(oldtime)){
                    curr_event.startTime = null;
                    curr_event.endTime = null;
                    System.out.println("Event postpone success.");
                }
                else {
                    System.out.println("This event doesn't happen in this time");
                }
            }
        }
    }


}
