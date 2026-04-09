package src.Objects;
import java.time.YearMonth;

public class Ticket {
    private static int nextId = 1;
    int id;
    String event;
    double price;
    YearMonth EventDate;
    
    public Ticket(String event, double price, String ticketType, YearMonth eventDate) {
        this.id = nextId++;
        this.event = event;
        this.price = price;
        this.EventDate = eventDate;
    }

}
