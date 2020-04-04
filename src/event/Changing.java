package event;

import java.util.ArrayList;
import java.util.Scanner;

public class Changing {
    private Event event;
    private Searching searching;

    public void changename() {
        System.out.println("Please enter the old event name you want to change");
        Scanner scanner = new Scanner(System.in);
        String eventName = scanner.nextLine().trim();
        ArrayList<Event> change = searching.searchByName();
        if (change.size() != 1) {
            change = searching.searchByStartTime(change);
            assert change != null;
            if (change.size() != 1) {
                change = searching.searchByEndTime(change);
            }
        }
        System.out.println("Please enter the new name you want to change to:");
        Scanner scanner1 = new Scanner(System.in);
        String newname = scanner1.nextLine().trim();
        System.out.println("Are you sure you want to change" + eventName + "? Y/N");
        String answer = scanner.nextLine().trim();
        if (answer.equals("Y")) {
            assert change != null;
            for (Event event :change){
                event.changeEventName(event.getEventName(),newname);
                System.out.println("Event name change success!");}
        } else if (answer.equals("N")) {
            System.out.println("exit!");
        } else {
            System.out.println("Wrong format!");
        }
    }
    public void changeStartTime(){
        System.out.println("Please enter the event name you want to change");
        Scanner scanner = new Scanner(System.in);
        String eventName = scanner.nextLine().trim();
        ArrayList<Event> change = searching.searchByName();
        if (change.size() != 1) {
            change = searching.searchByStartTime(change);
            assert change != null;
            if (change.size() != 1) {
                change = searching.searchByEndTime(change);
            }
        }
        System.out.println("Please enter the time you want to change to:");
        Scanner scanner1 = new Scanner(System.in);
        String newtime = scanner1.nextLine().trim();
        Event.TimeFormateChecker(newtime);
        System.out.println("Are you sure you want to change the start time? Please answer in Y/N");
        String answer = scanner.nextLine().trim();
        if (answer.equals("Y")) {
            assert change != null;
            for (Event event :change){
                event.changeEventStartTime(eventName,event.getEventStartTime(),answer);
                System.out.println("Event name change success!");}
        } else if (answer.equals("N")) {
            System.out.println("exit!");
        } else {
            System.out.println("Wrong format!");
        }
    }
    public void changeEndTime() {
        System.out.println("Please enter the event name you want to change");
        Scanner scanner = new Scanner(System.in);
        String eventName = scanner.nextLine().trim();
        ArrayList<Event> change = searching.searchByName();
        if (change.size() != 1) {
            change = searching.searchByStartTime(change);
            assert change != null;
            if (change.size() != 1) {
                change = searching.searchByEndTime(change);
            }
        }
        System.out.println("Please enter the time you want to change to:");
        Scanner scanner1 = new Scanner(System.in);
        String newtime = scanner1.nextLine().trim();
        Event.TimeFormateChecker(newtime);
        System.out.println("Are you sure you want to change the end time? Please answer in Y/N");
        String answer = scanner.nextLine().trim();
        if (answer.equals("Y")) {
            assert change != null;
            for (Event event : change) {
                event.changeEventEndTime(eventName, event.getEventEndTime(), answer);
                System.out.println("Event name change success!");
            }
        } else if (answer.equals("N")) {
            System.out.println("exit!");
        } else {
            System.out.println("Wrong format!");
        }
    }

    /**
     * Postpone an event
     */
    public void postponeEvent() {
        System.out.println("Please enter the event name you want to postpone");
        Scanner scanner = new Scanner(System.in);
        String eventName = scanner.nextLine().trim();
        ArrayList<Event> change = searching.searchByName();
        if (change.size() != 1) {
            change = searching.searchByStartTime(change);
            assert change != null;
            if (change.size() != 1) {
                change = searching.searchByEndTime(change);
            }
        }

        System.out.println("Do you have a date you want to postpone the event to? Please answer in Y/N");
        String answer = scanner.nextLine().trim();
        if (answer.equals("Y")) {
            // postpone to date
            System.out.println("Please enter the time you want to postpone to:");
            String newtime = scanner.nextLine().trim();

            if (Event.TimeFormateChecker(newtime)) {
                System.out.println("Are you sure you want to postpone? Please answer in Y/N");
                answer = scanner.nextLine().trim();

                if (answer.equals("Y")) {
                    assert change != null;
                    for (Event event : change) {
                        event.postponeTo(eventName, event.getEventStartTime(), newtime);
                        System.out.println("Event postpone success!");
                    }
                } else if (answer.equals("N")) {
                    System.out.println("exit!");
                } else {
                    System.out.println("Wrong format!");
                }
            }
        } else {
            // postpone indefinitely
            System.out.println("Are you sure you want to postpone indefinitely? Please answer in Y/N");
            answer = scanner.nextLine().trim();

            if (answer.equals("Y")) {
                assert change != null;
                for (Event event : change) {
                    event.postponeIndefinitely(eventName, event.getEventStartTime());
                    System.out.println("Event postpone success!");
                }
            } else if (answer.equals("N")) {
                System.out.println("exit!");
            } else {
                System.out.println("Wrong format!");
            }
        }
    }
}
