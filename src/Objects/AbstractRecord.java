package src.Objects;
import java.time.YearMonth;

public abstract class AbstractRecord implements Record{
    private float cost;
    private YearMonth date;

    public AbstractRecord(){
        cost = 0;
        date = YearMonth.now();
    }


}
