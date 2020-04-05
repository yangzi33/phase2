package feature;

import event.Event;
import user.CalendarManager;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class TagManager extends FeatureManager {
    public ArrayList<Tag> allTags = new ArrayList<>();

    /** user.user.User can view tags they created. */
    public void viewTags() {
        int n = allTags.size();
        if(n  == 0){
            System.out.println("  ");
            System.out.println("You don't have any tags!");
        }else{
            for (int i = 0; i < n; i++){
                int number = i + 1;
                System.out.println("feature.Tag" + number + ": " + allTags.get(i).Name);
                System.out.println("With events:");
                if(allTags.get(i).getEvents() == null){
                    System.out.println("No events yet!!");
                }else{FeatureEvents(allTags.get(i));
                System.out.println(" ");}

        }
    }}
    public void TagForEvent(Event event){
        int n = allTags.size();
        if (n == 0){
            System.out.println("No tag yet!");
        }else{
            int num = 0;
            boolean indicator = false;
            ArrayList<String> printtags = new ArrayList<>();
            for(Tag t: allTags){
                if(t.getIds().contains(event.getEventId())){
                    indicator =  true;
                    num++;
                    printtags.add("feature.Tag#" + num + ": " +t.getName());
                }
            }

            if (!indicator){
                System.out.println("You do not have any tag!");
            }else {
                for (String printtag : printtags) {
                System.out.println(printtag);
                }
            }
        }

    }

    public void changeTagContent(){
        for (int i = 0; i < allTags.size(); i++){
            int num  = 1+i;
            System.out.println("feature.Tag" + num+ ": " + allTags.get(i).getName());
        }
        System.out.println("Please type the tag number you want to change: ");
        Scanner scanner1 = new Scanner(System.in);
        String memo = scanner1.nextLine().trim();
        int num = parseInt(memo);
        System.out.println("Please enter the newname:");
        String newcontent= scanner1.nextLine().trim();
        allTags.get(num-1).setName(newcontent);
        System.out.println("Changed!");
    }

    public void removeEvent(CalendarManager c){
        for (int i = 0; i < allTags.size(); i++){
            int num  = 1+i;
            System.out.println("feature.Tag" + num+ ": " + allTags.get(i).getName());
        }
        System.out.println("Please type the tag number you want to change: ");
        Scanner scanner1 = new Scanner(System.in);
        String memo = scanner1.nextLine().trim();
        int num = parseInt(memo);
        c.eventManager.viewOptions(allTags.get(num-1).getEvents());
        String eventnum= scanner1.nextLine().trim();
        int eventindext = parseInt(eventnum);
        allTags.get(num-1).deleteEvent(allTags.get(num-1).getEvents().get(eventindext-1));
        System.out.println("Changed!");
    }

    public void addNewEvent(CalendarManager c){
        for (int i = 0; i < allTags.size(); i++){
            int num  = 1+i;
            System.out.println("feature.Tag" + num+ ": " + allTags.get(i).getName());
        }
        System.out.println("Please type the tag number you want to add event to: ");
        Scanner scanner1 = new Scanner(System.in);
        String memo = scanner1.nextLine().trim();
        int num = parseInt(memo);
        c.eventManager.viewOptions(allTags.get(num-1).getEvents());
        String eventnum= scanner1.nextLine().trim();
        int eventindext = parseInt(eventnum);
        allTags.get(num-1).addEvent(allTags.get(num-1).getEvents().get(eventindext-1));
        System.out.println("Changed!");
    }
    public void deleteTag(){
        for (int i = 0; i < allTags.size(); i++){
            int num  = 1+i;
            System.out.println("feature.Tag" + num+ ": " + allTags.get(i).getName());
        }
        System.out.println("Please type the tag number you want to delete: ");
        Scanner scanner1 = new Scanner(System.in);
        String memo = scanner1.nextLine().trim();
        int num = parseInt(memo);
        allTags.remove(num-1);
        System.out.println("Deleted!");
    }


    public void findTag() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input name of the tag you want to search:");
        String content = scanner.nextLine().trim();
        ArrayList<Tag> lists = new ArrayList<>();
        int n = allTags.size();
        for (int i = 0; i < n; i++) {
            if (allTags.get(i).getName().contains(content)) {
                lists.add(allTags.get(i));
            }
        }
        if (lists.size() == 0) {
            System.out.println("No feature.Tag Found!");
        } else {
            for(int i =0; i<lists.size(); i++){
                int number = i + 1;
                System.out.println("feature.Tag#" + number + ": " + lists.get(i).getName());
                System.out.println("Events with this tag: ");
                FeatureEvents(lists.get(i));
                System.out.println("-----------------------------------------------------");
            }
        }
    }

}
