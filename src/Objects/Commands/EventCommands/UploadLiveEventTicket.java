package Objects.Commands.EventCommands;

import java.time.YearMonth;

import Objects.CustomSystem;
import Objects.Event;
import Objects.Commands.Command;
import Objects.RecordTypes.LiveEventTicket;

public class UploadLiveEventTicket implements Command{
    
    Event event;

    public UploadLiveEventTicket(Event event){
        this.event = event;
    }

    public void execute(){
        //Event event = wweSystem.getEvent(eventIndex);
        System.out.println("Please Fill out the following information to upload a Live-Event ticket.");

        System.out.println("Enter a name for the record:");
        String name = System.console().readLine();

        boolean priceValid = false;
        float price = 0;
        System.out.println("Enter the price:");
        while (!priceValid) {
            String priceInput = System.console().readLine();
            if (priceInput.matches("\\d+(\\.\\d{1,2})?")) {
                price = Float.parseFloat(priceInput);
                priceValid = true;
            } else {
                System.out.println("Invalid price format. Please enter a valid price (ex: 19.99):");
            }
        }

        int amount = 0;
        boolean amountValid = false;
        System.out.println("Enter the amount of tickets sold:");
        while (!amountValid) {
            try {
                amount = Integer.parseInt(System.console().readLine());
                if (amount >= 0) {
                    amountValid = true;
                } else {
                    System.out.println("Amount must be 0 or greater:");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Enter a whole number:");
            }
        }

        YearMonth date = null;
        boolean dateValid = false;
        System.out.println("Enter the date (YYYY-MM):");
        while (!dateValid) {
            String dateInput = System.console().readLine();
            try {
                date = YearMonth.parse(dateInput);
                dateValid = true;
            } catch (Exception e) {
                System.out.println("Invalid date format. Please use YYYY-MM:");
            }
        }

        LiveEventTicket ticket = new LiveEventTicket(name, price * amount, date, amount);

        ticket.setCategory("Live Event");
        ticket.setRevenue(true);

        event.addRecord(ticket);
    }
}
