package Objects.RecordTypes;

public class LiveEventController extends AbstractEventController {
    private static final String FilePathLiveEvent = "Databases/LiveEventSales.txt";

    public LiveEventController(boolean editDatabase) {
        super(editDatabase);
    }

    @Override
    protected String getFilePath() {
        return FilePathLiveEvent;
    }

    @Override
    protected String getEventTypeName() {
        return "Live Event Ticket";
    }

    // Maintained for backward compatibility across other components
    public String[][] getLiveEvent() {
        return getEventData();
    }
}