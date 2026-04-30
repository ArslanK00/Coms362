package Objects.RecordTypes;

import java.time.YearMonth;

public abstract class AbstractTicket extends AbstractRecord {
    protected String name;
    protected float cost;
    protected YearMonth date;
    protected int amount;
    protected String FilePathToRecords = "Databases/Records.txt";
    protected RecordEnum RecType;

    public AbstractTicket(String name, float cost, YearMonth date, int amount, RecordEnum recType) {
        super(name);
        this.name = name;
        this.cost = cost;
        this.date = date;
        this.amount = amount;
        this.RecType = recType;
    }

    public AbstractTicket(String name) {
        super(name);
    }

    @Override
    public YearMonth getDate() {
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

    public int getAmount() {
        return amount;
    }

    @Override
    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        String summary = "Record Type: " + RecType + "\n"
                + "Name: " + name + "\n"
                + "Cost: " + cost + "\n"
                + "Date: " + date + "\n"
                + "Amount: " + amount + "\n";
        
        if (description == null || description.length() == 0) {
            return summary;
        }

        summary += "Description: " + description + "\n";
        return summary;
    }
}