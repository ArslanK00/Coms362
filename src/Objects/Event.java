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
     * @author Matayas Durr
     * @return list of records for this event
     */
    public ArrayList<Record> getRecords() {
        return records;
    }

    /**
     * @author Matayas Durr
     * Calculates total revenue for this event
     * @return total revenue from all records in this event
     */
    public float calculateRevenue() {
        float total = 0;
        for (Record record : records) {
            total += record.getCost();
        }
        return total;
    }

    public String listRecords(){
        String recordsList = "";
        for (int i = 0; i < records.size(); i++){
            int index = i + 1;
            recordsList += index + "-" + records.get(i);
        }
        return recordsList;
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
