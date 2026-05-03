package Objects.Strategies;

import java.util.List;

import Objects.RecordTypes.Record;

/*
* @author: Jamey Nguyen
*/
public class TotalRevenueStrategy implements RevenueCalculationStrategy {

    @Override
    public float calculateRevenue(List<Record> records) {
        float total = 0;
        for (Record record : records) {
            total += record.getCost();
        }
        return total;
    }
}
