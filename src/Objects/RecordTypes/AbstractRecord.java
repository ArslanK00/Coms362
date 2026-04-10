package src.Objects.RecordTypes;
import java.time.YearMonth;

public abstract class AbstractRecord implements Record{
    protected RecordEnum recordType;
    protected String name;
    protected float cost;
    protected YearMonth date;
    protected String description;

    public AbstractRecord(String name){
        this.name = name;
        cost = 0;
        date = YearMonth.now();
    }

    public String getName(){
        return name;
    }



    public float getCost(){
        return cost;
    }

    public YearMonth getDate(){
        return date;
    }

    public String getDescription(){
        return description;
    }

    public void updateDescription(String description){
        this.description = description;
    }


}
