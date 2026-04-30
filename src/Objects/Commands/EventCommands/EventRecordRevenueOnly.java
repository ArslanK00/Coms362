package Objects.Commands.EventCommands;

import Objects.Event;
import Objects.Commands.Command;

public class EventRecordRevenueOnly implements Command {
    
    private Event event;

    public EventRecordRevenueOnly(Event event){
        this.event = event;
    }

    public void execute(){
        System.out.println("Revenue Only: " + event.calculateRevenueOnly());
    }

    @Override
    public String toString(){
        return "View revenue only";
    }
}
