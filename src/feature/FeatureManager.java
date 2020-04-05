package feature;

import event.Event;

public class FeatureManager {
    /**
     * feature.features.feature.Memo manager class.
     * user.user.User can view a list of memo along with the corresponding events.
     * user.user.User can remove an event for a specific memo, move an event from one memo to one another.
     */


    /** user.User can view all events inside of a feature. */
    public void FeatureEvents(Feature f){
        int n = f.getEvents().size();
        for(int i = 0; i < n; i++){
            Event event = f.getEvents().get(i);
            int num = i+1;
            System.out.println("event.Event"+num+ ": " + event.showEvent().get(0));
            System.out.println("Start at: " + event.showEvent().get(1));
            System.out.println("End at: " + event.showEvent().get(2));
        }
    }

}
