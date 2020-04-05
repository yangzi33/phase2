package feature;

import event.Event;

import java.util.ArrayList;

public class Memo extends Feature {
    /** The content of the memo.*/
    public String Content = "";
    /** All  events inside the memo.*/
    public  ArrayList<Event> memoEvents = new ArrayList<>();

    public void deleteEvent(Event event){
        memoEvents.remove(event);}

    public ArrayList<Event> getEvents(){
        return memoEvents;
    }

    public ArrayList<String> getIds(){
        ArrayList<String> ids = new ArrayList<>();
        for (int i = 0; i < memoEvents.size(); i++){
            ids.add(memoEvents.get(i).getEventId());
        }
        return ids;
    }

    public void setContent(String content) {
        Content = content;
    }


    public void changeContent(String newContent) {
        Content = newContent;
    }

    public String getContent(){
        return Content;
    }

    public void addEventtoMemo(Event event){
        if (memoEvents.contains(event)){
            System.out.println("This event is already have this memo!");

        }else{
        memoEvents.add(event);}
    }




}
