

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Objects.CustomSystem;
import Objects.RecordTypes.AbstractRecord;
import Objects.RecordTypes.LiveEventTicket;
import Objects.RecordTypes.PayPerViewTicket;
import Objects.RecordTypes.RecordEnum;
import TrendData.RequestType;
import TrendData.TrendDataHandler;
import TrendData.TrendRequest;

public class AllRecordsTrendData extends TrendDataHandler
{

    private static String DatabaseFilePath = "Databases/Database.txt";

    @Override
    public void handle(TrendRequest request) 
    {
       if(request.type == RequestType.ALLRECORDS)
       {
            System.out.println("AllRecords");
            ArrayList<ArrayList<String>> TrendDataTable = new ArrayList<>();
            
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DatabaseFilePath))) 
        {
            CustomSystem CS = (CustomSystem)in.readObject();
            Float MerchSum = 0f;
            Float PayPerViewSum = 0f;
            Float LiveEventTicketSum = 0f;
            ArrayList<AbstractRecord> arr = CS.getAbstractRecords();
            for(int i = 1; i < CS.getAbstractRecords().size(); i++)
            {
                AbstractRecord obj = arr.get(i);
                switch (obj.getRecordEnum()) 
                {
                    case RecordEnum.LiveEventTicket:
                        LiveEventTicket LEobj = (LiveEventTicket)arr.get(i);
                        LiveEventTicketSum += arr.get(i).getCost() * LEobj.getAmount();
                        break;
                    case RecordEnum.MerchandiseSale:
                        MerchSum += arr.get(i).getCost();
                        break;
                    case RecordEnum.PayPerViewTicket:
                        PayPerViewTicket objP =  (PayPerViewTicket)arr.get(i);
                        PayPerViewSum += obj.getCost() * objP.getAmount();
                        break;
                    default:
                        break;
                }
            }    
            printBar("Merchandise", MerchSum);
            printBar("LiveEventTicket", LiveEventTicketSum);
            printBar("PayPerView", PayPerViewSum);
            printAxis();

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
       }
       else if(next != null)
       {
            next.handle(request);
       }
    }

    private static void printBar(String label, float val)
    {
        int bars = (int)(val/1000);
        String graph = "|".repeat(Math.max(0, bars));
        System.out.printf("%-18s %s%n", label, graph);

    }

    public static void printAxis() 
    {

        int labelWidth = 23;

        String[] ticks = {"|","|","|","|","|","|","|","|","|","|","|","|","|","|","|" };
        String[] labels = {"5k","10k","15k","20k","25k","30k","35k","40k", "45k", "50k", "55k", "60k", "65k", "70k", "75k"};

        // top axis line
        System.out.printf("%-" + labelWidth + "s", "");

        for (String t : ticks) 
        {
            System.out.printf("%-5s", t);
        }
        System.out.println();

        // bottom labels
        System.out.printf("%-" + labelWidth + "s", "");

        for (String l : labels) 
        {
        System.out.printf("%-5s", l);
        }
        System.out.println();
    }
}


