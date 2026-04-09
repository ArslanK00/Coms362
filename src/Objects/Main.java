package src.Objects;

/**
 *  Add revenue/expense records, and calculate revenue.
 */
public class Main {
    public static void main(String[] args) {
        Event event = new Event("EVT100", "WrestleMania");

        // Revenue (positive) and expense (negative) samples
        event.addRecord(new Record(40000.0, "PPV sales"));
        event.addRecord(new Record(25000.0, "Live ticket sales"));
        event.addRecord(new Record(-10000.0, "Arena rental"));
        event.addRecord(new Record(-5000.0, "Staff and crew"));

        double revenue = event.calculateRevenue();
        System.out.printf("Total revenue for %s: $%.2f%n", event.getEventId(), revenue);
    }
}
