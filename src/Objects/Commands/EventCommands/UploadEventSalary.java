package Objects.Commands.EventCommands;

import Objects.CustomSystem;
import Objects.Event;
import Objects.Commands.Command;
import Objects.Factory.SalaryFactory;
import Objects.RecordTypes.Salary;

/**
 * @author Eleena Rath
 */
public class UploadEventSalary implements Command{
    
    private CustomSystem database;
    private Event event;

    public UploadEventSalary(CustomSystem database, Event event){
        this.database = database;
        this.event = event;
    }

    public void execute(){
        SalaryFactory salaryFactory = new SalaryFactory(database);
        salaryFactory.createRecord();
        Salary eventSalary = salaryFactory.returnSalary();
        event.addRecord(eventSalary);
    }

    @Override
    public String toString(){
        
        return "Add an event salary";
    }
}
