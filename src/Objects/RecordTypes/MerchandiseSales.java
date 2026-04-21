package Objects.RecordTypes;

import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;



public class MerchandiseSales implements Record
{
    private String name;
    private float cost;
    private YearMonth date;
    String FilePathToRecords = "Databases/Records.txt";
    public MerchandiseSales(String name, float cost, YearMonth date) 
    {
        this.name = name;
        this.cost = cost;
        this.date = date;
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

    
    public MerchandiseSales[] TurnAllMerchandiseToSystemRecords(boolean SendtoRecords)
    {
        
        UploadMerchandiseSales uploadMerch = new UploadMerchandiseSales(false);
        String[][] merchData = uploadMerch.getMerch();
        MerchandiseSales[] MerchSystemRecords = new MerchandiseSales[uploadMerch.getMerch().length];
        try
        {
            FileWriter Writer = new FileWriter(FilePathToRecords, true);
            for (int i = 0; i < merchData.length; i++) 
            {


                float tempCost = (Float.parseFloat(merchData[i][3]) * Float.parseFloat(merchData[i][4])) - (Float.parseFloat(merchData[i][3]) * Float.parseFloat(merchData[i][5]));
                MerchandiseSales merchSale = new MerchandiseSales(merchData[i][2].trim(),tempCost,YearMonth.parse(merchData[i][6].trim()));
                MerchSystemRecords[i] = merchSale;
                if(SendtoRecords)
                {
                    Writer.write(MerchSystemRecords[i].name + ", " + MerchSystemRecords[i].cost + ", " + MerchSystemRecords[i].date + "\n");
                    
                }
                else 
                {
                    System.out.println(MerchSystemRecords[i].name + ", " + MerchSystemRecords[i].cost + ", " + MerchSystemRecords[i].date);
                }
            }
            Writer.close();
            System.out.println("Successfully retrieved all Merchandise Records");
        }
        catch (IOException ex) 
        {
            System.out.println("IOException is caught");
        }

        return MerchSystemRecords;

    }

}
