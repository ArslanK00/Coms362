package Objects.Strategies;

import java.util.List;

import Objects.RecordTypes.MerchandiseSale;
import Objects.RecordTypes.Record;

public class MerchandiseRevenueStrategy implements RevenueCalculationStrategy {

    @Override
    public float calculateRevenue(List<Record> records) {
        float total = 0;
        for (Record record : records) {
            if (record instanceof MerchandiseSale) {
                total += record.getCost();
            }
        }
        return total;
    }
}
