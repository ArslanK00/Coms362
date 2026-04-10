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

    //gets an event based on its index within the events ArrayList
    public Event getEvent(int index){
        return events.get(index-1);
    }

    public void addRecordToEvent(int index, Record record){
        events.get(index-1).addRecord(record);
    }

    public String listEventRecords(int index){
        Event event = getEvent(index);
        return event.listRecords();
    }

    public String listEvents(){
        String eventsList = "";
        for (int i = 0; i < events.size(); i++){
            int index = i + 1;
            eventsList += index + "-" + events.get(i).getName() + "\n";
        }

        return eventsList;
    }

}