package th.ac.kmitl.se;

public interface PaymentService {
    public void pay(Card card, float amount, PaymentCallback callback);
    public void refund(String paymentConfirmCode, PaymentCallback callback);
}
