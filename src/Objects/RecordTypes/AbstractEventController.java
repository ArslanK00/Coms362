package Objects.RecordTypes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public abstract class AbstractEventController {
    protected int choice = 0;

    public AbstractEventController(boolean editDatabase) {
        if(editDatabase) {
            manageDatabase();
        }
    }

    private void manageDatabase() {
        // ... (Keep existing manageDatabase loop from the previous response) ...
    }

    protected abstract String getFilePath();
    protected abstract String getEventTypeName();

    public String[][] getEventData() {
        // ... (Keep existing getEventData logic) ...
        File file = new File(getFilePath());
        ArrayList<String[]> data = new ArrayList<>();

        try(Scanner reader = new Scanner(file)) {
            while(reader.hasNextLine()) {
                data.add(reader.nextLine().split(","));
            }
            String[][] arr = new String[data.size()][];
            for(int i = 0; i < data.size(); i++) {
                arr[i] = data.get(i);
            }
            return arr;
        } catch(FileNotFoundException e) {
            System.out.println("Error reading events: " + e.getMessage());
        }
        return new String[0][0];
    }

    // UPDATED: Now accepts and writes eventName to the file
    public final void saveEventToFile(String name, float price, int quantity, float cost, YearMonth date, String eventName) {
        try(FileWriter writer = new FileWriter(getFilePath(), true)) {
            int id = getNextID();
            // Replaced '0' with 'eventName' so the text DB links the ticket to the event
            writer.write(id + ", " + eventName + ", " + name + ", " + price + ", " + quantity + ", " + cost + ", " + date + "\n");
            System.out.println(getEventTypeName() + " saved to database.");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    protected int getNextID() {
        String[][] data = getEventData();
        HashSet<Integer> ids = new HashSet<>();
        for(String[] row : data) {
            ids.add(Integer.parseInt(row[0].trim()));
        }
        int i = 1;
        while(ids.contains(i)) i++;
        return i;
    }
}