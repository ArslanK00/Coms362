package Objects.RecordTypes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.YearMonth;
import java.util.ArrayList;

public class MerchandiseSale extends AbstractRecord implements Serializable
{
    private String name;
    private float cost;
    private YearMonth date;
    private String FilePathToRecords = "Databases/Records.txt";
    private RecordEnum RecType = RecordEnum.MerchandiseSale;
    public MerchandiseSale(String name, float cost, YearMonth date) 
    {
        super(name);
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

    public void EditMerchandiseDatabase()
    {
        MerchandiseController EditDatabase = new MerchandiseController(true);
    }


    public ArrayList<MerchandiseSale> TurnAllMerchandiseToRecords(boolean sendToRecords)  //Makes object serializable in file
    {

        MerchandiseController uploadMerch = new MerchandiseController(false);
        String[][] merchData = uploadMerch.getMerch();

        ArrayList<MerchandiseSale> merchList = new ArrayList<>();

        for (int i = 0; i < merchData.length; i++) 
        {
            float tempCost = (Float.parseFloat(merchData[i][3]) * Float.parseFloat(merchData[i][4])) - (Float.parseFloat(merchData[i][3]) * Float.parseFloat(merchData[i][5]));
            MerchandiseSale merchSale = new MerchandiseSale(merchData[i][2].trim(), tempCost, YearMonth.parse(merchData[i][6].trim()));
            merchList.add(merchSale);
            if (!sendToRecords) 
            {
                System.out.println(merchSale.name + ", " + merchSale.cost + ", " + merchSale.date);
            }
        }
        if (sendToRecords) 
        {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FilePathToRecords))) 
            {
            out.writeObject(merchList);
            System.out.println("Successfully saved all Merchandise Records");
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        return merchList;
    }

    public ArrayList<MerchandiseSale> readRecords() 
    {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FilePathToRecords))) 
        {
            ArrayList<MerchandiseSale> arr = (ArrayList<MerchandiseSale>) in.readObject();
            for(MerchandiseSale m : arr)
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
        + "Date: " + date + "\n";
        
        if(description == null || description.length() == 0){
            return summary;
        }

        summary += "Description: " + description + "\n";
        return summary;
    }
}