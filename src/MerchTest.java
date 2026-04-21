import Objects.RecordTypes.MerchandiseSales;

public class MerchTest {
    public static void main(String[] args) 
    {
        //UploadMerchandiseSales merchSales = new UploadMerchandiseSales();
        MerchandiseSales merchsales = new MerchandiseSales("T-shirt", 20.0f, java.time.YearMonth.of(2020, 10));
        merchsales.TurnAllMerchandiseToSystemRecords(true);
    }

}
