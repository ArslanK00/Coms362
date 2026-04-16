package Objects.RecordTypes;
// package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class MerchandiseSales 
{
    
    private static String FilePathMerch = "Databases/MerchandiseSales.txt";
    private static String FilePathCategories = "Databases/MerchandiseSalesCategories.txt";

    private boolean priceValid = false;
    private float cost;
    private static String MerchdateStr;
    int choice = 0;
    public MerchandiseSales()
    {
        while(choice != -1)
        {
            System.out.println("Choose an option: \n 1.Add Units Sold \n 2. Delete unit details by ID \n 3. add category \n 4. get categories \n 5. Find unit by name \n 6. Find unit by category \n 7. Get units sold by year \n 8. Get units sold by month \n 9. Get units sold by name \n -1. Exit");

            choice = Integer.parseInt(System.console().readLine());
            switch(choice)
            {
                case 1:
                    AddUnitsSold();
                    break;
                case 2:
                    System.out.println("Enter the ID of the unit you want to delete:");
                    int id = Integer.parseInt(System.console().readLine());
                    deleteUnitDetailsByID(id);
                    break;
                case 3:
                    System.out.println("Enter the name of the new category:");
                    String newCategory = System.console().readLine();
                    addCategory(newCategory);
                    break;
                case 4:
                    getCatgories(true);
                    break;
                case 5:
                    System.out.println("Enter the name of the unit you want to find:");
                    String UNitname = System.console().readLine();
                    findUnitByName(UNitname);
                    break;
                case 6:
                    System.out.println("Enter the category ID of the units you want to find:");
                    int categoryID = Integer.parseInt(System.console().readLine());
                    findUnitByCategory(categoryID);
                    break;
                case 7:
                    System.out.println("Enter the year for which you want to get units sold:");
                    int year = Integer.parseInt(System.console().readLine());
                    getUnitsSoldbyYear(year);
                    break;
                case 8:
                    System.out.println("Enter the month for which you want to get units sold:");
                    int month = Integer.parseInt(System.console().readLine());
                    getUnitsSoldbyMonth(month);
                    break;
                case 9:
                    System.out.println("Enter the name of the unit for which you want to get sales data:");
                    String name = System.console().readLine();
                    getUnitsSoldByName(name);
                    break;
                case -1:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    public void getUnitsSoldbyCategory(int categoryID)
    {

            Map<Integer, String> Categories = getCatgories(true);
            //Get Categories from MerchandiseSalesCategories and list them below with numbers, indicating which number corresponds too
            String categoryIDString;
            if(!Categories.containsKey(categoryID))
            {
                while(!(categoryIDString = System.console().readLine()).matches("-?\\d+"))
                {
                    System.out.println("Invalid input. Please enter a valid Category ID:");
                }
                categoryID = Integer.parseInt(categoryIDString);

            }
            

        File myFile = new File(FilePathMerch);
        ArrayList<String[]> merchData = new ArrayList<>();
        try(Scanner myReader = new Scanner(myFile))
        {
            while(myReader.hasNextLine())
            {
                String[] data = myReader.nextLine().split(",");
                if(Integer.parseInt(data[1].trim()) == categoryID)
                {
                    merchData.add(data);
                    System.out.println("Unit ID: " + data[0] + " Category ID: " + data[1] + " Name: " + data[2] + " Units Sold: " + data[3] + " Price: " + data[5] + " Date: " + data[6]);
                }
            }
            return;
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred while reading the merchandise sales: " + e.getMessage());
        }
    
        System.out.println("No merchandise sales data found.");
        return;
    }
    
    public void getUnitsSoldbyMonth(int month)
    {
        String monthStringCheck;
        if((month > 12 || month < 1) )
        {
            System.out.println("Invalid month. Please enter a month between 1 and 12. please try again.");
            while(!(monthStringCheck = System.console().readLine()).matches("-?\\d+"))
            {
                System.out.println("Invalid input. Please enter a valid month:");
            }
            getUnitsSoldbyMonth(Integer.parseInt(monthStringCheck));
        }
        File myFile = new File(FilePathMerch);
        ArrayList<String[]> merchData = new ArrayList<>();
        try(Scanner myReader = new Scanner(myFile))
        {
            while(myReader.hasNextLine())
            {
                String[] data = myReader.nextLine().split(",");
                int CurrMonth = Integer.parseInt(data[6].split("-")[1].trim());
                if(CurrMonth == month)
                {
                    merchData.add(data);
                    System.out.println("Unit ID: " + data[0] + " Category ID: " + data[1] + " Name: " + data[2] + " Units Sold: " + data[3] + " Price: " + data[5] + " Date: " + data[6]);
                }
            }
            if(merchData.size() > 0)
            {
                return;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred while reading the merchandise sales: " + e.getMessage());
        }
    
        System.out.println("No merchandise sales data found.");
        return;
    }

    public void getUnitsSoldbyYear(int year)//Double Check Code
    {
        String yearStringCheck;
        if((year > 9999 || year < 1000) )
        {
            System.out.println("Invalid year. Please enter a valid year. please try again.");
            while(!(yearStringCheck = System.console().readLine()).matches("-?\\d+"))
            {
                System.out.println("Invalid input. Please enter a valid year:");
            }
            getUnitsSoldbyYear(Integer.parseInt(yearStringCheck));
        }
        File myFile = new File(FilePathMerch);
        ArrayList<String[]> merchData = new ArrayList<>();
        try(Scanner myReader = new Scanner(myFile))
        {
            while(myReader.hasNextLine())
            {
                String[] data = myReader.nextLine().split(",");
                int CurrYear = Integer.parseInt(data[6].split("-")[0].trim());
                if(CurrYear == year)
                {
                    merchData.add(data);
                    System.out.println("Unit ID: " + data[0] + " Category ID: " + data[1] + " Name: " + data[2] + " Units Sold: " + data[3] + " Price: " + data[5] + " Date: " + data[6]);
                }
            }
            if(merchData.size() > 0)
            {
                return;
            }

        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred while reading the merchandise sales: " + e.getMessage());
        }
    
        System.out.println("No merchandise sales data found.");
        
    }

    public void getUnitsSoldByName(String name)//Double Check Code
    {
        File myFile = new File(FilePathMerch);
        ArrayList<String[]> merchData = new ArrayList<>();
        try(Scanner myReader = new Scanner(myFile))
        {
            while(myReader.hasNextLine())
            {
                String[] data = myReader.nextLine().split(",");
                if(data[2].replaceAll("\\s+", "").toLowerCase().equals(name.toLowerCase().replaceAll("\\s+", "")))
                {
                    merchData.add(data);
                    System.out.println("Unit ID: " + data[0] + " Category ID: " + data[1] + " Name: " + data[2] + " Units Sold: " + data[3] + " Price: " + data[5] + " Date: " + data[6]);
                }
            }
            if (merchData.size()> 0) 
                {
                return;
            }

        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred while reading the merchandise sales: " + e.getMessage());
        }
    
        System.out.println("No merchandise sales data found.");
    }

    public void AddUnitsSold()
    {
            Map<Integer, String> Categories = getCatgories(true);
            //Get Categories from MerchandiseSalesCategories and list them below with numbers, indicating which number corresponds too
            System.out.println("Choose your Category: (if you do no not see the category you need type -1 and add a category)");
            String CategoryIDString;
            int CategoryID = 0;
            
            while(!(CategoryIDString = System.console().readLine()).matches("-?\\d+"))
            {
                System.out.println("Invalid input. Please enter a valid Category ID:");
            }
            CategoryID = Integer.parseInt(CategoryIDString);
            

            if(CategoryID == -1)
            {
                System.out.println("Enter the name of the new category:");
                String newCategory = System.console().readLine();
                //add new category to MerchandiseSalesCategories and get the id for that category
                CategoryID = addCategory(newCategory); 
            }
            else
            {
                while(!Categories.containsKey(CategoryID))
                {
                    System.out.println("Invalid Category ID. Please enter a valid Category ID:");
                    CategoryID = Integer.parseInt(System.console().readLine()); 
                }
            }
            

            System.out.println("Enter the Unit name:");
            String name = System.console().readLine();

            System.out.println("Enter the Units Sold:");
            int unitsSold = Integer.parseInt(System.console().readLine());

            System.out.println("Enter the price:");
            float price = 0;
            while(!priceValid)//Checks for valid price input
            {
                String priceInput = System.console().readLine();
                if(priceInput.matches("\\d+(\\.\\d{1,2})?"))
                {
                    price = Float.parseFloat(priceInput);
                    cost = price;
                    priceValid = true;
                }
                else
                {
                    System.out.println("Invalid price format. Please enter a valid price (ex: 19.99):");
                }
           }                                                                                                                                                    

            System.out.println("Enter the event date (YYYY-MM):");
            String MerchDateStr = System.console().readLine();
            String[] dateParts = MerchDateStr.split("-");
            while (!isDateValid(dateParts))
            {
                System.out.println("Invalid Year or month. Please enter as YYYY-MM:");
                MerchDateStr = System.console().readLine();
                dateParts = MerchDateStr.split("-");
            }
           
            MerchdateStr = YearMonth.parse(MerchDateStr).toString();
            
            

            try (FileWriter writer = new FileWriter(FilePathMerch, true))
            {
                writer.write(getNextMerchID() + ", " + CategoryID + ", " + name + ", " + unitsSold + ", " + ", " + price + ", " + MerchdateStr + "\n");
                System.out.println("Item added successfully.");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

    }

    public int addCategory(String categoryName)
    {
        int numberOfIDs = 0;
        HashMap<Integer, String> categories = new HashMap<>(getCatgories(false));
        try{
            //first counts the number of categories in database

            //Adds the new category with the approriate ID to the database
            FileWriter Writer = new FileWriter("Databases/MerchandiseSalesCategories.txt", true);
            
            Writer.write(categories.size() + 1 + ":" + categoryName + "\n");
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
        File myFile = new File(FilePathCategories);
        try(Scanner myReader = new Scanner(myFile)){
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

    public void deleteUnitDetailsByID(int id)
    {
        Path p = Paths.get(FilePathMerch);
        ArrayList<String[]> lines = new ArrayList<String[]>();
        try(Scanner myReader = new Scanner(p))
        {
            while(myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                String[] parts = data.split(",");
                if(Integer.parseInt(parts[0].trim()) != id)
                {
                    lines.add(parts);
                }
            }
        }
        catch(IOException e)
        {
            System.out.println("An error occurred while reading the merchandise sales: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter(FilePathMerch)) 
        {
            for (int i = 0; i < lines.size(); i++) 
            {
                writer.write(String.join(",", lines.get(i)));
                if (i < lines.size() - 1) 
                {
                writer.write("\n");
                }
            }  
            System.out.println("Item deleted successfully.");  
        }
        catch(IOException e)
        {
            System.out.println("An error occurred while writing the merchandise sales: " + e.getMessage());
        }
    }


    public ArrayList<String[]> findUnitByName(String name)
    {
        File myFile = new File(FilePathMerch);
        ArrayList<String[]> results = new ArrayList<>();
        try(Scanner myReader = new Scanner(myFile))
        {
            while(myReader.hasNextLine())
                {
                String[] data = myReader.nextLine().split(",");
                if(data[2].toLowerCase().replaceAll("\\s+", "").equals(name.toLowerCase().replaceAll("\\s+", "")))
                {
                    results.add(data);
                    System.out.println("Unit ID: " + data[0] + " Category ID: " + data[1] + " Name: " + data[2] + " Units Sold: " + data[3] + " Price: " + data[5] + " Date: " + data[6]);
                }
            }
            if(results.size() > 0)
            {
                return results;
            }
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred while reading the Names: " + e.getMessage());
        }
    
        System.out.println("No unit found with the given the name");
        return results;
    }

    public static String[][] getMerch()
    {
        File myFile = new File(FilePathMerch);
        ArrayList<String[]> merchData = new ArrayList<>();
        try(Scanner myReader = new Scanner(myFile))
        {
            while(myReader.hasNextLine())
                {
                String[] data = myReader.nextLine().split(",");
                merchData.add(data);
            }
            String[][] merchArray = new String[merchData.size()][];
            for(int i = 0; i < merchData.size(); i++)
            {
                merchArray[i] = merchData.get(i);
            }
            return merchArray;
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred while reading the merchandise sales: " + e.getMessage());
        }
    
        System.out.println("No merchandise sales data found.");
        return null;
    }

    private static int getNextMerchID()
    {
        String[][] merchData = getMerch();
        HashSet<Integer> hashTable = new HashSet<>();
        for(int i = 0; i< merchData.length; i++)
        {
            hashTable.add(Integer.parseInt(merchData[i][0]));
        }
        if(merchData == null || merchData.length == 0)
        {
            return -1;
        }
        else
        {
            for(int i = 1; i < merchData.length; i++)
            {
                if(!hashTable.contains(i))
                {
                    return i;
                }
                else if(i == merchData.length - 1)
                {
                    return merchData.length + 1;
                }
            }
            return -1;
        }
    }
    public ArrayList<String[]> findUnitByCategory(int categoryID)
    {
        File myFile = new File(FilePathMerch);
        ArrayList<String[]> results = new ArrayList<>();

        try(Scanner myReader = new Scanner(myFile))
        {
            while(myReader.hasNextLine())
                {
                String[] data = myReader.nextLine().split(",");
                if(Integer.parseInt(data[1].trim()) == categoryID)
                {
                    results.add(data);
                    System.out.println("Unit ID: " + data[0] + " Category ID: " + data[1] + " Name: " + data[2] + " Units Sold: " + data[3] + " Price: " + data[5] + " Date: " + data[6]);
                }
            }
            return results;
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred while reading the categories: " + e.getMessage());
        }
    
        System.out.println("No unit found with the given category ID.");
        return null;
    }
      
    private static boolean isDateValid(String[] dateParts)//Checks for Valid Date input
    {
        if (dateParts.length != 2) return false;
       
        String year = dateParts[0];
        String month = dateParts[1];

        if (!year.matches("\\d{4}") || !month.matches("\\d{2}"))
        return false;

        int m = Integer.parseInt(month);
        if(m < 1 || m > 12) return false;
        return true;
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