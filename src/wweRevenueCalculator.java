//package src;

import Objects.CustomSystem;
import Objects.Event;
import Objects.RecordTypes.LiveEventTicket;
import Objects.RecordTypes.PayPerViewTicket;

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

        while(true){
            System.out.println("Please select an option:");
            System.out.println("1 Add an event to the system");
            System.out.println("2 View Events");
            System.out.println("3 Calculate all Revenue");
            System.out.println("4 Exit");
            String choice = System.console().readLine();

            switch (choice) {
                case "1":
                    addEventToSystem();
                    break;
                case "2":
                    systemEvents();
                    break;
                case "3":
                    //CalculateAll
                    calculateAllRevenue();
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

    /**
     * @author Eleena Rath
     */
    private static void systemEvents(){
        System.out.println("All events in system:");
        System.out.println(wweSystem.listEvents());

        while(true){
            System.out.println("Please select an event by its number, or 'exit' to go back to the previous screen");
            String choice = System.console().readLine();

            try{
                int eventIndex = Integer.parseInt(choice);
                Event chosenEvent = wweSystem.getEvent(eventIndex);
                eventController(eventIndex);

            } catch(NumberFormatException e){
                if(choice.equalsIgnoreCase("exit")){
                    return;
                }
                else{
                    System.out.println("Invalid input");
                }
            } catch(IndexOutOfBoundsException f){
                System.out.println("Invalid input");
            }


        }
    }

    /**
     * @author Eleena Rath
     * @param eventIndex
     */
    private static void eventController(int eventIndex){
        Event event = wweSystem.getEvent(eventIndex);
        System.out.println(event.toString());

        while(true){
            System.out.println("What would you like to do with this event?");
            System.out.println("1 View all records for this event");
            System.out.println("2 Add a Live-Event ticket record");
            System.out.println("3 Add a Pay-Per-View ticket record");
            System.out.println("4 Delete a record");
            System.out.println("5 Calculate this event's revenue");
            System.out.println("6 Exit");

            String choice = System.console().readLine();
            switch (choice) {
                case "1":
                    System.out.println(event.listRecords());
                    break;
                case "2":
                    LiveEventTicket liveTicket = uploadLiveEventTicket(eventIndex);
                    wweSystem.addRecordToEvent(eventIndex, liveTicket);
                    break;
                case "3":
                    PayPerViewTicket ppvTicket = uploadPayPerViewTicket(eventIndex);
                    wweSystem.addRecordToEvent(eventIndex, ppvTicket);
                    break;
                case "4":
                    deleteRecordFromEvent(eventIndex);
                    break;
                case "5":
                    //Calculate the revenue for this event
                    System.out.println(event.calculateRevenue());
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }

        }
    }

    private static LiveEventTicket uploadLiveEventTicket(int eventIndex){
        Event event = wweSystem.getEvent(eventIndex);
        System.out.println("Please Fill out the following information to upload a Live-Event ticket.");
            
            System.out.println("Enter a name for the record:");
            String name = System.console().readLine();
            LiveEventTicket ticket = new LiveEventTicket(event.numberOfRecords() , name);
            
            boolean priceValid = false;
            System.out.println("Enter the price:");
            float price = 0;
            while(!priceValid)//Checks for valid price input
            {
                String priceInput = System.console().readLine();
                if(priceInput.matches("\\d+(\\.\\d{1,2})?"))
                {
                    price = Float.parseFloat(priceInput);
                    ticket.setCost(price);
                    priceValid = true;
                }
                else
                {
                    System.out.println("Invalid price format. Please enter a valid price (ex: 19.99):");
                }

            }

            // Add this later, because I'm super tired
            // System.out.println("Enter the event date (YYYY-MM):");
            // String eventDateStr = System.console().readLine();
            // String[] dateParts = eventDateStr.split("-");
            // while (!isDateValid(dateParts)) 
            // {
            //     System.out.println("Invalid Year or month. Please enter as YYYY-MM:");
            //     eventDateStr = System.console().readLine();
            //     dateParts = eventDateStr.split("-");
            // }
            
            // eventdateStr = YearMonth.parse(eventDateStr).toString();

            // try (FileWriter writer = new FileWriter(FilePath, true)) 
            // {
            //     writer.write(EventName + "," + eventdateStr + "," + cost + "\n");
            //     System.out.println("Item added successfully.");
            // } 
            // catch (IOException e) 
            // {
            //     e.printStackTrace();
            // }
            return ticket;
    }

    
    private static PayPerViewTicket uploadPayPerViewTicket(int eventIndex){
        System.out.println("Please Fill out the following information to upload a Pay-Per-View ticket.");
            
            System.out.println("Enter the name of the record:");
            String name = System.console().readLine();
            PayPerViewTicket ticket = new PayPerViewTicket(eventIndex, name);

            boolean priceValid = false;
            System.out.println("Enter the price:");
            float price = 0;
            while(!priceValid)//Checks for valid price input
            {
                String priceInput = System.console().readLine();
                if(priceInput.matches("\\d+(\\.\\d{1,2})?"))
                {
                    price = Float.parseFloat(priceInput);
                    ticket.setCost(price);
                    priceValid = true;
                }
                else
                {
                    System.out.println("Invalid price format. Please enter a valid price (ex: 19.99):");
                }

            }

            // Implement this later, I'm a little too eepy rn
            // System.out.println("Enter the event date (YYYY-MM):");
            // String eventDateStr = System.console().readLine();
            // String[] dateParts = eventDateStr.split("-");
            // while (!isDateValid(dateParts)) 
            // {
            //     System.out.println("Invalid Year or month. Please enter as YYYY-MM:");
            //     eventDateStr = System.console().readLine();
            //     dateParts = eventDateStr.split("-");
            // }
            
            // eventdateStr = YearMonth.parse(eventDateStr).toString();

            // try (FileWriter writer = new FileWriter(FilePath, true)) 
            // {
            //     writer.write(EventName + "," + eventdateStr + "," + cost + "\n");
            //     System.out.println("Item added successfully.");
            // } 
            // catch (IOException e) 
            // {
            //     e.printStackTrace();
            // }
            return ticket;
    }

    /**
     * @author Eleena Rath
     * @param eventIndex
     */
    private static void deleteRecordFromEvent(int eventIndex){
        Event event = wweSystem.getEvent(eventIndex);

        System.out.println(event.listRecords());
         while(true){
            System.out.println("Please select an event by its number, or 'exit' to go back to the previous screen");
            String choice = System.console().readLine();

            try{
                int recordIndex = Integer.parseInt(choice);
                wweSystem.deleteRecordFromEvent(eventIndex, recordIndex);
                System.out.println("Record was successfully deleted");
                return;

            } catch(NumberFormatException e){
                if(choice.equalsIgnoreCase("exit")){
                    return;
                }
                else{
                    System.out.println("Invalid input");
                }
            } catch(IndexOutOfBoundsException f){
                System.out.println("Invalid input");
            }

        }
    }

     /**
     * @author Matayas Durr
     * Displays total revenue across all events
     */
    private static void calculateAllRevenue() {
        float totalRevenue = wweSystem.calculateAllRevenue();
        System.out.println("Total revenue across all events: " + totalRevenue);
    }

       private static boolean isDateValid(String[] dateParts)//Checks for Valid Date input
    {
        if (dateParts.length != 2) return false;
        
        String year = dateParts[0];
        String month = dateParts[1];

        if (!year.matches("\\d{4}") || !month.matches("\\d{2}"))
        return false;

        int m = Integer.parseInt(month);
        if(m < 1 || m > 12) return false;
        return true;
    }


}

