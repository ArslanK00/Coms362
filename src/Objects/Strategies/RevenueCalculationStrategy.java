package Objects.Strategies;

import java.util.List;

import Objects.RecordTypes.Record;

public interface RevenueCalculationStrategy {
    float calculateRevenue(List<Record> records);
}
