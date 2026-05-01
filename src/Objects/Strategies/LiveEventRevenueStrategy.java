package Objects.Strategies;

import java.util.List;

import Objects.RecordTypes.LiveEventTicket;
import Objects.RecordTypes.Record;

public class LiveEventRevenueStrategy implements RevenueCalculationStrategy {

    @Override
    public float calculateRevenue(List<Record> records) {
        float total = 0;
        for (Record record : records) {
            if (record instanceof LiveEventTicket) {
                total += record.getCost();
            }
        }
        return total;
    }
}
