package event;

import java.util.Scanner;
import features.*;
import alert.*;
import main.Calendar;
import java.util.ArrayList;

public class EventManager {
        // =============== Local Storage ===============
        public static ArrayList <Event> allEvents = new ArrayList<>();
        public MemoManager memos = new MemoManager();
        public TagManager tags = new TagManager();
        public AlertManager alerts = new AlertManager();


        public void viewAllEvents(){
                int n = allEvents.size();
                if(n  == 0){
                        System.out.println("You don't have any event yet!");
                }else{
                        for (int i = 0; i < n; i++){
                                int number = i + 1;
                                System.out.println("Event" + number + ": " + allEvents.get(i).getEventName());
                                System.out.println("Start at: " + allEvents.get(i).getEventStartTime());
                                System.out.println("End at: "+ allEvents.get(i).getEventEndTime());
                                System.out.println("Memos: ");
                                memos.MemoForEvent(allEvents.get(i));
                                System.out.println("Tags: ");
                                tags.TagForEvent(allEvents.get(i));
                                AlertManager.displayAlertByID(allEvents.get(i).getEventId());
                                System.out.println("-----------------------------------------------------");
                        }
                }
        }

        /** Use when choose event for a new memo*/
        public static void viewOptions(ArrayList<Event> events){
                int n = events.size();
                if(n  == 0){
                        System.out.println("No event!!");
                }else{
                        for (int i = 0; i < n; i++){
                                int number = i + 1;
                                System.out.println("Event" + number + ": " + events.get(i).getEventName());
                                System.out.println("Start at: " + events.get(i).getEventStartTime());
                                System.out.println("End at: "+ events.get(i).getEventEndTime());
                                System.out.println("-----------------------------------------------------");
                        }
                }
        }


}