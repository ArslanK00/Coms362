package Objects;

import java.util.ArrayList;
import java.util.List;

import Objects.RecordTypes.AbstractRecord;
import Objects.RecordTypes.Record;
import Objects.Strategies.RevenueCalculationStrategy;
import Objects.Strategies.RevenueOnlyStrategy;
import Objects.Strategies.TotalRevenueStrategy;

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

    public void addRecord(Record record) {
        records.add(record);
    }

    public Record getRecord(int index) {
        return records.get(index - 1);
    }

    /**
     * @author Eleena Rath
     * @param record
     */
    public void deleteRecord(int index) {
        records.remove(index - 1);
    }

    /**
     * @author EleenaRath
     * @return
     */
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

    /**
     * @author Eleena Rath
     * @return
     */
    public String listRecords() {
        String recordsList = "";
        for (int i = 0; i < records.size(); i++) {
            int index = i + 1;
            recordsList += index + "-" + records.get(i);
        }
        return recordsList;
    }

    /**
     * @author Matayas Durr
     *         Calculates total revenue for this event
     * @return total revenue from all records in this event
     */
    public float calculateRevenue() {
        return calculateRevenue(new TotalRevenueStrategy());
    }

    // Added by Matayas Durr: supports sorting records by value
    public void sortByValue() {
        records.sort((a, b) -> Float.compare(b.getCost(), a.getCost()));
    }

    // Added by Matayas Durr: supports sorting records by category
    public void sortByCategory() {
        records.sort((a, b) -> {
            AbstractRecord first = (AbstractRecord) a;
            AbstractRecord second = (AbstractRecord) b;

            String firstCategory = first.getCategory() == null ? "" : first.getCategory();
            String secondCategory = second.getCategory() == null ? "" : second.getCategory();

            return firstCategory.compareToIgnoreCase(secondCategory);
        });
    }

    // Added by Matayas Durr: calculates revenue-only total
    public float calculateRevenueOnly() {
        return calculateRevenue(new RevenueOnlyStrategy());
    }

    public float calculateRevenue(RevenueCalculationStrategy strategy) {
        return strategy.calculateRevenue(records);
    }

    public List<Record> getRecords() {
        return new ArrayList<Record>(records);
    }

    @Override
    public String toString() {
        String summary = "Event Name: " + name + "\n"
                + "Venue: " + venue + "\n";

        return summary;
    }
}
