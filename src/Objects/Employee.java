package Objects;

import java.util.Scanner;

public class Employee {
    
    private String firstName;
    private String lastName;

    public Employee(String first, String last){
        firstName = first;
        lastName = last;
    }

    private void setFirstName(String firstName){
        this.firstName = firstName;
    }

    private void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getName(){
        return firstName + " " + lastName;
    }

    public void editEmployee(){
        Scanner scanner = new Scanner(System.in);
        String newFirstName, newLastName;
        while(true){
            System.out.println("Employee's first name: ");
            newFirstName = scanner.next();
            System.out.println("Employee's last name: ");
            newLastName = scanner.next();

            if(newFirstName.length() < 2 || newLastName.length() < 2){
                System.out.println("The employee's first and last name must be at least two characters long.");
            }
            else{
                setFirstName(newFirstName);
                setLastName(newLastName);
                scanner.close();
                break;
            }
        }


    }

    @Override
    public String toString(){
        return "Employee: " + "firstName" + "lastName";
    }

}
