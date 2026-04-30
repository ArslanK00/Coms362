package Objects.RecordTypes;

import java.time.YearMonth;

public class MerchandiseSale extends AbstractRecord
{
    private String name;
    private float cost;
    private YearMonth date;
    private RecordEnum RecType = RecordEnum.MerchandiseSale;
    public MerchandiseSale(String name, float cost, YearMonth date) 
    {
        super(name);
        this.name = name;
        this.cost = cost;
        this.date = date;
    }


    @Override
    public YearMonth getDate() 
    {
        return date;
    }

    @Override
    public float getCost() {
        return cost;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setDate(YearMonth y) 
    {
        this.date = y;
    }

    @Override
    public void setCost(float c) {
        this.cost = c;
    }

    @Override
    public void setName(String n) {
        super.setName(n);
        this.name = n;
    }


    @Override
    public String toString(){
        String summary = "Record Type: " + RecType + "\n"
        + "Name: " + getName() + "\n"
        + "Cost: " + cost + "\n"
        + "Date: " + date + "\n";
        
        if(description == null || description.length() == 0){
            return summary;
        }

        summary += "Description: " + description + "\n";
        return summary;
    }
}
