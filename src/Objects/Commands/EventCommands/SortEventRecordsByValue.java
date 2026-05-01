package Objects.Commands.EventCommands;

import Objects.Event;
import Objects.Commands.Command;

public class SortEventRecordsByValue implements Command {

    private Event event;

    public SortEventRecordsByValue(Event event) {
        this.event = event;
    }

    public void execute() {
        event.sortByValue();
        System.out.println(event.listRecords());
    }

    @Override
    public String toString(){
        return "Sort records by value";
    }
}
