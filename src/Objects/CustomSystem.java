package Objects;

import java.io.Serializable;
import java.util.ArrayList;

import Objects.RecordTypes.AbstractRecord;
import Objects.RecordTypes.MerchandiseController;
import Objects.RecordTypes.MerchandiseSale;
import Objects.RecordTypes.Record;
import Objects.RecordTypes.RecordEnum;
import Objects.RecordTypes.MerchandiseSale;

public class CustomSystem implements Serializable{
    ArrayList<Event> events;
    ArrayList<Record> records;
    ArrayList<Employee> employees; // Modification: by Eleena R.
    ArrayList<AbstractRecord> abstractRecords;
    public CustomSystem() {
        events = new ArrayList<Event>();
        records = new ArrayList<Record>();
        employees = new ArrayList<Employee>(); // Modification: by Eleena R.
        abstractRecords = new ArrayList<AbstractRecord>();
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

    public void addRecordToEvent(Event event, Record record) {
        event.addRecord(record);
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

    public void deleteRecord(int recordIndex) {
        Record recordToDelete = records.get(recordIndex - 1);

        records.remove(recordIndex - 1);

        for (Event event : events) {
            event.deleteRecord(recordToDelete);
        }
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

    public void addMerchandiseFromDatabase()
    {
        MerchandiseController MC = new MerchandiseController(false);
        for(MerchandiseSale ms : MC.TurnAllMerchandiseToRecords(false))
        {
            abstractRecords.add((AbstractRecord)ms);
        }
    }
    public void addAbstractRecord(AbstractRecord ar)
    {
            abstractRecords.add(ar);
    }

    public ArrayList<AbstractRecord> getAbstractRecords()
    {
        return abstractRecords;
    }

    public void listAbstractRecords()
    {
        int index;
        for (int i = 0; i < abstractRecords.size(); i++){
            index = i + 1;
            System.out.println(index + "-" + abstractRecords.get(i).toString());
        }
    }



    public void deleteEmployee(int i)
    {
        Employee deleting = employees.get(i-1);
        ArrayList<Employee> newArr = new ArrayList<>();
        for(Employee r : employees)
        {
            if(!r.equals(deleting))
            {
                newArr.add(r);
            }
        }
        this.employees = newArr;
    }

    //     public void editRecord(int i, int choice)
    // {
    //     Record deleting = records.get( i - 1);
    //     ArrayList<Record> newArr = new ArrayList<>();
    //     for(Record r : records)
    //     {
    //         if(!r.equals(deleting))
    //         {
    //             newArr.add(r);
    //         }
    //     }
    //     switch()
    //     deleting.

    //     this.records = newArr;
        
    // }
    
    public void replaceRecord(int recordIndex, Record newRecord) {
        Record oldRecord = records.get(recordIndex - 1);

        records.set(recordIndex - 1, newRecord);

        for (Event event : events) {
            event.replaceRecord(oldRecord, newRecord);
        }
    }
}
