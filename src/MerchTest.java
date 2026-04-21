import Objects.RecordTypes.MerchandiseSale;

public class MerchTest {
    public static void main(String[] args) 
    {
        //UploadMerchandiseSales merchSales = new UploadMerchandiseSales();
        MerchandiseSale merchsales = new MerchandiseSale("T-shirt", 20.0f, java.time.YearMonth.of(2020, 10));
        merchsales.TurnAllMerchandiseToRecords(true);
        merchsales.EditMerchandiseDatabase();
        //merchsales.readRecords();
    }

}
