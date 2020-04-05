package series;

import event.Event;
import user.CalendarManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class NewSeries {
    public CalendarManager calendarManager;

    public NewSeries(CalendarManager c) {
        calendarManager = c;

    }
    /** Take user's input of series.LinkedSeries' name and event.
     *
     *
     */
    public void linkedSeriesInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the name of your series:");
        String seriesName = scanner.nextLine();

        ArrayList<Event> events = calendarManager.eventManager.allEvents;
        ArrayList<Event> eventsSelected = new ArrayList<>();

        System.out.println("Please select the events for this series. You have " + Integer.toString(events.size()) +
                " events available.");
        System.out.println("The events available are:");

        for(Event event: events) {
            System.out.println(event.getEventName());
        }

        boolean finished = false;
        while (!finished) {
            String selectedName = scanner.nextLine();

            for (Event event: events) {
                if (event.getEventName().equals(selectedName)){
                    eventsSelected.add(event);
                    events.remove(event);
                };
            }

            System.out.println("Would you like to add more events? Y/N.");
            String userResponse = scanner.nextLine();
            if (userResponse.equals("N")){
                finished = true;
            }
        }


        try{
            LinkedSeriesManager.addNewSeries(seriesName);
            System.out.println("series.Series created.");
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    /** Take user's input of repeating series' name, duration, and frequencies.
     *
     */
    public void repeatingSeriesInput() {
        Scanner scanner = new Scanner(System.in);
        boolean validTime = false;
        String seriesName;
        String startTime = null;
        String endTime = null;

        System.out.println("Please enter the name of your series:");
        seriesName = scanner.nextLine();

        System.out.println("Please set your event start time, in format of DD/MM/YYYY HH:MM");
        startTime = scanner.nextLine();

        System.out.println("Please set your event end time, in format of DD/MM/YYYY HH:MM");
        endTime = scanner.nextLine();
        while (!validTime) {
            System.out.println("Please set your event start time, in format of DD-MM-YYYY HH:MM");
            startTime = scanner.nextLine();

            System.out.println("Please set your event end time, in format of DD-MM-YYYY HH:MM");
            endTime = scanner.nextLine();
            validTime = (Event.TimeFormateCHecker(startTime) && Event.TimeFormateCHecker(endTime));
        }

        boolean ValidFrequency = false;
        System.out.println("Please enter your frequency, ");
        System.out.println("the available frequencies are: daily, weekly, monthly");
        String frequency = scanner.nextLine().toLowerCase();

        while (!ValidFrequency) {
            if (frequency.equals("daily") || frequency.equals("weekly") || frequency.equals("monthly")) {
                ValidFrequency = true;
            } else {
                System.out.println("Please enter a correct frequency.");
                System.out.println("the available frequencies are: daily, weekly, monthly");
                frequency = scanner.nextLine();
            }
        }

        boolean fEndValid = false;
        String frequencyEnd = null;

        while (!fEndValid) {
            System.out.println("When do you want the event repetition to end? ");
            System.out.println("Please enter in format of DD-MM-YYYY");
            frequencyEnd = scanner.nextLine();
            fEndValid = endFormatChecker(frequencyEnd);
        }
        RepeatSeriesManager.addNewSeries(seriesName, startTime, endTime, frequency, frequencyEnd);
        System.out.println("series.Series created.");
    }

    private static boolean endFormatChecker(String time) {
        boolean answer = false;
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setLenient(false);
        try {
            format.parse(time);
            answer = true;
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
        return answer;
    }
}
