package src;

import src.Objects.CustomSystem;
import src.Objects.Event;
import src.Objects.RecordTypes.LiveEventTicket;
import src.Objects.RecordTypes.PayPerViewTicket;

/**
 * @author Matayas Durr
 */
public class wweRevenueCalculator {

    static CustomSystem wweSystem = new CustomSystem();

    public static void main(String[] args) {
        System.out.println("Welcome to the Ticketing System!");

        seedTestData();

        while (true) {
            displayMenu();
            String choice = System.console().readLine();

            switch (choice) {
                case "1":
                    addEventToSystem();
                    break;
                case "2":
                    viewEvents();
                    break;
                case "3":
                    calculateAllRevenue();
                    break;
                case "4":
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    /**
     * @author Matayas Durr
     * Displays the main menu
     */
    private static void displayMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1 Add an event to the system");
        System.out.println("2 View Events");
        System.out.println("3 Calculate all Revenue");
        System.out.println("4 Exit");
    }

    /**
     * @author Matayas Durr
     * Adds a new event to the system
     */
    private static void addEventToSystem() {
        System.out.println("Enter the event name:");
        String eventName = System.console().readLine();

        System.out.println("Enter the event venue:");
        String eventVenue = System.console().readLine();

        Event newEvent = new Event(eventName, eventVenue);
        wweSystem.addEvent(newEvent);

        System.out.println("Event added to the system: " + eventName + " at " + eventVenue);
    }

    /**
     * @author Matayas Durr
     * Displays all events currently in the system
     */
    private static void viewEvents() {
        String events = wweSystem.listEvents();

        if (events.length() == 0) {
            System.out.println("No events in the system.");
            return;
        }

        System.out.println("Current Events:");
        System.out.println(events);
    }

    /**
     * @author Matayas Durr
     * Displays total revenue across all events
     */
    private static void calculateAllRevenue() {
        float totalRevenue = wweSystem.calculateAllRevenue();
        System.out.println("Total revenue across all events: " + totalRevenue);
    }

    /**
     * @author Matayas Durr
     * Seeds test data so revenue calculation has records to total
     */
    private static void seedTestData() {
        Event event1 = new Event("WrestleMania", "Las Vegas");
        Event event2 = new Event("Royal Rumble", "St. Louis");

        wweSystem.addEvent(event1);
        wweSystem.addEvent(event2);

        LiveEventTicket ticket1 = new LiveEventTicket(1, "Front Row Ticket");
        ticket1.setCost(150.00f);

        PayPerViewTicket ticket2 = new PayPerViewTicket(2, "Premium PPV Purchase");
        ticket2.setCost(59.99f);

        wweSystem.addRecordToEvent(1, ticket1);
        wweSystem.addRecordToEvent(1, ticket2);
    }
}