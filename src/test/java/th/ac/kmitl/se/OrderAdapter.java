package th.ac.kmitl.se;

import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.*;
import org.graphwalker.java.test.Executor;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;
import org.graphwalker.websocket.WebSocketServer;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.*;
import static org.mockito.Mockito.*;

@Model(file  = "model.json")
@GraphWalker(value = "random(edge_coverage(100))")
public class OrderAdapter extends ExecutionContext {

    /* The following method add some delay between each step
    so that we can see the progress in GraphWalker player.
     */
    @AfterElement
    public void delay() {
        try
        {
            Thread.sleep(500);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    @Edge()
    public void reset() {
        System.out.println("Edge reset");
    }

    @Edge()
    public void cancel() {
        System.out.println("Edge cancel");
    }

    @Vertex()
    public void PAYMENT_ERROR() {
        System.out.println("Vertex PAYMENT_ERROR");
    }

    @Edge()
    public void refundSuccess() {
        System.out.println("Edge refundSuccess");
    }

    @Vertex()
    public void PAYMENT_CHECK() {
        System.out.println("Vertex PAYMENT_CHECK");
    }

    @Edge()
    public void refundError() {
        System.out.println("Edge refundError");
    }

    @Vertex()
    public void REFUNDED() {
        System.out.println("Vertex REFUNDED");
    }

    @Vertex()
    public void PAID() {
        System.out.println("Vertex PAID");
    }

    @Edge()
    public void pay() {
        System.out.println("Edge pay");
    }

    @Edge()
    public void ship() {
        System.out.println("Edge ship");
    }

    @Vertex()
    public void REFUND_ERROR() {
        System.out.println("Vertex REFUND_ERROR");
    }
    @Vertex()
    public void AWAIT_REFUND() {
        System.out.println("Vertex AWAIT_REFUND");
    }
    @Vertex()
    public void PLACED() {
        System.out.println("Vertex PLACED");
    }
    @Edge()
    public void paySuccess() {
        System.out.println("Edge paySuccess");
    }

    @Vertex()
    public void CREATED() {
        System.out.println("Vertex CREATED");
    }
    @Vertex()
    public void CANCELED() {
        System.out.println("Vertex CANCELED");
    }

    @Edge()
    public void place() {
        System.out.println("Edge place");
    }

    @Edge()
    public void payError() {
        System.out.println("Edge payError");
    }
    @Vertex()
    public void SHIPPED() {
        System.out.println("Vertex SHIPPED");
    }

    @Test
    public void mbtTest() throws java.io.IOException {
        Executor executor = new TestExecutor(this.getClass());

        /* Uncomment the following two lines to enable GraphWalker player */
        // WebSocketServer server = new WebSocketServer(8887, executor.getMachine());
        // server.start();

        Result result = executor.execute(true);
        if (result.hasErrors()) {
            for (String error : result.getErrors()) {
                System.out.println(error);
            }
        }
        System.out.println("Done: [" + result.getResults().toString(2) + "]");
    }

}
