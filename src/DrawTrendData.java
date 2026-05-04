


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.YearMonth;

import Objects.CustomSystem;
import Objects.RecordTypes.AbstractRecord;
import Objects.RecordTypes.LiveEventTicket;
import Objects.RecordTypes.PayPerViewTicket;
import TrendData.RequestType;
import TrendData.TrendDataHandler;
import TrendData.TrendRequest;
public class DrawTrendData 
{
    private static String DatabaseFilePath = "Databases/Database.txt";
     
    public DrawTrendData(RequestType rt, String Specification)
    {
        TrendDataHandler year = new AllYearsTrendData();
        TrendDataHandler month = new AllMonthsTrendData();
        TrendDataHandler records = new AllRecordsTrendData();
        
        records.setNext(month);
        month.setNext(year);

        TrendRequest request = new TrendRequest(rt, Specification);
        records.handle(request);
    }

    public static void main(String[] argv) throws FileNotFoundException, IOException
    {


        wweRevenueCalculator wweRec = new wweRevenueCalculator();
        // CustomSystem cs = new CustomSystem();
        // cs.addMerchandiseFromDatabase();
        // wweRec.setSystem(cs);
        // wweRec.saveData();

        PayPerViewTicket p = new PayPerViewTicket("Summer Royal Rumble", 8.99f, YearMonth.of(2024, 04), 150);
        PayPerViewTicket p1 = new PayPerViewTicket("Winter Nigh RAW", 5.99f, YearMonth.of(2022, 12), 300);
        PayPerViewTicket p2 = new PayPerViewTicket("WrestleMania", 15.99f, YearMonth.of(2016, 03), 500);
        PayPerViewTicket p3 = new PayPerViewTicket("Survivor Series", 6.99f, YearMonth.of(2018, 06), 80);
        PayPerViewTicket p4 = new PayPerViewTicket("SummerSlam", 8.99f, YearMonth.of(2024, 8), 90);
        PayPerViewTicket p5 = new PayPerViewTicket("No Holds", 4.99f, YearMonth.of(2025, 02), 120);
        PayPerViewTicket p6 = new PayPerViewTicket("In Your House", 3.99f, YearMonth.of(2019, 02), 80);
        PayPerViewTicket p7 = new PayPerViewTicket("King of the Ring", 11.99f, YearMonth.of(2012, 9), 30);
        PayPerViewTicket p8 = new PayPerViewTicket("Cage Match", 12.99f, YearMonth.of(2022, 9), 70);

     LiveEventTicket p10= new LiveEventTicket("Summer Royal Rumble", 8.99f, YearMonth.of(2020, 10), 150);
        LiveEventTicket p12 = new LiveEventTicket("Winter Nigh RAW", 30.99f, YearMonth.of(2017, 10), 300);
        LiveEventTicket p23 = new LiveEventTicket("WrestleMania", 60.99f, YearMonth.of(2017, 8), 200);
        LiveEventTicket p34 = new LiveEventTicket("Survivor Series", 18.99f, YearMonth.of(2020, 07), 80);
        LiveEventTicket p45 = new LiveEventTicket("SummerSlam", 24.99f, YearMonth.of(2026, 07), 90);
        LiveEventTicket p56 = new LiveEventTicket("No Holds", 11.99f, YearMonth.of(2026, 07), 120);
        LiveEventTicket p67 = new LiveEventTicket("In Your House", 13.99f, YearMonth.of(2018, 6), 80);
        LiveEventTicket p78 = new LiveEventTicket("King of the Ring", 20.99f, YearMonth.of(2016, 07), 30);
        LiveEventTicket p89 = new LiveEventTicket("Cage Match", 12.99f, YearMonth.of(2015, 06), 70);
        AbstractRecord r0 = (AbstractRecord)p10;
        AbstractRecord r01 = (AbstractRecord)p12;
        AbstractRecord r1 = (AbstractRecord)p23;
        AbstractRecord r2 = (AbstractRecord)p34;
        AbstractRecord r3 = (AbstractRecord)p45;
        AbstractRecord r4 = (AbstractRecord)p56;
        AbstractRecord r5 = (AbstractRecord)p67;
        AbstractRecord r6 = (AbstractRecord)p78;
       AbstractRecord r7 = (AbstractRecord)p89;
        AbstractRecord r8 = (AbstractRecord)p;
        AbstractRecord r9 = (AbstractRecord)p1;
        AbstractRecord r10 = (AbstractRecord)p2;
        AbstractRecord r11 = (AbstractRecord)p3;
        AbstractRecord r12 = (AbstractRecord)p4;
        AbstractRecord r13 = (AbstractRecord)p5;
        AbstractRecord r14 = (AbstractRecord)p6;
        AbstractRecord r15 = (AbstractRecord)p7;
       AbstractRecord r16 = (AbstractRecord)p8;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DatabaseFilePath))) 
        {
            wweRec.setSystem((CustomSystem)in.readObject());
            wweRec.getSystem().addAbstractRecord(r0);
            wweRec.getSystem().addAbstractRecord(r01);
            wweRec.getSystem().addAbstractRecord(r1);
            wweRec.getSystem().addAbstractRecord(r2);
            wweRec.getSystem().addAbstractRecord(r3);
            wweRec.getSystem().addAbstractRecord(r4);
            wweRec.getSystem().addAbstractRecord(r5);
            wweRec.getSystem().addAbstractRecord(r6);
            wweRec.getSystem().addAbstractRecord(r7);
            wweRec.getSystem().addAbstractRecord(r8);
            wweRec.getSystem().addAbstractRecord(r9);
            wweRec.getSystem().addAbstractRecord(r10);
            wweRec.getSystem().addAbstractRecord(r11);
            wweRec.getSystem().addAbstractRecord(r12);
            wweRec.getSystem().addAbstractRecord(r13);
            wweRec.getSystem().addAbstractRecord(r14);
            wweRec.getSystem().addAbstractRecord(r15);
            wweRec.getSystem().addAbstractRecord(r16);
            wweRec.getSystem().addMerchandiseFromDatabase();
            wweRec.saveData();
            wweRec.getSystem().listAbstractRecords();


        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }




    }
}





