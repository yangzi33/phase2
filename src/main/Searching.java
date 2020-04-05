package main;

import event.Event;
import series.LinkedSeries;
import series.LinkedSeriesManager;
import series.RepeatSeries;
import series.RepeatSeriesManager;
import user.CalendarManager;

import java.util.ArrayList;
import java.util.Scanner;

public class Searching {

    CalendarManager calendarManager;

    /** Please do  not change this method!!!*/
    public Event searchById(String id){
        for (Event event : calendarManager.eventManager.allEvents){
            if( event.getEventId().equals(id)){
                return event; }
        }
        return null;
    }

    public ArrayList<Event> searchByName(ArrayList<Event> list) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the event name: ");
        String name = scanner.nextLine().trim();
        return givennamesearch(list, name);
    }

    public ArrayList<Event> givennamesearch(ArrayList<Event> list, String name){
        ArrayList<Event> eventNames = new ArrayList<>();
        for (Event event : list) {
            if (event.getEventName().equals(name)) {
                eventNames.add(event);
            }
        }
        if (eventNames.size() != 0) {
            return eventNames;
        } else {
            return null;
        }
    }

    public ArrayList<Event> searchByName() {
        return searchByName(calendarManager.eventManager.allEvents);
    }

    public ArrayList<Event> searchByStartTIme(ArrayList<Event> list){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the event start time:");
        String starttime = scanner.nextLine().trim();
        return givenNameSearchStartTime(list, starttime);
    }
    public ArrayList<Event> givenNameSearchStartTime(ArrayList<Event> list, String starttime){
        Event.TimeFormateCHecker(starttime);
        ArrayList<Event> eventStartTime = new ArrayList<>();
        for (Event event : list) {
            if (event.getEventStartTime().equals(starttime)) {
                eventStartTime.add(event);
            }}
        if (eventStartTime.size() != 0) {
            return eventStartTime;
        } else {
            return null;
        }
    }

    public ArrayList<Event> searchByStartTime(){
        return searchByStartTIme(calendarManager.eventManager.allEvents);
    }

    public ArrayList<Event> searchByEndTime(ArrayList<Event> list) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the event end time:");
        String endtime = scanner.nextLine().trim();
        return givenNameSearchEndTime(list, endtime);
    }

    public ArrayList<Event> givenNameSearchEndTime(ArrayList<Event> list, String endtime){
        Event.TimeFormateCHecker(endtime);
        ArrayList<Event> eventNames = new ArrayList<>();
        for (Event event : list) {
            if (event.getEventName().equals(endtime)) {
                eventNames.add(event);
            }}
        if (eventNames.size() != 0) {
            return eventNames;
        } else {
            return null;
        }
    }

    public ArrayList<Event> searchByEndTime(){
        return searchByEndTime(calendarManager.eventManager.allEvents);
    }

    // Note: this method will be unpacked into multiple mthods: separate UI and events found.
    public void searchBySeriesName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the series' name:");
        String seriesName = scanner.nextLine().trim();
        ArrayList<String> foundInLinked = new ArrayList<>();
        ArrayList<String> foundInRepeat = new ArrayList<>();

        boolean seriesExists = false;

        while (!seriesExists) {
            for (LinkedSeries lseries : LinkedSeriesManager.allLinkedSeries) {
                if (lseries.getSeriesName().equals(seriesName)) {
                    seriesExists = true;
                    // main.main.Creating deep copy, so the id's won't be modified accidently
                    ArrayList<String> idFound = lseries.getSeriesEventsId();
                    for (Event event : calendarManager.eventManager.allEvents) {
                        if (idFound.contains(event.getEventId())) {
                            foundInLinked.add(event.getEventName());
                        }
                    }
                }
            }

            for (RepeatSeries rseries : RepeatSeriesManager.allRepeatSeries) {
                if (rseries.getSeriesName().equals(seriesName)) {
                    seriesExists = true;
                    for (Event event : calendarManager.eventManager.allEvents) {
                        if (event.getEventId().equals(rseries.getSeriesEventsId().get(0))) {
                            foundInRepeat.add(event.getEventName());
                            foundInRepeat.add(rseries.getFrequency());
                            foundInRepeat.add(rseries.getStartTime());
                            foundInRepeat.add(rseries.getEndTime());
                        }
                    }
                }
            }
        }
        System.out.println("The series you entered contains the following events:");

        if (!foundInLinked.isEmpty()) {
            System.out.println("The following events are contained in series you entered:");
            for (String eventName: foundInLinked) {
                System.out.println(eventName);
            }
        }
        if (!foundInRepeat.isEmpty()) {
            System.out.println("The series you entered contains repeating event " + foundInRepeat.get(0));
            System.out.println("This event is a "+ foundInRepeat.get(1) + "event");
            System.out.println("This event starts at date" + foundInRepeat.get(2) +
                    ", ends at date" + foundInRepeat.get(3));
        }

    }

}

