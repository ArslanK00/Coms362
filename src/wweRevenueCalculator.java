package src;

import src.Objects.CustomSystem;
import src.Objects.RecordTypes.LiveEventTicket;
import src.Objects.RecordTypes.PayPerViewTicket;

public class wweRevenueCalculator {

CustomSystem system = new CustomSystem();
    public static void main(String[] args){
        LiveEventTicket ticket1 = new LiveEventTicket(1, "ticket1");
        PayPerViewTicket ticket2 = new PayPerViewTicket(2, "ticket2");

        

        // System.out.println(ticket1);
        // System.out.println(ticket2);

        //  System.out.println("Welcome to the Ticketing System!");
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
