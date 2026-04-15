package src.Objects;

import java.util.ArrayList;

/*
* @author Jamey Nguyen
*/
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
}
