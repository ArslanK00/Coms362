package Objects.RecordTypes;
import java.time.YearMonth;

public abstract class AbstractRecord implements Record{
    protected RecordEnum recordType;
    protected String name;
    protected float cost;
    protected YearMonth date;
    protected String description;

    // Added by Matayas Durr: stores category for financial record review
    protected String category;

    // Added by Matayas Durr: identifies whether the record is revenue or cost
    protected boolean isRevenue;

    public AbstractRecord(String name){
        this.name = name;
        cost = 0;
        date = YearMonth.now();
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


    public float getCost(){
        return cost;
    }

    public void setCost(float cost){
        this.cost = cost;
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

    // Added by Matayas Durr: returns record category
    public String getCategory() {
        return category;
    }

    // Added by Matayas Durr: sets record category
    public void setCategory(String category) {
        this.category = category;
    }

    // Added by Matayas Durr: returns whether this record is revenue
    public boolean isRevenue() {
        return isRevenue;
    }

    // Added by Matayas Durr: sets whether this record is revenue
    public void setRevenue(boolean isRevenue) {
        this.isRevenue = isRevenue;
    }


}
