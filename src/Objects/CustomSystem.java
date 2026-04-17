package Objects;
import java.util.ArrayList;

import Objects.RecordTypes.Record;

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

    /**
     * @author Eleena Rath
     * @param eventIndex
     * @param recordIndex
     */
    public void deleteRecordFromEvent(int eventIndex, int recordIndex){
        getEvent(eventIndex).deleteRecord(recordIndex);
    }

 
    /**
     * @author Eleena Rath
     * @param index
     * @return
     */
    public String listEventRecords(int index){
        Event event = getEvent(index);
        return event.listRecords();
    }

    /**
     * @author Eleena Rath
     * @return
     */
    public String listEvents(){
        String eventsList = "";
        for (int i = 0; i < events.size(); i++){
            int index = i + 1;
            eventsList += index + "-" + events.get(i).getName() + "\n";
        }

        return eventsList;
    }

        /**
     * @author Matayas Durr
     * Calculates total revenue across all events
     * @return total revenue of all events
     */
    public float calculateAllRevenue() {
        float total = 0;
        for (Event event : events) {
            total += event.calculateRevenue();
        }
        return total;
    }

}