package Objects;

import java.io.Serializable;

/**
 * @author Eleena Rath
 */
public class Employee implements Serializable{
    
    private String firstName;
    private String lastName;

    public Employee(String first, String last){
        firstName = first;
        lastName = last;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getName(){
        return firstName + " " + lastName;
    }

    public void editEmployee(){
        String newFirstName, newLastName;
        while(true){
            System.out.println("Employee's first name: ");
            newFirstName = System.console().readLine();
            System.out.println("Employee's last name: ");
            newLastName = System.console().readLine();

            if(newFirstName.length() < 2 || newLastName.length() < 2){
                System.out.println("The employee's first and last name must be at least two characters long.");
            }
            else{
                setFirstName(newFirstName);
                setLastName(newLastName);
                break;
            }
        }


    }

    @Override
    public String toString(){
        return "Employee: " + firstName + " " + lastName;
    }

}
