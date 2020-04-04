package series;

import event.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SeriesIO {

    protected static String LINKED_DATA_FILE = "./data/linkedData.csv";

    protected static ArrayList<Series> allSeries = new ArrayList<>();

    protected static ArrayList<String> allSeriesId = new ArrayList<>();

    protected static ArrayList<String> allSeriesName = new ArrayList<>();

    public SeriesIO() {}

    public static ArrayList<Series> readSeriesData() {
        ArrayList<Series> series = new ArrayList<>();
        File seriesFile = new File(LINKED_DATA_FILE);
        if (seriesFile.exists()) {
            try {
                Scanner scanner = new Scanner(seriesFile);
                while (scanner.hasNextLine()) {
                    String curr = scanner.nextLine().trim();
                    String[] attr = curr.split(",");
                    String seriesId = attr[0];
                    String seriesName = attr[1];
                    ArrayList<String> seriesEventsId = new ArrayList<>(Arrays.asList(attr).subList(2, attr.length));

                    allSeriesId.add(seriesId);
                    allSeriesName.add(seriesName);

                    Series currSeries = new Series(seriesName, seriesId);
                    currSeries.eventIdArray = seriesEventsId;
                    series.add(currSeries);
                    System.out.println("Line is invalid");
                    System.out.println(curr);
                }scanner.close();
            } catch (FileNotFoundException ex){
                ex.printStackTrace();
            }
        } else {
            System.out.println("No Valid Series File Found");
        }
        allSeries = series;
        return series;
    }

    /**
     * Writes the Series list into the event data file.
     *
     * @param allSeries - the list of all Series to save into the event data file
     * @return - true if the write was successful, false otherwise.
     */
    public static boolean writeSeriesData(ArrayList<Series> allSeries) {
        try {
            File SeriesFile = new File(LINKED_DATA_FILE);
            if (!SeriesFile.exists()) {
                if (!SeriesFile.createNewFile()) {
                    return false;
                }
            }
            FileWriter writer = new FileWriter(SeriesFile);
            for (Series curr : allSeries) {
                List<String> curr_line_lst = Arrays.asList(
                        curr.getSeriesId(),
                        curr.getSeriesName()
                );

                curr_line_lst.addAll(curr.getSeriesEventsId());

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
