package th.ac.kmitl.se;

public interface PaymentCallback {
    public void onSuccess(String successCode);
    public void onError(String errorCode);
}
