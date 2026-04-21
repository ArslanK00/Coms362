package Objects.RecordTypes;

import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;

public int amount;

public class PayPerViewTicket implements Record
{
    private String name;
    private float cost;
    private YearMonth date;
    String FilePathToRecords = "Databases/Records.txt";
    public PayPerViewTicket(String name, float cost, YearMonth date, int amount) 
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
    
    public PayPerViewTicket[] TurnAllPayPerViewTicketRecords(boolean SendtoRecords)
    {
        
        UploadPayPerViewTicketSales uploadPayPerViewTicket = new UploadPayPerViewTicketSales();
        String[][] payPerViewTicketData = uploadPayPerViewTicket.getPayPerViewTicket();
        PayPerViewTicketSales[] PayPerViewTicketSystemRecords = new PayPerViewTicketSales[uploadPayPerViewTicket.getPayPerViewTicket().length];
        try
        {
            FileWriter Writer = new FileWriter(FilePathToRecords, true);
            for (int i = 0; i < payPerViewTicketData.length; i++) 
            {


                float tempCost = (Float.parseFloat(payPerViewTicketData[i][3]) * Float.parseFloat(payPerViewTicketData[i][4])) - (Float.parseFloat(payPerViewTicketData[i][3]) * Float.parseFloat(payPerViewTicketData[i][5]));
                PayPerViewTicketSales payPerViewTicketSale = new PayPerViewTicketSales(payPerViewTicketData[i][2].trim(),tempCost,YearMonth.parse(payPerViewTicketData[i][6].trim()));
                PayPerViewTicketSystemRecords[i] = payPerViewTicketSale;
                if(SendtoRecords)
                {
                    Writer.write(PayPerViewTicketSystemRecords[i].name + ", " + PayPerViewTicketSystemRecords[i].cost + ", " + PayPerViewTicketSystemRecords[i].date + "\n");
                    
                }
                else 
                {
                    System.out.println(PayPerViewTicketSystemRecords[i].name + ", " + PayPerViewTicketSystemRecords[i].cost + ", " + PayPerViewTicketSystemRecords[i].date);
                }
            }
            Writer.close();
            System.out.println("Successfully retrieved all live-event ticket Records");
        }
        catch (IOException ex) 
        {
            System.out.println("IOException is caught");
        }

        return PayPerViewTicketSystemRecords;

    }

}
