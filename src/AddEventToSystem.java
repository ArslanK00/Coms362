package src;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import src.Objects.*;

/*
* @author Jamey Nguyen
*/

public class AddEventToSystem {
    private static String endUpload = "Y";
    static ArrayList<Event> events = new ArrayList<Event>();

    public static void main(String[] args) {
        while (endUpload.equalsIgnoreCase("Y")) {
            System.out.println("Would you like to add an event to the system? (Y/N)");
            String endUpload = System.console().readLine();
            if (endUpload.equalsIgnoreCase("N")) {
                break;
            }

            System.out.println("Enter the event name:");
            String eventName = System.console().readLine();

            System.out.println("Enter the event venue:");
            String eventVenue = System.console().readLine();

            Event newEvent = new Event(eventName, eventVenue);
            CustomSystem system = new CustomSystem();
            system.addEvent(newEvent);
            events.add(newEvent);
            System.out.println("Event added to the system: " + eventName + " at " + eventVenue);
        }

        try {
            File file = new File("TicketSystemDatabases/AddedEventsToSystem.txt");
            file.getParentFile().mkdirs();

            FileWriter writer = new FileWriter(file);

            for (Event event : events) {
                writer.write(event.getName() + " - " + event.getVenue() + "\n");
            }
            writer.close();
            System.out.println("Events are written to AddedEventsToSystem.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
