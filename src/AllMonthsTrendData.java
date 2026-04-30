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

public class AllMonthsTrendData extends TrendDataHandler
{

    private static String DatabaseFilePath = "Databases/Database.txt";

    @Override
    public void handle(TrendRequest request) 
    {
       if(request.type == RequestType.MONTH)
       {
            System.out.println("Revenue by month");
            
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DatabaseFilePath))) 
        {
            CustomSystem CS = (CustomSystem)in.readObject();
            Float Jan = 0f;
            Float Feb = 0f;
            Float Mar = 0f;
            Float Apr = 0f;
            Float May = 0f;
            Float June = 0f;
            Float July = 0f;
            Float Aug = 0f;
            Float Sep = 0f;
            Float Oct = 0f;
            Float Nov = 0f;
            Float Dec = 0f;
            ArrayList<AbstractRecord> arr = CS.getAbstractRecords();
            for(int i = 1; i < CS.getAbstractRecords().size(); i++)
            {
                AbstractRecord obj = arr.get(i);
                if(!request.DataVal.equals("none"))
                {
                    if(obj.getDate().getYear() != Integer.parseInt(request.DataVal))
                    {
                        continue;
                    }
                }
                switch (obj.getDate().getMonthValue()) 
                {
                    case 1:
                        Jan += getRev(obj);
                        break;
                    case 2:
                        Feb += getRev(obj);
                        break;
                    case 3:
                        Mar += getRev(obj);
                        break;
                    case 4:
                        Apr += getRev(obj);
                        break;
                    case 5:
                        May += getRev(obj);
                        break;
                    case 6:
                        June += getRev(obj);
                        break;
                    case 7:
                        July += getRev(obj);
                        break;
                    case 8:
                        Aug += getRev(obj);
                        break;
                    case 9:
                        Sep += getRev(obj);
                        break;
                    case 10:
                        Oct += getRev(obj);
                        break;
                    case 11:
                        Nov += getRev(obj);
                        break;
                    case 12:
                        Dec += getRev(obj);
                        break;
                }
            }      
            printBar("January", Jan);
            printBar("Febuary", Feb);
            printBar("March", Mar);
            printBar("April", Apr);
            printBar("May", May);
            printBar("June", June);
            printBar("July", July);
            printBar("August", Aug);
            printBar("September", Sep);
            printBar("October", Oct);
            printBar("November", Nov);
            printBar("December", Dec);

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
    private float getRev(AbstractRecord obj)
    {
        switch (obj.getRecordEnum()) 
        {
            case RecordEnum.LiveEventTicket:
                LiveEventTicket LEobj = (LiveEventTicket)obj;
                return obj.getCost() * LEobj.getAmount();
                
            case RecordEnum.MerchandiseSale:
                return obj.getCost();
                
            case RecordEnum.PayPerViewTicket:
                PayPerViewTicket objP =  (PayPerViewTicket)obj;
                return obj.getCost() * objP.getAmount();
                
            default:
                return 0f;
        }
    }
}
