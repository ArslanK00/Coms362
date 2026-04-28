package Objects.RecordTypes;

import java.time.YearMonth;

public abstract class RecordDecorator implements Record {

    protected Record wrappedRecord;

    public RecordDecorator(Record record) {
        this.wrappedRecord = record;
    }

    @Override
    public float getCost() {
        return wrappedRecord.getCost();
    }

    @Override
    public String getName() {
        return wrappedRecord.getName();
    }

    @Override
    public YearMonth getDate() {
        return wrappedRecord.getDate();
    }

    @Override
    public String toString() {
        return wrappedRecord.toString();
    }
}