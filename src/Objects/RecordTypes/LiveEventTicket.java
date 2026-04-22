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
    private int amount
    private String FilePathToRecords = "Databases/Records.txt";
    private RecordEnum RecType = RecordEnum.LiveEventSale;
    public LiveEventSale(String name, float cost, YearMonth date, int amount) 
    {
        super(name);
        this.name = name;
        this.cost = cost;
        this.date = date;
        this.amount = amount
    }


    @Override
    public YearMonth getDate() 
    {
        return date;
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public String getName() {
        return name;
    }

    // public void EditLiveEventDatabase()
    // {
    //     LiveEventController EditDatabase = new LiveEventController(true);
    // }

    public int getAmount() {
        return amount;
    }

    public ArrayList<LiveEventSale> TurnAllLiveEventToRecords(boolean sendToRecords)  //Makes object serializable in file
    {

        LiveEventController uploadLiveEvent = new LiveEventController(false);
        String[][] liveEventData = uploadLiveEvent.getLiveEvent();

        ArrayList<LiveEventSale> liveEventList = new ArrayList<>();

        for (int i = 0; i < liveEventData.length; i++) 
        {
            float tempCost = (Float.parseFloat(liveEventData[i][3]) * Float.parseFloat(liveEventData[i][4])) - (Float.parseFloat(liveEventData[i][3]) * Float.parseFloat(liveEventData[i][5]));
            LiveEventSale liveEventSale = new LiveEventSale(liveEventData[i][2].trim(), tempCost, YearMonth.parse(liveEventData[i][6].trim()));
            liveEventList.add(liveEventSale);
            if (!sendToRecords) 
            {
                System.out.println(liveEventSale.name + ", " + liveEventSale.cost + ", " + liveEventSale.date);
            }
        }
        if (sendToRecords) 
        {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FilePathToRecords))) 
            {
            out.writeObject(liveEventList);
            System.out.println("Successfully saved all live event ticket records");
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        return liveEventList;
    }

    public ArrayList<LiveEventSale> readRecords() 
    {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FilePathToRecords))) 
        {
            ArrayList<LiveEventSale> arr = (ArrayList<LiveEventSale>) in.readObject();
            for(LiveEventSale m : arr)
            {
                System.out.println("Reccord Type: " + RecType + " Name: " + m.name + " Cost: " + m.cost + " Date: " + m.date);
            }
            return arr;

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    @Override
    public String toString(){
        String summary = "Record Type: " + RecType + "\n"
        + "Name: " + name + "\n"
        + "Cost: " + cost + "\n"
        + "Date: " + date + "\n"
        + "Amount:" + amount + "\n";
        
        if(description == null || description.length() == 0){
            return summary;
        }

        summary += "Description: " + description + "\n";
        return summary;
    }
}