package Objects.Strategies;

import java.util.List;

import Objects.RecordTypes.MerchandiseController;
import Objects.RecordTypes.Record;

/*
* @author: Jamey Nguyen
*/
public class MerchandiseRevenueStrategy implements RevenueCalculationStrategy {

    @Override
    public float calculateRevenue(List<Record> records) {
        MerchandiseController merchandiseController = new MerchandiseController(false);
        return merchandiseController.calculateTotalRevenue();
    }
}
