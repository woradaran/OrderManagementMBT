package th.ac.kmitl.se;

public class Order {

    public enum Status {
        CREATED, PLACED, PAYMENT_CHECK, PAID, SHIPPED, PAYMENT_ERROR, CANCELED, AWAIT_REFUND, REFUNDED, REFUND_ERROR
    }

    private Status status;
    OrderDB orderDB;
    ProductDB productDB;
    PaymentService paymentService;
    ShippingService shippingService;
    int orderID;
    String customerID;
    Address address;
    String productID;
    int quantity;
    String paymentConfirmCode;
    String trackingCode;

    public Order(OrderDB orderDB, ProductDB productDB, PaymentService paymentService, ShippingService shippingService) {
        status = Status.CREATED;
        this.orderDB = orderDB;
        this.productDB = productDB;
        this.paymentService = paymentService;
        this.shippingService = shippingService;
    }

    public Status getStatus() {
        return status;
    }

    public void place(String customerID, String productID, int quantity, Address address) {
        if (status==Status.CREATED) {
            this.customerID = customerID;
            this.productID = productID;
            this.quantity = quantity;
            this.address = address;
            orderID = orderDB.getOrderID();
            status = Status.PLACED;
            orderDB.update(this);
        }
    }

    public float getTotalCost() {
        float productPrice = productDB.getPrice(productID);
        float weight = productDB.getWeight(productID) * quantity;
        float shippingCost = shippingService.getPrice(address, weight);
        return (productPrice*quantity) + shippingCost;
    }

    public void pay(Card card) {
        if (status==Status.PLACED || status==Status.PAYMENT_ERROR) {
            float amount = getTotalCost();
            status = Status.PAYMENT_CHECK;
            orderDB.update(this);
            paymentService.pay(card, amount, new PaymentCallback() {
                public void onSuccess(String code) {
                    status = Status.PAID;
                    paymentConfirmCode = code;
                    orderDB.update(Order.this);
                }

                public void onError(String code) {
                    status = Status.PAYMENT_ERROR;
                    orderDB.update(Order.this);
                }
            });
        }
    }

    public void ship() {
        if (status==Status.PAID) {
            float productWeight = productDB.getWeight(productID);
            float weight = productWeight * quantity;
            trackingCode = shippingService.ship(address, weight);
            status = Status.SHIPPED;
            orderDB.update(this);
        }
    }

    public void refund() {
        if (status==Status.PAID) {
            status = Status.AWAIT_REFUND;
            orderDB.update(this);
            paymentService.refund(paymentConfirmCode, new PaymentCallback() {
                public void onSuccess(String code) {
                    status = Status.REFUNDED;
                    orderDB.update(Order.this);
                }

                public void onError(String code) {
                    status = Status.REFUND_ERROR;
                    orderDB.update(Order.this);
                }
            });
        }
    }

    public void cancel() {
        if (status == Status.PLACED) {
            status = Status.CANCELED;
            orderDB.update(this);
        } else if (status == Status.PAID) {
            refund();
        }
    }
}
