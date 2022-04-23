package th.ac.kmitl.se;

import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;

// Update the filename of the saved file of your model here.
@Model(file  = "model.json")
public class OrderAdapter extends ExecutionContext {
    // The following method add some delay between each step
    // so that we can see the progress in GraphWalker player.
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
        // Add the code here to be executed before
        // GraphWalk starts traversing the model.

    }

    @Edge()
    public void reset() {
        System.out.println("Edge reset");
    }

    @Edge()
    public void place() {
        System.out.println("Edge place");
    }

    @Edge()
    public void cancel() {
        System.out.println("Edge cancel");
    }

    @Edge()
    public void pay() {
        System.out.println("Edge pay");
    }

    @Edge()
    public void retryPay() {
        System.out.println("Edge retryPay");
    }

    @Edge()
    public void paySuccess() {
        System.out.println("Edge paySuccess");
    }

    @Edge()
    public void payError() {
        System.out.println("Edge payError");
    }

    @Edge()
    public void ship() {
        System.out.println("Edge ship");
    }

    @Edge()
    public void refundSuccess() {
        System.out.println("Edge refundSuccess");
    }

    @Edge()
    public void refundError() {
        System.out.println("Edge refundError");
    }
}
