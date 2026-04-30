package Objects.Commands.EventCommands;

import Objects.Event;
import Objects.Commands.Command;

/**
 * @author Eleena Rath
 */
public class PrintEventRecords implements Command {
    
    private Event event;

    public PrintEventRecords(Event event){

        this.event = event;
    }

    public void execute(){
        System.out.println(event.listRecords());
    }

    @Override
    public String toString(){
        return "View all records for this event";
    }

}
