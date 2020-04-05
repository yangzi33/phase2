package user;

import alert.AlertManager;
import event.EventManager;
import feature.MemoManager;
import feature.TagManager;


public class CalendarManager {
    public AlertManager alertManager;
    public EventManager eventManager;
    public MemoManager memoManager;
    public TagManager tagManager;
    public String address;

    public CalendarManager(String s){
        alertManager = new AlertManager();
        eventManager = new EventManager();
        memoManager = new MemoManager();
        tagManager = new TagManager();
        address = s;
    }

}
