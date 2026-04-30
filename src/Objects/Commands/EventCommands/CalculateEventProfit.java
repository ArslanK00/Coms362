package Objects.Commands.EventCommands;

import Objects.Event;
import Objects.Commands.Command;

/**
 * @author Eleena Rath
 */
public class CalculateEventProfit implements Command{
    
    private Event event;

    public CalculateEventProfit(Event event){
        this.event = event;
    }

    public void execute(){
        // Calculate the revenue for this event
        System.out.println(event.calculateRevenue());
    }

    @Override
    public String toString(){
        return "Calculate this event's profit";
    }
}
