import Objects.RecordTypes.MerchandiseController;
import Objects.RecordTypes.MerchandiseSale;

public class MerchTest {
    public static void main(String[] args) 
    {
        //UploadMerchandiseSales merchSales = new UploadMerchandiseSales();
        MerchandiseController m = new MerchandiseController(false);
        MerchandiseSale merchsales = new MerchandiseSale("T-shirt", 20.0f, java.time.YearMonth.of(2020, 10));
        System.out.println(merchsales.toString());
        m.TurnAllMerchandiseToRecords(true);
        merchsales.EditMerchandiseDatabase();
        //merchsales.readRecords();
    }

}
