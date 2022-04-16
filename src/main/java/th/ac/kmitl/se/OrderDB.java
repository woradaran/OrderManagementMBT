package th.ac.kmitl.se;

public interface OrderDB {
    public int getOrderID();
    public Boolean update(Order order);
    public Order retrieveOrder(int orderID);
}
