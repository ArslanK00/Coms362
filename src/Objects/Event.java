package src.Objects;
import java.util.ArrayList;

import src.Objects.RecordTypes.Record;

public class Event {
     String name;
    String venue;
    
    ArrayList<Record> records;

    public Event(String name, String venue) {
        this.name = name;
        this.venue = venue;
        this.records = new ArrayList<Record>();
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    /**
     * @author Eleena Rath
     * @param record
     */
    public void deleteRecord(Record record) {
        records.remove(record);
    }

    public String getName() {
        return name;
    }

    public String getVenue() {
        return venue;
    }

    /**
     * @author Eleena Rath
     */
    @Override
    public String toString(){
        String summary = "Event Name: " + name + "\n"
        + "Venue: " +  venue + "\n";

        return summary;
    }
}
