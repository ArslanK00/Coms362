package Objects.Commands.EventCommands;

import Objects.Event;
import Objects.Commands.Command;

/**
 * @author Eleena Rath
 */
public class DeleteRecordFromEvent implements Command{
    
    private Event event;

    public DeleteRecordFromEvent(Event event){
        this.event = event;
    }

    /**
     * @author Eleena Rath
     */
    public void execute(){
        System.out.println(event.listRecords());
        while (true) {
            System.out.println("Please select an record by its number, or 'exit' to go back to the previous screen");
            String choice = System.console().readLine();

            try {
                int recordIndex = Integer.parseInt(choice);
                event.deleteRecord(recordIndex);
                System.out.println("Record was successfully deleted");
                return;

            } catch (NumberFormatException e) {
                if (choice.equalsIgnoreCase("exit")) {
                    return;
                } else {
                    System.out.println("Invalid input");
                }
            } catch (IndexOutOfBoundsException f) {
                System.out.println("Invalid input");
            }
        }
    }

    @Override
    public String toString(){
        return "Delete a record";
    }

}
