package Objects.RecordTypes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class LiveEventController 
{
    private static String FilePathLiveEvent = "Databases/LiveEventSales.txt";
    //private boolean priceValid = false;
    private int choice = 0;

    public LiveEventController(boolean editDatabase)
    {
        while(choice != -1 && editDatabase)
        {
            System.out.println("Choose an option:\n"
                + "1. Add Live Event Ticket\n"
                + "2. Delete Event by ID\n"
                + "3. Find Event by Name\n"
                + "4. Get Revenue by Month\n"
                + "5. Get Revenue by Year\n"
                + "-1 Exit");

            choice = Integer.parseInt(System.console().readLine());

            switch(choice)
            {
                case 1:
                    addLiveEvent();
                    break;
                case 2:
                    System.out.println("Enter ID:");
                    deleteByID(Integer.parseInt(System.console().readLine()));
                    break;
                case 3:
                    System.out.println("Enter name:");
                    findByName(System.console().readLine());
                    break;
                case 4:
                    System.out.println("Enter month (MM):");
                    revenueByMonth(System.console().readLine());
                    break;
                case 5:
                    System.out.println("Enter year (YYYY):");
                    revenueByYear(System.console().readLine());
                    break;
                case -1:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public String[][] getLiveEvent()
    {
        File file = new File(FilePathLiveEvent);
        ArrayList<String[]> data = new ArrayList<>();

        try(Scanner reader = new Scanner(file))
        {
            while(reader.hasNextLine())
            {
                data.add(reader.nextLine().split(","));
            }

            String[][] arr = new String[data.size()][];
            for(int i = 0; i < data.size(); i++)
            {
                arr[i] = data.get(i);
            }

            return arr;
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error reading live events: " + e.getMessage());
        }

        return new String[0][0];
    }

    // 🔹 Add event
    public void addLiveEvent()
    {
        try(FileWriter writer = new FileWriter(FilePathLiveEvent, true))
        {
            int id = getNextID();

            System.out.println("Enter event name:");
            String name = System.console().readLine();

            System.out.println("Enter ticket price:");
            float price = checkPrice(System.console().readLine());

            System.out.println("Enter tickets sold:");
            int quantity = Integer.parseInt(System.console().readLine());

            System.out.println("Enter cost/expense:");
            float cost = checkPrice(System.console().readLine());

            System.out.println("Enter date (YYYY-MM):");
            String dateStr = System.console().readLine();
            YearMonth date = YearMonth.parse(dateStr);

            writer.write(id + ", 0, " + name + ", " + price + ", " + quantity + ", " + cost + ", " + date + "\n");

            System.out.println("Live event added.");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    // 🔹 Delete
    public void deleteByID(int id)
    {
        ArrayList<String[]> lines = new ArrayList<>();

        try(Scanner reader = new Scanner(new File(FilePathLiveEvent)))
        {
            while(reader.hasNextLine())
            {
                String[] parts = reader.nextLine().split(",");
                if(Integer.parseInt(parts[0].trim()) != id)
                {
                    lines.add(parts);
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        try(FileWriter writer = new FileWriter(FilePathLiveEvent))
        {
            for(String[] line : lines)
            {
                writer.write(String.join(",", line) + "\n");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Deleted.");
    }

    // 🔹 Find
    public void findByName(String name)
    {
        String[][] data = getLiveEvent();

        for(String[] row : data)
        {
            if(row[2].trim().equalsIgnoreCase(name))
            {
                System.out.println("ID: " + row[0] + " Name: " + row[2] + " Sold: " + row[4]);
            }
        }
    }

    // 🔹 Revenue by Month
    public float revenueByMonth(String month)
    {
        String[][] data = getLiveEvent();
        float total = 0;

        for(String[] row : data)
        {
            if(row[6].split("-")[1].equals(month))
            {
                total += Float.parseFloat(row[3]) * Integer.parseInt(row[4]);
            }
        }

        System.out.println("Revenue (Month): " + total);
        return total;
    }

    // 🔹 Revenue by Year
    public float revenueByYear(String year)
    {
        String[][] data = getLiveEvent();
        float total = 0;

        for(String[] row : data)
        {
            if(row[6].split("-")[0].equals(year))
            {
                total += Float.parseFloat(row[3]) * Integer.parseInt(row[4]);
            }
        }

        System.out.println("Revenue (Year): " + total);
        return total;
    }

    // 🔹 ID generator
    private int getNextID()
    {
        String[][] data = getLiveEvent();
        HashSet<Integer> ids = new HashSet<>();

        for(String[] row : data)
        {
            ids.add(Integer.parseInt(row[0].trim()));
        }

        int i = 1;
        while(ids.contains(i)) i++;
        return i;
    }

    // 🔹 Price validation
    private float checkPrice(String input)
    {
        while(true)
        {
            if(input.matches("\\d+(\\.\\d{1,2})?"))
            {
                return Float.parseFloat(input);
            }
            System.out.println("Invalid price:");
            input = System.console().readLine();
        }
    }
}