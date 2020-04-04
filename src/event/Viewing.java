package event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Viewing {

    public static Date stringTimeConvert(String s){
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
    public static void printEvents(ArrayList<Event> list){
        int n = list.size();
        if(n  == 0){
            System.out.println("No event!");
        }else {
            for (int i = 0; i < n; i++) {
                int number = i + 1;
                System.out.println("Event" + number + ": " + list.get(i).getEventName());
                System.out.println("Start at: " + list.get(i).getEventStartTime());
                System.out.println("End at: " + list.get(i).getEventEndTime());
                System.out.println("-----------------------------------------------------");
            }
        }
    }

    public static void pastEvents(){
        ArrayList<Event> event = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter time ");
        String time = scanner.nextLine().trim();
        if (Event.TimeFormateChecker(time)){
            for(Event events : EventManager.allEvents){
                if( stringTimeConvert(time).after(stringTimeConvert(events.getEventEndTime()))) {
                    event.add(events);
            } }
        }
        printEvents(event);
    }

    public static void futurEvents() {
        ArrayList<Event> event = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the time ");
        String time = scanner.nextLine().trim();
        if (Event.TimeFormateChecker(time)){
            for (Event events : EventManager.allEvents) {
                if (stringTimeConvert(time).before(stringTimeConvert(events.getEventEndTime()))) {
                    event.add(events);
                }
            }
        }
        printEvents(event);
    }
    public static void currentEvent(){
        ArrayList<Event> event = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the time ");
        String time = scanner.nextLine().trim();
        if (Event.TimeFormateChecker(time)){
            for (Event events : EventManager.allEvents) {
                if((stringTimeConvert(time).after(stringTimeConvert(events.getEventEndTime())))
                && (stringTimeConvert(time).before(stringTimeConvert(events.getEventEndTime())))){
                    event.add(events);
            }}}
        printEvents(event);
    }


}
