package Objects.RecordTypes;

public class PayPerViewController extends AbstractEventController {
    private static final String FilePathPayPerView = "Databases/PayPerViewSales.txt";

    public PayPerViewController(boolean editDatabase) {
        super(editDatabase);
    }

    @Override
    protected String getFilePath() {
        return FilePathPayPerView;
    }

    @Override
    protected String getEventTypeName() {
        return "Pay-Per-View Ticket";
    }

    // Maintained for backward compatibility across other components
    public String[][] getPayPerView() {
        return getEventData();
    }
}