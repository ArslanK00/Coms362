package Objects.RecordTypes;

public class ArenaRentalCost extends AbstractRecord {
    private RecordEnum recordType = RecordEnum.ArenaRentalCost;
    private String arenaName;
    private float additionalFees;

    public ArenaRentalCost(String name, String arenaName) {
        super(name);
        this.arenaName = arenaName;
        this.additionalFees = 0;
    }

    public String getArenaName() {
        return arenaName;
    }

    public void setArenaName(String arenaName) {
        this.arenaName = arenaName;
    }

    public float getAdditionalFees() {
        return additionalFees;
    }

    public void setAdditionalFees(float additionalFees) {
        this.additionalFees = additionalFees;
    }

    @Override
    public String toString() {
        String summary = "Record Type: " + recordType + "\n"
                + "Name: " + name + "\n"
                + "Arena Name: " + arenaName + "\n"
                + "Rental Cost: $" + cost + "\n"
                + "Additional Fees: $" + additionalFees + "\n"
                + "Total Cost: $" + (cost + additionalFees) + "\n"
                + "Date: " + date + "\n";

        if (description == null || description.length() == 0) {
            return summary;
        }

        summary += "Description: " + description + "\n";
        return summary;
    }
}
