package features;

import event.Event;
import event.EventManager;
import event.ReadWriteCSV;
import event.Searching;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class MemoManager extends FeatureManager {
    /**Memos' Manager class!
     * We can view all the memos for all events, view all memos for a certain event,
     * read memos form data file and store(write) memos to data file!
     *
     * */

    public ArrayList<Memo> allMemos = new ArrayList<>();
    /** Path may change for different computer!*/



    /** User can view all memos they created. */
    public void viewMemos(){
        int n = allMemos.size();
        if(n  == 0){
            System.out.println("  ");
            System.out.println("You don't have any memos!"); }
        for (int i = 0; i < n; i++) {
            int number = i + 1;
            System.out.println("Memo" + number + ": " + allMemos.get(i).getContent());
            System.out.println("With events: ");
            if (allMemos.get(i).getEvents() == null){
                System.out.println("No event yet!!");
            }else{FeatureEvents(allMemos.get(i));}
            System.out.println(" "); } }


    /**View memos for a certain even!*/
    public void MemoForEvent(Event event){

        int n = allMemos.size();
        if (n == 0){
            System.out.println("No memo yet!");
        }else{
            int num = 0;
            boolean indicator = false;
            ArrayList<String> printMemos = new ArrayList<>();

            for (Memo m : allMemos) {
                if (m.getIds().contains(event.getEventId())) {
                    indicator = true;
                    num++;
                    printMemos.add("Memo" + num + ": " + m.getContent()); } }
            if (indicator) {
                for (String printMemo : printMemos) {
                    System.out.println(printMemo); }
            } else {
                System.out.println("You don't have any memo yet!"); } } }

    public void changeMemoContent(){
        for (int i = 0; i < allMemos.size(); i++){
            int num  = 1+i;
            System.out.println("Memo" + num+ ": " + allMemos.get(i).getContent());
        }
        System.out.println("Please type the memo number you want to change: ");
        Scanner scanner1 = new Scanner(System.in);
        String memo = scanner1.nextLine().trim();
        int num = parseInt(memo);
        System.out.println("Please enter the newcontent:");
        String newcontent= scanner1.nextLine().trim();
        allMemos.get(num-1).changeContent(newcontent);
        System.out.println("Changed!");
    }
    public void removeEvent(){
        for (int i = 0; i < allMemos.size(); i++){
            int num  = 1+i;
            System.out.println("Memo" + num+ ": " + allMemos.get(i).getContent());
        }
        System.out.println("Please type the memo number you want to change: ");
        Scanner scanner1 = new Scanner(System.in);
        String memo = scanner1.nextLine().trim();
        int num = parseInt(memo);
        EventManager.viewOptions(allMemos.get(num-1).getEvents());
        String eventnum= scanner1.nextLine().trim();
        int eventindext = parseInt(eventnum);
        allMemos.get(num-1).deleteEvent(allMemos.get(num-1).getEvents().get(eventindext-1));
        System.out.println("Changed!");
    }

    public void addNewEvent(){
        for (int i = 0; i < allMemos.size(); i++){
            int num  = 1+i;
            System.out.println("Memo" + num+ ": " + allMemos.get(i).getContent());
        }
        System.out.println("Please type the memo number you want to add event to: ");
        Scanner scanner1 = new Scanner(System.in);
        String memo = scanner1.nextLine().trim();
        int num = parseInt(memo);
        EventManager.viewOptions(allMemos.get(num-1).getEvents());
        String eventnum= scanner1.nextLine().trim();
        int eventindext = parseInt(eventnum);
        allMemos.get(num-1).addEventtoMemo(allMemos.get(num-1).getEvents().get(eventindext-1));
        System.out.println("Changed!");
    }
    public void deleteMemo(){
        for (int i = 0; i < allMemos.size(); i++){
            int num  = 1+i;
            System.out.println("Memo" + num+ ": " + allMemos.get(i).getContent());
        }
        System.out.println("Please type the memo number you want to delete: ");
        Scanner scanner1 = new Scanner(System.in);
        String memo = scanner1.nextLine().trim();
        int num = parseInt(memo);
        allMemos.remove(num-1);
        System.out.println("Deleted!");
    }

    public void findMemo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input part of the content of the memo you want to search:");
        String content = scanner.nextLine().trim();
        ArrayList<Memo> lists = new ArrayList<>();
        int n = allMemos.size();
        for(int i=0;i < n; i ++){
            if(allMemos.get(i).getContent().contains(content)){
                lists.add(allMemos.get(i));
            }
        }
        if(lists.size() ==  0){
            System.out.println("No Memo Found!");
        }else{
            for(int i =0; i<lists.size(); i++){
                int number = i + 1;
                System.out.println("Memo" + number + ": " + lists.get(i).getContent());
                System.out.println("Events with this memo: ");
                FeatureEvents(lists.get(i));
                System.out.println("-----------------------------------------------------");
            }
        }
    }




}
