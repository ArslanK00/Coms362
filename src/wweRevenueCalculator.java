import java.util.ArrayList;
import java.util.List;

import Objects.CustomSystem;
import Objects.Employee;
import Objects.Event;
import Objects.Factory.SalaryFactory;
import Objects.RecordTypes.MerchandiseController;
import Objects.Commands.*;
import Objects.Commands.EventCommands.*;


public class wweRevenueCalculator {

    static CustomSystem wweSystem = new CustomSystem();

    public static void main(String[] args) {

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

        List<Command> eventCommands = new ArrayList<>();
        eventCommands.add(new PrintEventRecords(event));
        eventCommands.add(new UploadLiveEventTicket(event));
        eventCommands.add(new UploadPayPerViewTicket(event));
        eventCommands.add(new UploadEventSalary(wweSystem, event));
        eventCommands.add(new RecordVenueRentalCost(event));
        eventCommands.add(new DeleteRecordFromEvent(event));
        eventCommands.add(new CalculateEventProfit(event));
        eventCommands.add(new EventRecordRevenueOnly(event));
        eventCommands.add(new SortEventRecordsByValue(event));
        eventCommands.add(new SortEventRecordsByCategory(event));


        while (true) {
            System.out.println("What would you like to do with this event?");
            for(int i = 0; i < eventCommands.size(); i++){
                System.out.println(i+1 + " " + eventCommands.get(i).toString());
            }
            System.out.println("- Exit");
            String choice = System.console().readLine();
            if(choice.equals("-")){
                return;
            }
            
            try{
                int intChoice = Integer.parseInt(choice);   //possible NumberFormatException
                eventCommands.get(intChoice-1).execute();   //possible IndexOutOfBoundsException
            } catch(Exception e){
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


}
