package main;

import alert.AlertManager;
import event.Event;
import feature.Memo;
import feature.Tag;
import user.CalendarManager;
import user.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReadWriteCSV {
    /**
     * Reads the text event data and store them into the events list.
     *
     * @return - the list of all the events stored in the event data txt file.
     */
    private static Searching searching = new Searching();

    public static ArrayList<Event> readEventData(String path, CalendarManager calendarManager) {
        ArrayList<Event> events = new ArrayList<>();
        File eventFile = new File(path);
        if (eventFile.exists()) {
            try {
                Scanner scanner = new Scanner(eventFile);
                while (scanner.hasNextLine()) {
                    String curr_line = scanner.nextLine().trim();
                    String[] attributes = curr_line.split(",");
                    if (attributes.length == 4) {
                        String eventName = attributes[0].trim();
                        String startTime = attributes[1].trim();
                        String endtime = attributes[2].trim();
                        String eventId = attributes[3].trim();

                        Event curr_event = new Event(eventName, startTime, endtime, eventId);
                        events.add(curr_event);
                    } else {
                        System.out.println("Invalid Line:");
                        System.out.println(curr_line);
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        calendarManager.eventManager.allEvents = events;
        return events;
    }

    /**
     * Writes the events list into the event data file.
     *
     * @param allEvents - the list of all events to save into the event data file
     * @return - true if the write was successful, false otherwise.
     */
    public static boolean writeEventData(ArrayList<Event> allEvents, String path) {
        try {
            File eventFile = new File(path);
            if (!eventFile.exists()) {
                if (!eventFile.createNewFile()) {
                    return false;
                }
            }
            FileWriter writer = new FileWriter(eventFile);
            for (Event curr_event : allEvents) {
                List<String> curr_line_lst = Arrays.asList(
                        curr_event.getEventName(),
                        curr_event.getEventStartTime(),
                        curr_event.getEventEndTime(),
                        curr_event.getEventId()
                );
                writer.write(String.join(",", curr_line_lst) +
                        System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static ArrayList<Memo> readMemoData(String path, CalendarManager calendarManager){
        File memoFile = new File(path);
        ArrayList<Memo> memos = new ArrayList<>();
        if (memoFile.exists()) {
            try {
                Scanner scanner = new Scanner(memoFile);
                while (scanner.hasNextLine()) {
                    String curr_line = scanner.nextLine().trim();
                    String[] attributes = curr_line.split(",");
                    if (attributes.length >= 2) {
                        String memoContent = attributes[0].trim();;
                        Memo newlymemo = new Memo();
                        newlymemo.setContent(memoContent);
                        for(int i = 1; i < attributes.length; i++){
                            searching.calendarManager = calendarManager;
                            newlymemo.addEventtoMemo(searching.searchById(attributes[i].trim())); }
                        memos.add(newlymemo);
                    } else {
                        System.out.println("Invalid Line:");
                        System.out.println(curr_line); } }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace(); }
        } else {
            System.out.println("Data file not found. No memo is created."); }
        calendarManager.memoManager.allMemos = memos;
        return memos; }



    public static boolean writeMemotData(String path, CalendarManager calendarManager) {
        try {
            File memoFile = new File(path);
            if (!memoFile.exists()) {
                if (!memoFile.createNewFile()) { return  false; } }
            FileWriter writer = new FileWriter(memoFile);
            for (Memo curr_memo : calendarManager.memoManager.allMemos) {
                ArrayList<String> ids = new ArrayList<>();
                for (int i = 0; i < curr_memo.getEvents().size(); i++){
                    ids.add(curr_memo.getEvents().get(i).getEventId()); }
                String newList = "";
                for(int i = 0; i < ids.size(); i++){
                    if(i == ids.size() -1){
                        newList = newList.concat(ids.get(i));
                    }else{
                        newList = ids.get(i).concat(","); } }
                String datas = "";
                datas =  datas.concat(curr_memo.getContent()).concat(",").concat(newList);
                writer.write(datas + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();File userFile = new File(path); }
        return true; }

    public static ArrayList<Tag> readTagData(String path, CalendarManager calendarManager) {
        ArrayList<Tag> tags = new ArrayList<>();
        File tagFile = new File(path);
        if (tagFile.exists()) {
            try {
                Scanner scanner = new Scanner(tagFile);
                while (scanner.hasNextLine()) {
                    String curr_line = scanner.nextLine().trim();
                    String[] attributes = curr_line.split(",");
                    if (attributes.length >= 2) {
                        String tagContent = attributes[0].trim();;
                        Tag newlytag = new Tag();
                        newlytag.setName(tagContent);
                        for(int i = 1; i < attributes.length; i++){
                            searching.calendarManager = calendarManager;
                            newlytag.addEvent(searching.searchById(attributes[i].trim()));
                        }
                        tags.add(newlytag);
                    } else {
                        System.out.println("Invalid Line:");
                        System.out.println(curr_line);
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("tagData file not found. No tag is created.");
        }
        calendarManager.tagManager.allTags = tags;
        return tags;
    }



    public static boolean writeTagData(String path, CalendarManager calendarManager){
        try {
            File tagFile = new File(path);
            if (!tagFile.exists()) {
                if (!tagFile.createNewFile()) {
                    return false;
                }
            }
            FileWriter writer = new FileWriter(tagFile);
            for (Tag curr_tag : calendarManager.tagManager.allTags) {
                ArrayList<String> ids = new ArrayList<>();
                for (int i =0; i < curr_tag.getEvents().size(); i++){
                    ids.add(curr_tag.getEvents().get(i).getEventId());
                }
                String newList = " ";
                for(int i = 0; i < ids.size(); i++){
                    if(i == ids.size() -1){
                        newList = newList.concat(ids.get(i));
                    }else{
                        newList = ids.get(i).concat(",");
                    }
                }
                String datas = "";
                datas =datas.concat(curr_tag.getName().concat(",").concat(newList));
                writer.write(datas + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();File userFile = new File(path);
        }
        return true;
    }

    public static boolean writeCalenderData(User user, String path) {
        try {
            File eventFile = new File(path);
            if (!eventFile.exists()) {
                if (!eventFile.createNewFile()) {
                    return false;
                }
            }
            FileWriter writer = new FileWriter(eventFile);
            int i = 1;
            for (CalendarManager calendar : user.calendars){
                List<String> curr_line_lst = Arrays.asList(user.getUid(), Integer.toString(i));
                writer.write(String.join(",", curr_line_lst) +
                        System.lineSeparator());
                ReadWriteCSV.writeEventData(calendar.eventManager.allEvents, "./data/"+
                        user.getUid()+ "_"+ i + "_eventData.txt");
                ReadWriteCSV.writeMemotData("./data/"+
                        user.getUid()+ "_"+ i + "_memoData.txt", calendar);
                ReadWriteCSV.writeTagData("./data/"+
                        user.getUid()+ "_"+ i + "_tagData.txt", calendar);
                AlertManager.writeAlertData("./data/"+
                        user.getUid()+ "_"+ i + "_alertData.txt", calendar);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static ArrayList<CalendarManager> readCalendarData(String path, User user) {
        ArrayList<CalendarManager> calendars = new ArrayList<>();
        File eventFile = new File(path);
        if (eventFile.exists()) {
            try {
                Scanner scanner = new Scanner(eventFile);
                while (scanner.hasNextLine()) {
                    String curr_line = scanner.nextLine().trim();
                    String[] attributes = curr_line.split(",");
                    if (attributes.length == 2) {
                        String num = attributes[1].trim();
                        CalendarManager calendarManager = new CalendarManager(num);
                        calendars.add(calendarManager);
                        ReadWriteCSV.readEventData("./data/"+
                                user.getUid()+ "_"+ num + "_eventData.txt", calendarManager);
                        ReadWriteCSV.readMemoData("./data/"+
                                user.getUid()+ "_"+ num + "_memoData.txt", calendarManager);
                        ReadWriteCSV.readTagData("./data/"+
                                user.getUid()+ "_"+ num + "_tagData.txt", calendarManager);
                        AlertManager.readAlertData("./data/"+
                                user.getUid()+ "_"+ num + "_alertData.txt", calendarManager);
                    } else {
                        System.out.println("Invalid Line:");
                        System.out.println(curr_line);
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            return null;
        }
        user.calendars = calendars;
        return calendars;
    }

}
