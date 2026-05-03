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

public class AllYearsTrendData extends TrendDataHandler
{


    private static String DatabaseFilePath = "Databases/Database.txt";

    @Override
    public void handle(TrendRequest request) 
    {
       if(request.type == RequestType.YEAR)
       {
            System.out.println("Revenue by month");
            
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DatabaseFilePath))) 
        {
            CustomSystem CS = (CustomSystem)in.readObject();
            Float Y2026 = 0f;
            Float Y2025 = 0f;
            Float Y2024 = 0f;
            Float Y2023 = 0f;
            Float Y2022 = 0f;
            Float Y2021 = 0f;
            Float Y2020 = 0f;
            Float Y2019 = 0f;
            Float Y2018 = 0f;
            Float Y2017 = 0f;
            Float Y2016 = 0f;
            Float Y2015 = 0f;
            ArrayList<AbstractRecord> arr = CS.getAbstractRecords();
            for(int i = 1; i < CS.getAbstractRecords().size(); i++)
            {
                AbstractRecord obj = arr.get(i);
                switch (obj.getDate().getYear()) 
                {
                    case 2026:
                        Y2026 += getRev(obj);
                        break;
                    case 2025:
                        Y2025 += getRev(obj);
                        break;
                    case 2024:
                        Y2024 += getRev(obj);
                        break;
                    case 2023:
                        Y2023 += getRev(obj);
                        break;
                    case 2022:
                        Y2022 += getRev(obj);
                        break;
                    case 2021:
                        Y2021 += getRev(obj);
                        break;
                    case 2020:
                        Y2020 += getRev(obj);
                        break;
                    case 2019:
                        Y2019 += getRev(obj);
                        break;
                    case 2018:
                        Y2018 += getRev(obj);
                        break;
                    case 2017:
                        Y2017 += getRev(obj);
                        break;
                    case 2016:
                        Y2016 += getRev(obj);
                        break;
                    case 2015:
                        Y2015 += getRev(obj);
                        break;
                }
            }
            printBar("2026", Y2026);
            printBar("2025", Y2025);
            printBar("2024", Y2024);
            printBar("2023", Y2023);
            printBar("2022", Y2022);
            printBar("2021", Y2021);
            printBar("2020", Y2020);
            printBar("2019", Y2019);
            printBar("2018", Y2018);
            printBar("2017", Y2017);
            printBar("2016", Y2016);
            printBar("2015", Y2015);
            printAxis();

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
       }
       else if(next != null)
       {
            return;
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
            case LiveEventTicket:
                LiveEventTicket LEobj = (LiveEventTicket)obj;
                return obj.getCost() * LEobj.getAmount();
                
            case MerchandiseSale:
                return obj.getCost();
                
            case PayPerViewTicket:
                PayPerViewTicket objP =  (PayPerViewTicket)obj;
                return obj.getCost() * objP.getAmount();
                
            default:
                return 0f;
        }    
    }
}
