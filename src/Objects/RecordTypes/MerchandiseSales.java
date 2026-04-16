package Objects.RecordTypes;
import java.io.BufferedReader;
// package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MerchandiseSales 
{
    
    
    
    public MerchandiseSales()
    {
        //AddUnitsSold();
    }
    
    public int getUnitsSoldbyCategory()
    {
        return 0;
    }
    
    public int getUnitsSoldbyMonth()
    {
        return 0;
    }

    public int getUnitsSoldbyYear()
    {
        return 0;
    }

    public int getUnitsSoldByName()
    {
        return 0;
    }

    public void AddUnitsSold()
    {
        int id = 1;

            ObjectMapper mapper = new ObjectMapper();
            ObjectNode MerchData = mapper.createObjectNode();
            MerchData.put("UnitID", id++); 
            getCatgories(true);
            
            //Get Categories from MerchandiseSalesCategories and list them below with numbers, indicating which number corresponds too
            System.out.println("Choose your Category: (if you do no not see the category you need type -1 and add a category)");
            int CategoryID = Integer.parseInt(System.console().readLine());
            if(CategoryID == -1)
            {
                System.out.println("Enter the name of the new category:");
                String newCategory = System.console().readLine();
                //add new category to MerchandiseSalesCategories and get the id for that category
                CategoryID = addCategory(newCategory); 
            }
            else
            {
                Map<Integer, String> Categories = getCatgories(false);
                while(!Categories.containsKey(CategoryID))
                {
                    System.out.println("Invalid Category ID. Please enter a valid Category ID:");
                    CategoryID = Integer.parseInt(System.console().readLine()); 
                }
            }
            

            System.out.println("Enter the Unit name:");
            String name = System.console().readLine();
            MerchData.put("Unit Name", name);

            System.out.println("Enter the Units Sold:");
            int unitsSold = Integer.parseInt(System.console().readLine());
            MerchData.put("Units Sold", unitsSold);

            System.out.println("Enter the price:");
            double price = Double.parseDouble(System.console().readLine());
            MerchData.put("price", price);


            System.out.println("Enter the event date (YYYY-MM):");
            String eventDateStr = System.console().readLine();
            String[] dateParts = eventDateStr.split("-");
            while (Integer.parseInt(dateParts[1]) > 12 || Integer.parseInt(dateParts[1]) < 1) 
            {
                System.out.println("Invalid month. Please enter a month between 01 and 12.");
                eventDateStr = System.console().readLine();
                dateParts = eventDateStr.split("-");
            }
            YearMonth eventDate = YearMonth.parse(eventDateStr);
            MerchData.put("eventDate", eventDate.toString());  

            try 
            {
                File file = new File("Databases/MerchandiseSalesDatabase.txt");

                ArrayNode UnitsArray;

            if (file.exists()) 
            {
                UnitsArray = (ArrayNode) mapper.readTree(file);
            } 
            else 
            {
                UnitsArray = mapper.createArrayNode();
            }

            UnitsArray.add(MerchData);

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, UnitsArray);

            System.out.println("Unit added successfully!");
            } 
            catch (IOException e) 
            {
                System.out.println("An error occurred while saving the unit data: " + e.getMessage());
            }
    }

    public int addCategory(String categoryName)
    {
        int numberOfIDs = 0;

        try{
            //first counts the number of categories in database
            BufferedReader reader = new BufferedReader(new FileReader("Databases/MerchandiseSalesCategories.txt"));
            while(reader.readLine() != null)numberOfIDs++;
            reader.close();

            //Adds the new category with the approriate ID to the database
            FileWriter Writer = new FileWriter("Databases/MerchandiseSalesCategories.txt");
            Writer.write(numberOfIDs + ":" + categoryName + "\n");
            Writer.close();
        }
        catch(IOException e){
            System.out.println("An error occurred while adding the category: " + e.getMessage());
        }

        return numberOfIDs;

    }


    public static Map<Integer, String> getCatgories(boolean printCategories)
    {
        Map<Integer, String> categories = new  HashMap<>();
        File myFile = new File("Databases/MerchandiseSalesCategories.txt");
        try(Scanner myReader = new Scanner(myFile)){
            ObjectMapper objectMapper = new ObjectMapper();
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                String[] parts = data.split(":");
                int id = Integer.parseInt(parts[0].trim());
                String categoryName = parts[1].trim();
                categories.put(id, categoryName);
                if (printCategories) {
                    System.out.println(id+ ":" + categoryName );
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred while reading the categories: " + e.getMessage());
        }
    

        return categories;
    }

    public int calculateRevenuebyCategory()
    {
        return 0;
    }

    public int calculateRevenuebyMonth()
    {
        return 0;
    }

    public int calculateRevenuebyYear()
    {
        return 0;
    }

    public void sendRevenueToSystem()
    {

    }

    public int editUnitDetailsbyID(int id)
    {
        return 0;
    }

    public int deleteUnitDetailsByID(int id)
    {
        return 0;
    }

    public int findIDForUnitByName()
    {
        return 0;
    }

    public int findIDForUnitByCategory()
    {
        return 0;
    }


    
}


/*Public int getUnitsSoldbyCategory()
Public int getUnitsSoldbyMonth()
Public int getUnitsSoldbyYear()
Public int getUnitsSoldByName()
Public void AddUnitsSold()
Public void addCategory()
Public int calculateRevenuebyCategory()
Public int calculateRevenuebyMonth()
Public int calculateRevenuebyYear()
Public void sendRevenueToSystem()
Public int editUnitDetailsbyID(int)
Public int deleteUnitDetailsByID(int)
Public int findIDForUnit()//will use date or id or category, depending on user choice
 */