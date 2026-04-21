package Objects.RecordTypes;

import java.time.YearMonth;
import Objects.Employee;

/**
 * @author Eleena Rath
 */
public class Salary extends AbstractRecord{
    private RecordEnum recordType = RecordEnum.Salary;
    private Employee employee;

    public Salary(String name){
        super(name);
    }

    public Salary(String name, Employee employee){
        super(name);
        this.employee = employee;
    }

    public YearMonth getDate(){
        return date;
    }

    public void setDate(YearMonth date){
        this.date = date;
    }

    public float getCost(){
        return cost;
    }

    public void setCost(float cost){
        this.cost = cost;
        if(cost > 0){
            this.cost *= -1;
        }
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


    //Override toString method
    @Override
    public String toString(){
        String summary = "";
        summary += employee.getName() + " " + recordType + ": " + cost
         +"\nDate: " + date  + "\nDescription: " + description;
        
        return summary;
    }

}
