package Objects;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Objects.RecordTypes.AbstractRecord;
import Objects.RecordTypes.Record;

public class Event {
    String name;
    String venue;
    String arenaName;

    ArrayList<Record> records;

    public Event(String name, String venue) {
        this.name = name;
        this.venue = venue;
        this.arenaName = null;
        this.records = new ArrayList<Record>();
    }

    // New method to record the event in a text database
    public void saveToFile() {
        try (FileWriter writer = new FileWriter("Databases/Events.txt", true)) {
            String arena = (arenaName == null || arenaName.isEmpty()) ? "No Arena Assigned" : arenaName;
            writer.write(name + ", " + venue + ", " + arena + "\n");
        } catch (IOException e) {
            System.out.println("Could not write event to file: " + e.getMessage());
        }
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    public Record getRecord(int index) {
        return records.get(index - 1);
    }

    public void deleteRecord(int index) {
        records.remove(index - 1);
    }

    public int numberOfRecords() {
        return records.size();
    }

    public String getName() {
        return name;
    }

    public String getVenue() {
        return venue;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public boolean hasArena() {
        return arenaName != null && !arenaName.isEmpty();
    }

    public String listRecords() {
        String recordsList = "";
        for (int i = 0; i < records.size(); i++) {
            int index = i + 1;
            recordsList += index + "-" + records.get(i) + "\n";
        }
        return recordsList;
    }

    public float calculateRevenue() {
        float total = 0;
        for (Record record : records) {
            total += record.getCost();
        }
        return total;
    }

    public void sortByValue() {
        records.sort((a, b) -> Float.compare(b.getCost(), a.getCost()));
    }

    public void sortByCategory() {
        records.sort((a, b) -> {
            AbstractRecord first = (AbstractRecord) a;
            AbstractRecord second = (AbstractRecord) b;

            String firstCategory = first.getCategory() == null ? "" : first.getCategory();
            String secondCategory = second.getCategory() == null ? "" : second.getCategory();

            return firstCategory.compareToIgnoreCase(secondCategory);
        });
    }

    public float calculateRevenueOnly() {
        float total = 0;
        for (Record record : records) {
            AbstractRecord currentRecord = (AbstractRecord) record;
            if (currentRecord.isRevenue()) {
                total += record.getCost();
            }
        }
        return total;
    }

    @Override
    public String toString() {
        String summary = "Event Name: " + name + "\n"
                + "Venue: " + venue + "\n";
        return summary;
    }

    public static Event fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length >= 2) {
            Event e = new Event(parts[0].trim(), parts[1].trim());
            if (parts.length >= 3 && !parts[2].trim().equals("No Arena Assigned")) {
                e.setArenaName(parts[2].trim());
            }
            return e;
        }
        return null;
    }

    public ArrayList<Record> getRecordsList() {
        return this.records;
    }
}