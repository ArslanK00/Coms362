package Objects.RecordTypes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;

public class LiveEventTicket extends AbstractRecord implements Serializable
{
    private String name;
    private float cost;
    private YearMonth date;
    private int amount;
    private String associatedEvent;
    private String FilePathToRecords = "Databases/Records.txt";
    private RecordEnum RecType = RecordEnum.LiveEventTicket;

    public LiveEventTicket(String name, float cost, YearMonth date, int amount, String associatedEvent) 
    {
        super(name);
        this.name = name;
        this.cost = cost;
        this.date = date;
        this.amount = amount;
        this.associatedEvent = associatedEvent;
    }

    public LiveEventTicket(String name){
        super(name);
    }

    @Override
    public YearMonth getDate() { return date; }

    @Override
    public float getCost() { return cost; }

    @Override
    public String getName() { return name; }

    public int getAmount() { return amount; }

    public String getAssociatedEvent() { return associatedEvent; }

    public ArrayList<LiveEventTicket> TurnAllLiveEventToRecords(boolean sendToRecords)  
    {
        LiveEventController uploadLiveEvent = new LiveEventController(false);
        String[][] liveEventData = uploadLiveEvent.getLiveEvent();
        ArrayList<LiveEventTicket> liveEventList = new ArrayList<>();

        for (int i = 0; i < liveEventData.length; i++) 
        {
            float tempCost = (Float.parseFloat(liveEventData[i][3]) * Float.parseFloat(liveEventData[i][4])) - (Float.parseFloat(liveEventData[i][3]) * Float.parseFloat(liveEventData[i][5]));
            int amount = Integer.parseInt(liveEventData[i][4].trim());
            String eventName = liveEventData[i][1].trim(); // Extract event name from column 2
            
            LiveEventTicket liveEventTicket = new LiveEventTicket(liveEventData[i][2].trim(), tempCost, YearMonth.parse(liveEventData[i][6].trim()), amount, eventName);
            liveEventList.add(liveEventTicket);
            
            if (!sendToRecords) {
                System.out.println(liveEventTicket.name + " (" + eventName + "), " + liveEventTicket.cost + ", " + liveEventTicket.date);
            }
        }
        if (sendToRecords) 
        {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FilePathToRecords))) {
                out.writeObject(liveEventList);
                System.out.println("Successfully saved all live event ticket records");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return liveEventList;
    }

    @Override
    public void setCost(float cost){
        this.cost = cost;
    }

    @Override
    public String toString(){
        String summary = "Record Type: " + RecType + "\n"
        + "For Event: " + associatedEvent + "\n"  // Added to toString
        + "Name: " + name + "\n"
        + "Cost: " + cost + "\n"
        + "Date: " + date + "\n"
        + "Amount: " + amount + "\n";
        
        if(description != null && description.length() > 0){
            summary += "Description: " + description + "\n";
        }
        return summary;
    }
}