package Objects.Strategies;

import java.util.List;

import Objects.RecordTypes.AbstractRecord;
import Objects.RecordTypes.Record;

public class RevenueOnlyStrategy implements RevenueCalculationStrategy {

    @Override
    public float calculateRevenue(List<Record> records) {
        float total = 0;
        for (Record record : records) {
            if (record instanceof AbstractRecord && ((AbstractRecord) record).isRevenue()) {
                total += record.getCost();
            }
        }
        return total;
    }
}
