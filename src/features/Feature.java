package features;

import event.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Feature {
    /*
    *features.Feature of an event, has sub class memo and tag
    * */


    /** Abstract get all events inside the feature.*/
    public abstract ArrayList<Event> getEvents();
    /** Abstract get all event ids inside the feature.*/
    public abstract ArrayList<String> getIds();



}
