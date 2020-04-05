package main;//
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class Changing {
//    private event.Event event;
//    private main.Searching searching;
//
//    public void changename() {
//        System.out.println("Please enter the old event name you want to change");
//        Scanner scanner = new Scanner(System.in);
//        String eventName = scanner.nextLine().trim();
//        ArrayList<event.Event> change = searching.searchByName();
//        if (change.size() != 1) {
//            change = searching.searchByStartTIme(change);
//            assert change != null;
//            if (change.size() != 1) {
//                change = searching.searchByEndTime(change);
//            }
//        }
//        System.out.println("Please enter the new name you want to change to:");
//        Scanner scanner1 = new Scanner(System.in);
//        String newname = scanner1.nextLine().trim();
//        System.out.println("Are you sure you want to change" + eventName + "? Y/N");
//        String answer = scanner.nextLine().trim();
//        if (answer.equals("Y")) {
//            assert change != null;
//            for (event.Event event :change){
//                event.changeEventName(event.getEventName(),newname);
//                System.out.println("event.Event name change success!");}
//        } else if (answer.equals("N")) {
//            System.out.println("exit!");
//        } else {
//            System.out.println("Wrong format!");
//        }
//    }
//    public  void changeStartTime(){
//        System.out.println("Please enter the event name you want to change");
//        Scanner scanner = new Scanner(System.in);
//        String eventName = scanner.nextLine().trim();
//        ArrayList<event.Event> change = searching.searchByName();
//        if (change.size() != 1) {
//            change = searching.searchByStartTIme(change);
//            assert change != null;
//            if (change.size() != 1) {
//                change = searching.searchByEndTime(change);
//            }
//        }
//        System.out.println("Please enter the time you want to change to:");
//        Scanner scanner1 = new Scanner(System.in);
//        String newtime = scanner1.nextLine().trim();
//        event.Event.TimeFormateCHecker(newtime);
//        System.out.println("Are you sure you want to change the start time? Please answer in Y/N");
//        String answer = scanner.nextLine().trim();
//        if (answer.equals("Y")) {
//            assert change != null;
//            for (event.Event event :change){
//                event.changeEventstarttime(eventName,event.getEventStartTime(),answer);
//                System.out.println("event.Event name change success!");}
//        } else if (answer.equals("N")) {
//            System.out.println("exit!");
//        } else {
//            System.out.println("Wrong format!");
//        }
//    }
//    public  void changeEndTime() {
//        System.out.println("Please enter the event name you want to change");
//        Scanner scanner = new Scanner(System.in);
//        String eventName = scanner.nextLine().trim();
//        ArrayList<event.Event> change = searching.searchByName();
//        if (change.size() != 1) {
//            change = searching.searchByStartTIme(change);
//            assert change != null;
//            if (change.size() != 1) {
//                change = searching.searchByEndTime(change);
//            }
//        }
//        System.out.println("Please enter the time you want to change to:");
//        Scanner scanner1 = new Scanner(System.in);
//        String newtime = scanner1.nextLine().trim();
//        event.Event.TimeFormateCHecker(newtime);
//        System.out.println("Are you sure you want to change the end time? Please answer in Y/N");
//        String answer = scanner.nextLine().trim();
//        if (answer.equals("Y")) {
//            assert change != null;
//            for (event.Event event : change) {
//                event.changeEventEndTime(eventName, event.getEventEndTime(), answer);
//                System.out.println("event.Event name change success!");
//            }
//        } else if (answer.equals("N")) {
//            System.out.println("exit!");
//        } else {
//            System.out.println("Wrong format!");
//        }
//    }
//
//}
