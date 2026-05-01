package Objects.Strategies;

import java.util.List;

import Objects.RecordTypes.PayPerViewTicket;
import Objects.RecordTypes.Record;

public class PayPerViewRevenueStrategy implements RevenueCalculationStrategy {

    @Override
    public float calculateRevenue(List<Record> records) {
        float total = 0;
        for (Record record : records) {
            if (record instanceof PayPerViewTicket) {
                total += record.getCost();
            }
        }
        return total;
    }
}
