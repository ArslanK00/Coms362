package src.Objects;
import java.util.ArrayList;

import src.Objects.RecordTypes.Record;

public class CustomSystem {
    ArrayList<Event> events;
    ArrayList<Record> records;
    // We don't need a List of Employees right now

    public CustomSystem() {
        events = new ArrayList<Event>();
        records = new ArrayList<Record>();
    };

    public void addEvent(Event event) {
        events.add(event);
    };

    //gets an event from the 
    public Event getEvent(int index){
        return events.get(index);
    }

    public String listEvents(){
        String eventsList = "";
        for (int i = 0; i < events.size(); i++){
            eventsList += i + events.get(i).getName();
        }

        return eventsList;
    }

}