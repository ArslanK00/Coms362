package Objects.RecordTypes;

import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;

public int amount;

public class LiveEventTicket implements Record
{
    private String name;
    private float cost;
    private YearMonth date;
    String FilePathToRecords = "Databases/Records.txt";
    public LiveEventTicket(String name, float cost, YearMonth date, int amount) 
    {
        this.name = name;
        this.cost = cost;
        this.date = date;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }
    
    public LiveEventTicket[] TurnAllLiveEventTicketRecords(boolean SendtoRecords)
    {
        
        UploadLiveEventTicketSales uploadLiveEventTicket = new UploadLiveEventTicketSales();
        String[][] liveEventTicketData = uploadLiveEventTicket.getLiveEventTicket();
        LiveEventTicketSales[] LiveEventTicketSystemRecords = new LiveEventTicketSales[uploadLiveEventTicket.getLiveEventTicket().length];
        try
        {
            FileWriter Writer = new FileWriter(FilePathToRecords, true);
            for (int i = 0; i < liveEventTicketData.length; i++) 
            {


                float tempCost = (Float.parseFloat(liveEventTicketData[i][3]) * Float.parseFloat(liveEventTicketData[i][4])) - (Float.parseFloat(liveEventTicketData[i][3]) * Float.parseFloat(liveEventTicketData[i][5]));
                LiveEventTicketSales liveEventTicketSale = new LiveEventTicketSales(liveEventTicketData[i][2].trim(),tempCost,YearMonth.parse(liveEventTicketData[i][6].trim()));
                LiveEventTicketSystemRecords[i] = liveEventTicketSale;
                if(SendtoRecords)
                {
                    Writer.write(LiveEventTicketSystemRecords[i].name + ", " + LiveEventTicketSystemRecords[i].cost + ", " + LiveEventTicketSystemRecords[i].date + "\n");
                    
                }
                else 
                {
                    System.out.println(LiveEventTicketSystemRecords[i].name + ", " + LiveEventTicketSystemRecords[i].cost + ", " + LiveEventTicketSystemRecords[i].date);
                }
            }
            Writer.close();
            System.out.println("Successfully retrieved all live-event ticket Records");
        }
        catch (IOException ex) 
        {
            System.out.println("IOException is caught");
        }

        return LiveEventTicketSystemRecords;

    }

}
