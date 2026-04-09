package src.Objects;
import java.time.YearMonth;

public abstract class AbstractRecord implements Record{
    private float cost;
    private YearMonth date;
    private String description;

    public AbstractRecord(){
        cost = 0;
        date = YearMonth.now();
    }

    public String getDescription(){
        return description;
    }

    public void updateDescription(String description){
        this.description = description;
    }


}
