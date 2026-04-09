package src.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a WWE event and holds all financial records.
 */
public class Event {
    private final String eventId;
    private final String name;
    private final List<Record> records = new ArrayList<>();

    public Event(String eventId, String name) {
        this.eventId = eventId;
        this.name = name;
    }

    public String getEventId() { return eventId; }
    public String getName() { return name; }

    public void addRecord(Record record) { records.add(record); }

    public List<Record> getRecords() { return records; }

    /** Adds up all costs; positive is revenue, negative is expense. */
    public double calculateRevenue() {
        double total = 0.0;
        for (Record record : records) {
            total += record.getCost();
        }
        return total;
    }
}
