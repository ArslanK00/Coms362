package TrendData;

public class TrendRequest
{
    public RequestType type;
    public String DataVal;
    public TrendRequest(RequestType type, String DataVal)
    {
        this.type = type;
        this.DataVal = DataVal;
    }
}
