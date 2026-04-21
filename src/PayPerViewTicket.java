// import java.io.File;
// import java.io.IOException;
// import java.time.YearMonth;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.node.ArrayNode;
// import com.fasterxml.jackson.databind.node.ObjectNode;

// public class PayPerViewTicket {

//     private static String endUpload = "Y";
//     public static void main(String[] args) 
//     {
       
//          uploadPayPerViewTicket();
//     }

//     public static void uploadPayPerViewTicket() 
//     {
//          int id = 1;

//         while(endUpload.equalsIgnoreCase("Y"))
//         {
//             System.out.println("Would you like to upload a Pay Per View ticket? (Y/N)");
//             String endUpload = System.console().readLine();
            
//             if(endUpload.equalsIgnoreCase("N"))
//             {
//                 break;
//             }
            
//             ObjectMapper mapper = new ObjectMapper();
//             ObjectNode ticketData = mapper.createObjectNode();
//             ticketData.put("ticketID", id++); 

//             System.out.println("Enter the event name:");
//             String event = System.console().readLine();
//             ticketData.put("event", event);

//             System.out.println("Enter the price:");
//             double price = Double.parseDouble(System.console().readLine());
//             ticketData.put("price", price);


//             System.out.println("Enter the event date (YYYY-MM):");
//             String eventDateStr = System.console().readLine();
//             String[] dateParts = eventDateStr.split("-");
//             while (Integer.parseInt(dateParts[1]) > 12 || Integer.parseInt(dateParts[1]) < 1) 
//             {
//                 System.out.println("Invalid month. Please enter a month between 01 and 12.");
//                 eventDateStr = System.console().readLine();
//                 dateParts = eventDateStr.split("-");
//             }
//             YearMonth eventDate = YearMonth.parse(eventDateStr);
//             ticketData.put("eventDate", eventDate.toString());  

//             try 
//             {
//                 File file = new File("TicketSystemDatabases/UploadedPayPerViewTickets.json");

//                 ArrayNode ticketsArray;

//             if (file.exists()) 
//             {
//                 ticketsArray = (ArrayNode) mapper.readTree(file);
//             } 
//             else 
//             {
//                 ticketsArray = mapper.createArrayNode();
//             }

//             ticketsArray.add(ticketData);

//             mapper.writerWithDefaultPrettyPrinter().writeValue(file, ticketsArray);

//             System.out.println("Ticket added successfully!");
//             } 
//             catch (IOException e) 
//             {
//                 System.out.println("An error occurred while saving the ticket data: " + e.getMessage());
//             }
           
//         }
//     }
// }