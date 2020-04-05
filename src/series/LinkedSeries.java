package series;

import java.util.ArrayList;

public class LinkedSeries extends Series {
    /*
    * An series.LinkedSeries. user.user.User select Events to add to this series.
    *
    */
//    protected static String LINKED_DATA_FILE = "./data/linkedData.csv";

//    protected ArrayList<event.Event> eventArray;

    protected ArrayList<String> eventIdArray = new ArrayList<>();

    private String name;

    public LinkedSeries(String seriesName, String seriesId) {
        super(seriesName, seriesId);
    }

}
