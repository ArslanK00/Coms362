package src.Objects;

/**
 * Positive cost = revenue, negative cost = expense.
 */
public class Record {
    private final double cost;
    private final String description;

    public Record(double cost, String description) {
        this.cost = cost;
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }
}
