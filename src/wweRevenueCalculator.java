import java.io.File;
import java.io.FileNotFoundException;
import java.time.YearMonth;
import java.util.Scanner;

import Objects.CustomSystem;
import Objects.Employee;
import Objects.Event;
import Objects.Factory.SalaryFactory;
import Objects.RecordTypes.LiveEventController;
import Objects.RecordTypes.LiveEventTicket;
import Objects.RecordTypes.MerchandiseController;
import Objects.RecordTypes.PayPerViewController;
import Objects.RecordTypes.PayPerViewTicket;
import Objects.RecordTypes.Salary;
//import Objects.RecordTypes.*;

public class wweRevenueCalculator {

    static CustomSystem wweSystem = new CustomSystem();

    public static void main(String[] args) {

        loadEventsFromFile();

        System.out.println("Welcome to the Ticketing System!");

        while (true) {
            System.out.println("Please select an option:");
            System.out.println("1 Add an Event to the System");
            System.out.println("2 View Events");
            System.out.println("3 Add Employee");
            System.out.println("4 View Employees");
            System.out.println("5 Add a Record to the System");
            System.out.println("6 View System Records");
            System.out.println("7 Calculate All Revenue");
            System.out.println("8 Edit merchandise");
            System.out.println("0 Exit");
            String choice = System.console().readLine();

            switch (choice) {
                case "1":
                    addEventToSystem();
                    break;
                case "2":
                    systemEvents();
                    break;
                case "3":
                    addEmployeeToSystem();
                    break;
                case "4":
                    systemEmployees();
                    break;
                case "5":
                    createARecord();
                    break;
                case "6":
                    systemRecords();
                    break;
                case "7":
                    // CalculateAll
                    calculateAllRevenue();
                    break;
                case "8":
                    MerchandiseController merch = new MerchandiseController(true);
                    break;
                case "0":
                    System.out.println("Exiting the system. Goodbye!");
                    System.exit(1);
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    /**
     * @author Benjamin Diaz 
     */
    private static void loadEventsFromFile() {
        File file = new File("Databases/Events.txt");
        if (!file.exists()) return;

        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                Event loadedEvent = Event.fromCSV(line);
                if (loadedEvent != null) {
                    wweSystem.addEvent(loadedEvent);
                }
            }
            System.out.println("Existing events loaded from database.");
        } catch (FileNotFoundException e) {
            System.out.println("No existing event database found.");
        }
    }

    private static void addEventToSystem() {
        String endUpload = "Y";

        while (!endUpload.equalsIgnoreCase("Y")) {
            System.out.println("Would you like to add an event to the system? (Y/N)");
            endUpload = System.console().readLine();
            if (endUpload.equalsIgnoreCase("N")) {
                return;
            } else if (endUpload.equalsIgnoreCase("Y")) {
                break;
            }
        }

        System.out.println("Enter the event name:");
        String eventName = System.console().readLine();

        System.out.println("Enter the event venue:");
        String eventVenue = System.console().readLine();

        Event newEvent = new Event(eventName, eventVenue);
        wweSystem.addEvent(newEvent);
        newEvent.saveToFile();
        System.out.println("Event added to the system: " + eventName + " at " + eventVenue);
    }

    /**
     * @author Eleena Rath
     */
    private static void systemEvents() {
        System.out.println("All events in system:");
        System.out.println(wweSystem.listEvents());

        while (true) {
            System.out.println("Please select an event by its number, or enter '0' to go back to the previous screen");
            String choice = System.console().readLine();

            try {
                int eventIndex = Integer.parseInt(choice);
                if (eventIndex == 0) {
                    return;
                }
                wweSystem.getEvent(eventIndex);
                eventController(eventIndex);

            } catch (NumberFormatException e) {
                System.out.println("Invalid input");
            } catch (IndexOutOfBoundsException f) {
                System.out.println("Invalid input");
            }
        }
    }

    /**
     * @author Eleena Rath
     * @param eventIndex
     */
    private static void eventController(int eventIndex) {
        Event event = wweSystem.getEvent(eventIndex);
        System.out.println(event.toString());

        while (true) {
            System.out.println("What would you like to do with this event?");
            System.out.println("1 View all records for this event");
            System.out.println("2 Add a Live-Event ticket record");
            System.out.println("3 Add a Pay-Per-View ticket record");
            System.out.println("4 Add an event salary");
            System.out.println("5 Delete a record");
            System.out.println("6 Calculate this event's profit");
            System.out.println("7 Sort records by value");
            System.out.println("8 Sort records by category");
            System.out.println("9 View revenue only");
            System.out.println("0 Record Arena Rental Cost For an Event");
            System.out.println("- Exit");

            String choice = System.console().readLine();
            switch (choice) {
                case "1":
                    syncRecordsFromFile(event);
                    System.out.println(event.listRecords());
                    break;
                case "2":
                    new LiveEventUploader().uploadAndSave(eventIndex);
                    break;
                case "3":
                    new PayPerViewUploader().uploadAndSave(eventIndex);
                    break;
                case "4":
                    SalaryFactory salaryFactory = new SalaryFactory(wweSystem);
                    salaryFactory.createRecord();
                    Salary eventSalary = salaryFactory.returnSalary();
                    wweSystem.addRecordToEvent(eventIndex, eventSalary);
                    break;
                case "5":
                    deleteRecordFromEvent(eventIndex);
                    break;
                case "6":
                    // Calculate the revenue for this event
                    System.out.println(event.calculateRevenue());
                    break;
                case "7":
                    event.sortByValue();
                    System.out.println(event.listRecords());
                    break;
                case "8":
                    event.sortByCategory();
                    System.out.println(event.listRecords());
                    break;
                case "9":
                    System.out.println("Revenue Only: " + event.calculateRevenueOnly());
                    break;
                case "0":
                    recordArenaRentalCost(eventIndex);
                    break;
                case "-":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    /**
     * @author Benjamin Diaz 
     */
    abstract static class TicketUploaderTemplate {
        public final void uploadAndSave(int eventIndex) {
            Event event = wweSystem.getEvent(eventIndex);
            String eventName = event.getName(); 

            System.out.println("Please Fill out the following information to upload a " + getTicketName() + ".");

            System.out.println("Enter a name for the record:");
            String name = System.console().readLine();

            boolean priceValid = false;
            float price = 0;
            System.out.println("Enter the price:");
            while (!priceValid) {
                String priceInput = System.console().readLine();
                if (priceInput.matches("\\d+(\\.\\d{1,2})?")) {
                    price = Float.parseFloat(priceInput);
                    priceValid = true;
                } else {
                    System.out.println("Invalid price format. Please enter a valid price (ex: 19.99):");
                }
            }

            int amount = 0;
            boolean amountValid = false;
            System.out.println("Enter the amount of tickets sold:");
            while (!amountValid) {
                try {
                    amount = Integer.parseInt(System.console().readLine());
                    if (amount >= 0) {
                        amountValid = true;
                    } else {
                        System.out.println("Amount must be 0 or greater:");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount. Enter a whole number:");
                }
            }

            boolean costValid = false;
            float cost = 0;
            System.out.println("Enter the cost/expense involved per ticket (enter 0 if n/a):");
            while (!costValid) {
                String costInput = System.console().readLine();
                if (costInput.matches("\\d+(\\.\\d{1,2})?")) {
                    cost = Float.parseFloat(costInput);
                    costValid = true;
                } else {
                    System.out.println("Invalid format. Please enter a valid cost (ex: 19.99):");
                }
            }

            YearMonth date = null;
            boolean dateValid = false;
            System.out.println("Enter the date (YYYY-MM):");
            while (!dateValid) {
                String dateInput = System.console().readLine();
                try {
                    date = YearMonth.parse(dateInput);
                    dateValid = true;
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please use YYYY-MM:");
                }
            }

            saveToFile(name, price, amount, cost, date, eventName);
            addTicketToSystem(eventIndex, name, price, amount, cost, date, eventName);
        }

        protected abstract String getTicketName();
        protected abstract void saveToFile(String name, float price, int amount, float cost, YearMonth date, String eventName);
        protected abstract void addTicketToSystem(int eventIndex, String name, float price, int amount, float cost, YearMonth date, String eventName);
    }

    /**
     * @author Benjamin Diaz 
     */
    static class LiveEventUploader extends TicketUploaderTemplate {
        @Override
        protected String getTicketName() { return "Live-Event ticket"; }

        @Override
        protected void saveToFile(String name, float price, int amount, float cost, YearMonth date, String eventName) {
            LiveEventController lec = new LiveEventController(false);
            lec.saveEventToFile(name, price, amount, cost, date, eventName);
        }

        @Override
        protected void addTicketToSystem(int eventIndex, String name, float price, int amount, float cost, YearMonth date, String eventName) {
            // Include eventName in constructor
            LiveEventTicket ticket = new LiveEventTicket(name, price * amount, date, amount, eventName);
            ticket.setCategory("Live Event");
            ticket.setRevenue(true);
            wweSystem.addRecordToEvent(eventIndex, ticket);
        }
    }

    /**
     * @author Benjamin Diaz 
     */
    static class PayPerViewUploader extends TicketUploaderTemplate {
        @Override
        protected String getTicketName() { return "Pay-Per-View ticket"; }

        @Override
        protected void saveToFile(String name, float price, int amount, float cost, YearMonth date, String eventName) {
            PayPerViewController pvc = new PayPerViewController(false);
            pvc.saveEventToFile(name, price, amount, cost, date, eventName);
        }

        @Override
        protected void addTicketToSystem(int eventIndex, String name, float price, int amount, float cost, YearMonth date, String eventName) {
            // Include eventName in constructor
            PayPerViewTicket ticket = new PayPerViewTicket(name, price * amount, date, amount, eventName);
            ticket.setCategory("Pay-Per-View");
            ticket.setRevenue(true);
            wweSystem.addRecordToEvent(eventIndex, ticket);
        }
    }
    
    /**
     * @author Eleena Rath
     * @param eventIndex
     */
    private static void deleteRecordFromEvent(int eventIndex) {
        Event event = wweSystem.getEvent(eventIndex);

        System.out.println(event.listRecords());
        while (true) {
            System.out.println("Please select an record by its number, or 'exit' to go back to the previous screen");
            String choice = System.console().readLine();

            try {
                int recordIndex = Integer.parseInt(choice);
                wweSystem.deleteRecordFromEvent(eventIndex, recordIndex);
                System.out.println("Record was successfully deleted");
                return;

            } catch (NumberFormatException e) {
                if (choice.equalsIgnoreCase("exit")) {
                    return;
                } else {
                    System.out.println("Invalid input");
                }
            } catch (IndexOutOfBoundsException f) {
                System.out.println("Invalid input");
            }
        }
    }

    /**
     * @author Matayas Durr
     *         Displays total revenue across all events
     */
    private static void calculateAllRevenue() {
        float totalRevenue = wweSystem.calculateAllRevenue();
        System.out.println("Total revenue across all events: " + totalRevenue);
    }

    private static boolean isDateValid(String[] dateParts)// Checks for Valid Date input
    {
        if (dateParts.length != 2)
            return false;

        String year = dateParts[0];
        String month = dateParts[1];

        if (!year.matches("\\d{4}") || !month.matches("\\d{2}"))
            return false;

        int m = Integer.parseInt(month);
        if (m < 1 || m > 12)
            return false;
        return true;
    }

    /**
     * @author Eleena Rath
     *         Description: Used to choose an employee in the system
     */
    private static void systemEmployees() {
        String choice;

        if (wweSystem.numberOfEmployees() == 0) {
            System.out.println("There are currently no employees in the system");
            return;
        } else {
            while (true) {
                System.out.println("\nEmployees: ");
                wweSystem.listEmployees();
                System.out.println("Please select an employee by number, or enter '0' to return the previous screen");
                choice = System.console().readLine();
                try {
                    int employeeIndex = Integer.parseInt(choice);
                    if (employeeIndex > 0 && employeeIndex <= wweSystem.numberOfEmployees()) {
                        employeeController(employeeIndex);
                    } else if (employeeIndex == 0) {
                        return;
                    } else {
                        System.out.println("Invalid option");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Invalid option");
                }
            }
        }
    }

    /**
     * @author Eleena Rath
     * @param emp
     *            Description: Used to manage employees in the system.
     */
    private static void employeeController(int emp) {
        Employee employee = wweSystem.getEmployee(emp);
        System.out.println(employee.toString());
        String choice;

        while (true) {
            System.out.println("What would you like to do with this employee?\n1 Edit\n0 Exit");
            choice = System.console().readLine();
            switch (choice) {
                case "1":
                    employee.editEmployee();
                    System.out.println(employee.toString());
                    break;
                case "0":
                    return;
            }
        }
    }

    /**
     * @author Eleena Rath
     *         Description: Creates a new employee and adds them to the system.
     */
    private static void addEmployeeToSystem() {
        String firstName, lastName;
        while (true) {
            System.out.println("Enter the employee's first name, or enter 'exit' to cancel");
            firstName = System.console().readLine();
            if (firstName.equals("exit")) {
                return;
            }
            System.out.println("Enter the employee's last name, or enter 'exit' to cancel");
            lastName = System.console().readLine();
            if (lastName.equals("exit")) {
                return;
            }

            if (firstName.length() < 2 || lastName.length() < 2) {
                System.out.println("Both the employee's first and last name must be at least two characters.");
            } else {
                Employee employee = new Employee(firstName, lastName);
                wweSystem.addEmployee(employee);
                System.out.println("Employee successfully created!");
                break;
            }
        }

    }

    /**
     * @author Eleena Rath
     *         For iteration 2: when a user chooses to add a record NOT tied to an
     *         event, such as an annual salary
     */
    private static void createARecord() {
        while (true) {
            System.out.println("Choose a record to add:\n1 Annual Salary\n0 Exit");
            String choice = System.console().readLine();

            switch (choice) {
                case "1":
                    // create a salary
                    SalaryFactory salaryFactory = new SalaryFactory(wweSystem);
                    salaryFactory.createRecord();
                    wweSystem.addRecord(salaryFactory.returnSalary());
                    System.out.println("Annual salary successfully created");
                    break;
                case "0":
                    return;
            }
        }
    }

    /**
     * @author Eleena Rath
     */
    private static void systemRecords() {
        //System.out.println("All Records in System:");
        //wweSystem.listRecords();
        //System.out.println("Select a record by its number, or enter '0' to exit");
        while (true) {
            System.out.println("All Records in System:");
            wweSystem.listRecords();
            System.out.println("Select a record by its number, or enter '0' to exit");
            String choice = System.console().readLine();

            try {
                if (choice.equals("0")) {
                    return;
                }
                // Potential compiler issues abound here.
                recordController((Objects.RecordTypes.Record) wweSystem.getRecord(Integer.parseInt(choice)));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Invalid input");
            }

        }
    }

    /**
     * @author Eleena Rath
     * @param record
     */
    public static void recordController(Objects.RecordTypes.Record record) {
        String choice;
        System.out.println(record.toString());
        while (true) {
            System.out.println("What would you like to do with this record?");
            System.out.println("1 Edit (not yet implemented)"); // TODO
            System.out.println("2 Delete (not yet implemented)"); // TODO
            System.out.println("0 Exit");

            choice = System.console().readLine();
            switch (choice) {
                case "0":
                    return;
            }
        }
    }

    /**
     * @author Jamey Nguyen
     *         Records arena rental cost for an event
     */
    private static void recordArenaRentalCost(int eventIndex) {
        RecordEventVenueCost arenaRecorder = new RecordEventVenueCost(wweSystem);
        arenaRecorder.recordArenaRentalCost(eventIndex);
    }

    /**
     * @author Benjamin Diaz 
     */
    private static void syncRecordsFromFile(Event event) {
        // Clear existing records to prevent duplicates during the reload
        event.getRecordsList().clear(); 

        String targetEvent = event.getName();

        // 1. Sync Live Event Tickets
        LiveEventController lec = new LiveEventController(false);
        String[][] liveData = lec.getLiveEvent(); //[cite: 1]
        for (String[] row : liveData) {
            if (row.length >= 7 && row[1].trim().equalsIgnoreCase(targetEvent)) {
                float price = Float.parseFloat(row[3].trim());
                int quantity = Integer.parseInt(row[4].trim());
                float totalCost = price * quantity;
                YearMonth date = YearMonth.parse(row[6].trim());
                
                LiveEventTicket ticket = new LiveEventTicket(row[2].trim(), totalCost, date, quantity, targetEvent);
                ticket.setCategory("Live Event");
                ticket.setRevenue(true);
                event.addRecord(ticket); //
            }
        }

        // 2. Sync Pay-Per-View Tickets
        PayPerViewController pvc = new PayPerViewController(false);
        String[][] ppvData = pvc.getPayPerView(); //[cite: 5]
        for (String[] row : ppvData) {
            if (row.length >= 7 && row[1].trim().equalsIgnoreCase(targetEvent)) {
                float price = Float.parseFloat(row[3].trim());
                int quantity = Integer.parseInt(row[4].trim());
                float totalCost = price * quantity;
                YearMonth date = YearMonth.parse(row[6].trim());

                PayPerViewTicket ticket = new PayPerViewTicket(row[2].trim(), totalCost, date, quantity, targetEvent);
                ticket.setCategory("Pay-Per-View");
                ticket.setRevenue(true);
                event.addRecord(ticket); //
            }
        }
    }

}
