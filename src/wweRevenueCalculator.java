package src;

import src.Objects.CustomSystem;
import src.Objects.Event;
import src.Objects.RecordTypes.LiveEventTicket;
import src.Objects.RecordTypes.PayPerViewTicket;

public class wweRevenueCalculator {

    public static void main(String[] args){
        CustomSystem wweSystem = new CustomSystem();
        LiveEventTicket ticket1 = new LiveEventTicket(1, "ticket1");
        PayPerViewTicket ticket2 = new PayPerViewTicket(2, "ticket2");

        Event event1 = new Event("Event1", "N/A");
        Event event2 = new Event("Event2", "N/A");
        Event event3 = new Event("Event3", "N/A");
        wweSystem.addEvent(event1);
        wweSystem.addEvent(event2);
        wweSystem.addEvent(event3);

        System.out.println(wweSystem.listEvents());
        System.out.println(wweSystem.getEvent(3)); //This should return the summary of event3

        wweSystem.addRecordToEvent(3, ticket1); //Adding ticket1 to event3
        wweSystem.addRecordToEvent(3, ticket2); //Adding ticket2 to event3

        System.out.println(wweSystem.listEventRecords(3));


        // System.out.println("Welcome to the Ticketing System!");
        // System.out.println("Please select an option:");
        // System.out.println("1. Add an event to the system");
        // System.out.println("2. Upload a Live-Event View ticket");
        // System.out.println("3. Exit");

        // String choice = System.console().readLine();

        // switch (choice) {
        //     case "1":
        //         AddEventToSystem.main(args);
        //         break;
        //     case "2":
        //         UploadLiveEventTicket.main(args);
        //         break;
        //     case "3":
        //         System.out.println("Exiting the system. Goodbye!");
        //         break;
        //     default:
        //         System.out.println("Invalid option. Please try again.");
        //         break;
        // }
    }
}
