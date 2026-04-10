package src.Objects.RecordTypes;

public class PayPerViewTicket extends AbstractRecord{
    private RecordEnum recordType = RecordEnum.PayPerViewTicket;

    public PayPerViewTicket(int id, String name){
        super(name);
    }

    @Override
    public String toString(){
        String summary = "Record Type: " + recordType + "\n"
        + "Name: " + name + "\n"
        + "Cost: " + cost + "\n"
        + "Date: " + date + "\n";
        
        if(description == null || description.length() == 0){
            return summary;
        }

        summary += "Description: " + description + "\n";
        return summary;
    }


}
