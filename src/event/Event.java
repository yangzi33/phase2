package event;

import user.CalendarManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class Event {
    /*
     * An events.event.Event
     * Detailed descriptions required here :)
     * */
    public static String EVENT_EVENTID_PRETEXT = "event";

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
    public void deleteEvent(CalendarManager c, Event event){
        c.eventManager.allEvents.remove(event);
    }

    /**
     * Change event's name by event's old name
     *
     * @param oldname - event's name
     * @param newname- event's new name
     */
    public void changeEventName(String oldname, String newname, CalendarManager c){
        for (Event curr_event : c.eventManager.allEvents) {
            if (curr_event.eventName.equals(oldname)) {
                curr_event.eventName = newname;
                System.out.println("event.Event name changed success!");
            }
        }
        System.out.println("event.Event not found");
    }

    /**
     * Change event's start time by event's name and time
     *
     * @param eventName - event's name
     * @param oldtime - the time of event want to be changed
     * @param newtime - the time of event that want to to change to
     */
    public void changeEventstarttime(String eventName, String oldtime, String newtime, CalendarManager c){
        if (TimeFormateCHecker(newtime) && TimeFormateCHecker(oldtime)){
            for (Event curr_event : c.eventManager.allEvents) {
                if (curr_event.eventName.equals(eventName)){
                    if (curr_event.startTime.equals(oldtime)){
                        curr_event.startTime = newtime;
                        System.out.println("event.Event start time change success.");
                    }
                    else {
                        System.out.println("this event doesn't happen in this time");
                    }
                }
            }
        }
        System.out.println( "event.Event not found");
    }

    /**
     * Change event's duration by event's name and time
     *
     * @param eventName - event's name
     * @param time - the time of event want to be changed
     * @param newendtime - the new duration of event that want to to change to
     */
    public void changeEventEndTime(String eventName, String time, String newendtime, CalendarManager c) {
        if (TimeFormateCHecker(eventName) && TimeFormateCHecker(newendtime)) {
            for (Event curr_event : c.eventManager.allEvents) {
                if (curr_event.eventName.equals(eventName)) {
                    if (curr_event.startTime.equals(time)) {
                        curr_event.endTime = newendtime;
                        System.out.println("event.Event duration change success.");
                    } else {
                        System.out.println("this event doesn't happen in this time");
                    }
                }
            }
        }
        System.out.println("event.Event not found");
    }

    /**
     * to remove event from user's input.
     */
//    public void removeEvent(user.CalendarManager c) {
//        main.Searching searching = new main.Searching();
//        System.out.println("Please enter the event name you want to delete");
//        Scanner scanner = new Scanner(System.in);
//        String eventName = scanner.nextLine().trim();
//        ArrayList<event.Event> remove = searching.givennamesearch(c.eventManager.allEvents,eventName);
//        if (remove.size() != 1) {
//            remove = searching.searchByStartTIme(remove);
//            assert remove != null;
//            if (remove.size() != 1) {
//                remove = searching.searchByEndTime(remove);
//            }
//        }
//        System.out.println("Are you sure you want to delete" + eventName + "? Y/N");
//        String answer = scanner.nextLine().trim();
//        if (answer.equals("Y")) {
//            assert remove != null;
//            deleteEvent(c, remove.get(0));
//            System.out.println("event.Event deleted success!");
//        }else if (answer.equals("N")) {
//            System.out.println("exit!");
//        }else{
//            System.out.println("Wrong format!");
//            removeEvent(c);
//        }
//    }
    /** Get events.event.Event's Name
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
        return this.endTime;
    }

    /**Get event's starttime.
     *
     * @return startTime.
     */
    public  String getEventStartTime() {
        return this.startTime;
    }

    /** Get events.event.Event's ID
     *
     * @return eventId
     */
    public String getEventId() {
        return this.eventId;
    }

    public ArrayList<String> showEvent(){
        ArrayList<String> information = new ArrayList<>();
        information.add(this.eventName);
        information.add(startTime);
        information.add(endTime);
        information.add(eventId);
        return information;
    }

    /**
     * want to check if input is in a date format.
     *
     * @param time - the time want to check
     * @return true if the string can convert to date format, otherwise false.
     */
    public static boolean TimeFormateCHecker(String time) {
        boolean answer = false;
        String pattern = "dd-MM-yyyy HH:mm";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setLenient(false);
        try {
            format.parse(time);
            answer = true;
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
        return answer;
    }


}
