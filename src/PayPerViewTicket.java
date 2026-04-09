package src;
import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;

import src.Objects.AbstractRecord;
public class PayPerViewTicket extends AbstractRecord
{

    
    private static String eventdateStr;
    private static float cost;
    private static String EventName;
    private static String FilePath = "TicketSystemDatabases/UploadedPayPerViewTickets.txt";
    private static boolean priceValid = false;

    public PayPerViewTicket() 
    {
        uploadPayPerViewTicket();
    }


    public static void uploadPayPerViewTicket() 
    {
            
            System.out.println("Please Fill out the following information to upload a Pay-Per-View ticket.");
            
            System.out.println("Enter the event name:");
            String eventName = System.console().readLine();
            EventName = eventName;

            System.out.println("Enter the price:");
            float price = 0;
            while(!priceValid)//Checks for valid price input
            {
                String priceInput = System.console().readLine();
                if(priceInput.matches("\\d+(\\.\\d{1,2})?"))
                {
                    price = Float.parseFloat(priceInput);
                    cost = price;
                    priceValid = true;
                }
                else
                {
                    System.out.println("Invalid price format. Please enter a valid price (ex: 19.99):");
                }

            }

            System.out.println("Enter the event date (YYYY-MM):");
            String eventDateStr = System.console().readLine();
            String[] dateParts = eventDateStr.split("-");
            while (!isDateValid(dateParts)) 
            {
                System.out.println("Invalid Year or month. Please enter as YYYY-MM:");
                eventDateStr = System.console().readLine();
                dateParts = eventDateStr.split("-");
            }
            
            eventdateStr = YearMonth.parse(eventDateStr).toString();

            try (FileWriter writer = new FileWriter(FilePath, true)) 
            {
                writer.write(EventName + "," + eventdateStr + "," + cost + "\n");
                System.out.println("Item added successfully.");
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
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

    @Override
    public String getDate() 
    {
        return this.eventdateStr;
    }

    @Override
    public float getCost() 
    {
        return this.cost;
    }

    public String getEventName() 
    {
        return this.EventName;
    }

    @Override
    public String toString() 
    {
        return "PayPerViewTicket [Event Name=" + EventName + ", Event Date=" + getDate() + ", Cost=" + getCost() + "]";
    }
}