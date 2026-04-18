package Objects;

public class Employee {
    
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

    @Override
    public String toString(){
        return "Employee: " + "firstName" + "lastName";
    }

}
