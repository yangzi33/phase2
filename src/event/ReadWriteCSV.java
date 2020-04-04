package event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReadWriteCSV {
    protected static String EVENT_DATA_FILE = "./data/eventData.csv";
    /**
     * Reads the text event data and store them into the events list.
     *
     * @return - the list of all the events stored in the event data txt file.
     */
    public static ArrayList<Event> readEventData() {
        ArrayList<Event> events = new ArrayList<>();
        File eventFile = new File(EVENT_DATA_FILE);
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
            System.out.println("eventData.csv file not found. No event is created.");
        }
        EventManager.allEvents = events;
        return events;
    }

    /**
     * Writes the events list into the event data file.
     *
     * @param allEvents - the list of all events to save into the event data file
     * @return - true if the write was successful, false otherwise.
     */
    public static boolean writeEventData(ArrayList<Event> allEvents) {
        try {
            File eventFile = new File(EVENT_DATA_FILE);
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
}
