package TrendData;
public abstract class TrendDataHandler
{
    public TrendDataHandler next;
    public void setNext(TrendDataHandler next)
    {
        this.next = next;
    }
    public abstract void handle(TrendRequest request);
}