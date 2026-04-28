package Objects.Commands;

import Objects.Event;

public interface EventCommand extends Command{
    
    public void setEvent(Event e);
}
