package Objects.Factory;

import java.time.YearMonth;

import Objects.CustomSystem;
import Objects.RecordTypes.Salary;

/**
 * @author Eleena Rath
 */
public class SalaryFactory extends RecordFactory{
    CustomSystem wweSystem;
    Salary newSalary;

    public SalaryFactory(CustomSystem wweSystem){
        this.wweSystem = wweSystem;
    }

    public Record createRecord(){
        //TODO

        String choice;
        //1. Choose the Employee
        System.out.println("\nAll employees:");
        wweSystem.listEmployees();
        while(true){
            System.out.println("Select the employee by number, or enter '0' to cancel");
            choice = System.console().readLine();
            
            try{
                int emp = Integer.parseInt(choice);

                if(emp == 0){
                    return null;
                }
                newSalary = new Salary("Employee Salary", wweSystem.getEmployee(emp));
                break;
            } catch (NumberFormatException e){
                System.out.println("Invalid input");
            } catch(IndexOutOfBoundsException f){
                System.out.println("Invalid input");
            }
        }

        //2. Get the cost of the salary (make it negative)
        while(true){
            System.out.println("Enter the employee's salary: ");
            choice = System.console().readLine();
            try{
                float chosenCost = Float.parseFloat(choice);
                newSalary.setCost(chosenCost);
                break;
            } catch(NumberFormatException e){
                System.out.println("Invalid input");
            } catch(NullPointerException f){
                newSalary.setCost(0);
            }
            
        }

        //3. Choose the date
        while(true){
            System.out.println("Enter the year and month in the format xxxx-xx");
            choice = System.console().readLine();
            choice = choice.trim();
            try{
                int year = Integer.parseInt(choice.substring(0,4));
                int month = Integer.parseInt(choice.substring(5,7));
                newSalary.setDate(YearMonth.of(year, month));
                break;

            } catch(Exception e){
                System.out.println("Invalid input");
            }

        }

        //4. Ask for optional description
        System.out.println("Press 'Enter' to skip this step");
        System.out.println("Enter a description");
        choice = System.console().readLine();
        newSalary.updateDescription(choice);

        return null;
    }

    public Salary returnSalary(){
        return newSalary;
    }
}
