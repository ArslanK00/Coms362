package Objects;

import java.util.ArrayList;

import Objects.RecordTypes.Record;

public class CustomSystem {
    ArrayList<Event> events;
    ArrayList<Record> records;
    ArrayList<Employee> employees; // Modification: by Eleena R.

    public CustomSystem() {
        events = new ArrayList<Event>();
        records = new ArrayList<Record>();
        employees = new ArrayList<Employee>(); // Modification: by Eleena R.
    };

    public void addEvent(Event event) {
        events.add(event);
    };

    // gets an event based on its index within the events ArrayList
    public Event getEvent(int index) {
        return events.get(index - 1);
    }

    public void addRecordToEvent(int index, Record record) {
        events.get(index - 1).addRecord(record);
        records.add(record);
    }

    /**
     * @author Eleena Rath
     * @param eventIndex
     * @param recordIndex
     */
    public void deleteRecordFromEvent(int eventIndex, int recordIndex) {
        getEvent(eventIndex).deleteRecord(recordIndex);
    }

    

    /**
     * @author Eleena Rath
     * @param index
     * @return
     */
    public String listEventRecords(int index) {
        Event event = getEvent(index);
        return event.listRecords();
    }

    /**
     * @author Eleena Rath
     * @return
     */
    public String listEvents() {
        String eventsList = "";
        for (int i = 0; i < events.size(); i++) {
            int index = i + 1;
            eventsList += index + "-" + events.get(i).getName() + "\n";
        }

        return eventsList;
    }

    /**
     * Gets the total number of events in the system
     * 
     * @return number of events
     */
    public int getEventCount() {
        return events.size();
    }

    /**
     * @author Matayas Durr
     *         Calculates total revenue across all events
     * @return total revenue of all events
     */
    public float calculateAllRevenue() {
        float total = 0;
        for (Event event : events) {
            total += event.calculateRevenue();
        }

        // Count system-level records only when they are not already attached to an event.
        for (Record record: records){
            boolean recordBelongsToEvent = false;
            for (Event event : events) {
                if (event.records.contains(record)) {
                    recordBelongsToEvent = true;
                    break;
                }
            }

            if (!recordBelongsToEvent) {
                total += record.getCost();
            }
        }
        return total;
    }

    /**
     * @author Eleena Rath
     *         Description: lists the employees currently in the database
     */
    public void listEmployees() {
        int index;
        for (int i = 0; i < employees.size(); i++) {
            index = i + 1;
            System.out.println(index + "-" + employees.get(i).getName());
        }
    }

    /**
     * @author Eleena Rath
     * @param index
     * @return Employee
     */
    public Employee getEmployee(int index) {
        return employees.get(index - 1);
    }

    /**
     * @author Eleena Rath
     * @param employee
     *                 Description: Adds an employee to the database
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    /**
     * @author Eleena Rath
     * @return number of employees in the database
     */
    public int numberOfEmployees() {
        return employees.size();
    }

    /**
     * @author Eleena Rath
     */
    public void listRecords(){
        int index;
        for (int i = 0; i < records.size(); i++){
            index = i + 1;
            System.out.println(index + "-" + records.get(i).toString());
        }
    }

    /**
     * @author Eleena Rath
     * @param index
     * @return
     */
    public Record getRecord(int index){
        return records.get(index - 1);
    }

    /**
     * @author Eleena Rath
     * @param record
     */
    public void addRecord(Record record){
        records.add(record);
    }
}
