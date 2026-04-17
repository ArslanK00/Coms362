package Objects;
import java.util.ArrayList;

import Objects.RecordTypes.Record;

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

    public Record getRecord(int index){
        return records.get(index-1);
    }

    /**
     * @author Eleena Rath
     * @param record
     */
    public void deleteRecord(int index) {
        records.remove(index-1);
    }

    /**
     * @author EleenaRath
     * @return
     */
    public int numberOfRecords(){
        return records.size();
    }

    public String getName() {
        return name;
    }

    public String getVenue() {
        return venue;
    }

    /**
     * @author Eleena Rath
     * @return
     */
    public String listRecords(){
        String recordsList = "";
        for (int i = 0; i < records.size(); i++){
            int index = i + 1;
            recordsList += index + "-" + records.get(i);
        }
        return recordsList;
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
