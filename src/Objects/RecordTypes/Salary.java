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
        return employee.getName() + " " + recordType + ": " + cost;
    }

}
