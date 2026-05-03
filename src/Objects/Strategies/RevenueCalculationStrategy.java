package Objects.Strategies;

import java.util.List;

import Objects.RecordTypes.Record;

/*
* @author: Jamey Nguyen
*/
public interface RevenueCalculationStrategy {
    float calculateRevenue(List<Record> records);
}
