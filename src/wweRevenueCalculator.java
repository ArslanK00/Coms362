package src;

import src.Objects.CustomSystem;
import src.Objects.Event;
import src.Objects.RecordTypes.LiveEventTicket;
import src.Objects.RecordTypes.PayPerViewTicket;

public class wweRevenueCalculator {

    static CustomSystem wweSystem = new CustomSystem();
    public static void main(String[] args){
        // LiveEventTicket ticket1 = new LiveEventTicket(1, "ticket1");
        // PayPerViewTicket ticket2 = new PayPerViewTicket(2, "ticket2");

        // Event event1 = new Event("Event1", "N/A");
        // Event event2 = new Event("Event2", "N/A");
        // Event event3 = new Event("Event3", "N/A");
        // wweSystem.addEvent(event1);
        // wweSystem.addEvent(event2);
        // wweSystem.addEvent(event3);

        // System.out.println(wweSystem.listEvents());
        // System.out.println(wweSystem.getEvent(3)); //This should return the summary of event3

        // wweSystem.addRecordToEvent(3, ticket1); //Adding ticket1 to event3
        // wweSystem.addRecordToEvent(3, ticket2); //Adding ticket2 to event3

        // System.out.println(wweSystem.listEventRecords(3));

        //Read all 

        System.out.println("Welcome to the Ticketing System!");
        System.out.println("Please select an option:");
        System.out.println("1 Add an event to the system");
        System.out.println("2 View Events");
        System.out.println("3 Calculate all Revenue");
        System.out.println("4 Exit");

        while(true){

            String choice = System.console().readLine();

            switch (choice) {
                case "1":
                    addEventToSystem();
                    break;
                case "2":
                    //
                    break;
                case "3":
                    //CalculateAll
                    break;
                case "4":
                    System.out.println("Exiting the system. Goodbye!");
                    //Save all records to a file

                    System.exit(1);
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void addEventToSystem(){
        String endUpload = "Y"; 

        while(!endUpload.equalsIgnoreCase("Y"))
        {
            System.out.println("Would you like to add an event to the system? (Y/N)");
            endUpload = System.console().readLine();
            if(endUpload.equalsIgnoreCase("N"))
            {
                return;
            }
            else if(endUpload.equalsIgnoreCase("Y")){
                break;
            }
        }

            System.out.println("Enter the event name:");
            String eventName = System.console().readLine();


            System.out.println("Enter the event venue:");
            String eventVenue = System.console().readLine();

            Event newEvent = new Event(eventName, eventVenue);
            wweSystem.addEvent(newEvent);
            System.out.println("Event added to the system: " + eventName + " at " + eventVenue);
    }

    private static void systemEvents(){
        wweSystem.listEvents();

        
        // while(true){
            
        // }
    }

    private static void uploadLiveEventTicket(){

    }



}

