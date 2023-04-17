package th.ac.kmitl.se;

import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.*;
import org.graphwalker.java.test.TestBuilder;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;


// Update the filename of the saved file of your model here.
@Model(file  = "model.json")
public class OrderAdapter extends ExecutionContext {
    // The following method add some delay between each step
    // so that we can see the progress in GraphWalker player.
    public enum Status {
        CREATED, PLACED, PAYMENT_CHECK, PAID, SHIPPED, PAYMENT_ERROR, CANCELED, AWAIT_REFUND, REFUNDED, REFUND_ERROR
    }
    public static int delay = 0;

    @AfterElement
    public void afterEachStep() {
        try
        {
            Thread.sleep(delay);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    OrderDB orderDB;
    ProductDB productDB;
    PaymentService paymentService;
    ShippingService shippingService;
    Order order;

    @BeforeExecution
    public void setUp() {
        orderDB = mock(OrderDB.class);
        productDB = mock(ProductDB.class);
        paymentService = mock(PaymentService.class);
        shippingService = mock(ShippingService.class);
        order = new Order(orderDB, productDB, paymentService, shippingService);
    }

    @Edge()
    public void reset() {
        order = new Order(orderDB, productDB, paymentService, shippingService);
        System.out.println("Edge reset");
    }

    @Edge()
    public void place() {
        order.place("customer1", "product1", 1, new Address(
                "John Doe",
                "123 Main St.",
                "Apt. 4",
                "Downtown",
                "Los Angeles",
                "90001"
        ));
        System.out.println("Edge place");
    }

    @Edge()
    public void cancel() {
        order.cancel();
        System.out.println("Edge cancel");
    }

    @Edge()
    public void pay() {
        Card card = new Card("62011295", "Worada", 2, 20);
        order.pay(card);
        System.out.println("Edge pay");
    }

    @Edge()
    public void retryPay() {
        Card card = new Card("62011295", "Worada", 2, 20);
        order.pay(card);
        System.out.println("Edge retryPay");
    }

    @Edge()
    public void paySuccess() {
        if (order.getStatus() == Order.Status.PAID) {
            System.out.println("Payment successful");
        }
        System.out.println("Edge paySuccess");
    }

    @Edge()
    public void payError() {
        if (order.getStatus() == Order.Status.PAYMENT_ERROR) {
            System.out.println("Payment Error");
        }
        System.out.println("Edge payError");
    }

    @Edge()
    public void ship() {
        order.ship();
        System.out.println("Edge ship");
    }

    @Edge()
    public void refundSuccess() {
        order.refund();
        System.out.println("Edge refundSuccess");
    }

    @Edge()
    public void refundError() {
        if (order.getStatus() == Order.Status.REFUND_ERROR) {
            order.refund();
        }
        System.out.println("Edge refundError");
    }
}