// package src;
// import java.io.File;
// import java.io.IOException;
// import java.time.YearMonth;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.node.ObjectNode;
// public class UploadLiveEventTicket {

//     private static String endUpload = "Y";
//     public static void main(String[] args) 
//     {
//         while(endUpload.equalsIgnoreCase("Y"))
//         {
//             System.out.println("Would you like to upload a Live-Event View ticket? (Y/N)");
//             String endUpload = System.console().readLine();
//             if(endUpload.equalsIgnoreCase("N"))
//             {
//                 break;
//             }

//             ObjectMapper mapper = new ObjectMapper();
//             ObjectNode ticketData = mapper.createObjectNode();
            
//             System.out.println("Enter the event name:");
//             String event = System.console().readLine();
//             ticketData.put("event", event);

//             System.out.println("Enter the price:");
//             double price = Double.parseDouble(System.console().readLine());
//             ticketData.put("price", price);

//             System.out.println("Enter the ticket type: (Live-Event View or Live)");
//             String ticketType = System.console().readLine();
//             ticketData.put("ticketType", ticketType);

//             System.out.println("Enter the event date (YYYY-MM):");
//             String eventDateStr = System.console().readLine();
//             YearMonth eventDate = YearMonth.parse(eventDateStr);
//             ticketData.put("eventDate", eventDate.toString());  

//             try {
//                 mapper.writerWithDefaultPrettyPrinter().writeValue(new File("TicketSystemDatabases/UploadedTickets.json"), ticketData);
//                 System.out.println("Ticket data saved to UploadedTickets.json");
//             } catch (IOException e) 
//             {
//                 System.out.println("An error occurred while saving the ticket data: " + e.getMessage());
//             }
//         }

//     }
// }