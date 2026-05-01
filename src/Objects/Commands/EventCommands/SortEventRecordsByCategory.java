package Objects.Commands.EventCommands;

import Objects.Event;
import Objects.Commands.Command;

/**
 * @author Eleena Rath
 */
public class SortEventRecordsByCategory implements Command{
 
    private Event event;

    public SortEventRecordsByCategory(Event event){
        this.event = event;
    }
    

    public void execute(){
        event.sortByCategory();
         System.out.println(event.listRecords());
    }

    @Override
    public String toString(){
        return "Sort records by category";
    }
}
