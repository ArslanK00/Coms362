package Objects.RecordTypes;

import java.io.Serializable;
import java.time.YearMonth;

public interface Record extends Serializable{

    public YearMonth getDate();

    public float getCost();

    public String getName();
}
