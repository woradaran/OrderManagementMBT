package th.ac.kmitl.se;

public interface ShippingService {
    public float getPrice(Address address, float weight);
    public String ship(Address address, float weight);
}
