package th.ac.kmitl.se;

import org.graphwalker.java.test.*;
import org.graphwalker.core.machine.Context;
import org.graphwalker.core.condition.*;
import org.graphwalker.core.generator.*;
import org.graphwalker.websocket.WebSocketServer;
import org.junit.jupiter.api.*;

class OrderTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    public void testMBT() throws java.io.IOException {
        TestExecutor executor = new TestExecutor(OrderAdapter.class);
        Context context = executor.getMachine().getCurrentContext();
        context.setPathGenerator(new RandomPath(new EdgeCoverage(50)));

        /* Uncomment the following three lines to enable GraphWalker player */
        //OrderAdapter.delay = 500;
        //WebSocketServer server = new WebSocketServer(8887, executor.getMachine());
        //server.start();

        Result result = executor.execute(true);
        if (result.hasErrors()) {
            for (String error : result.getErrors()) {
                System.out.println(error);
            }
        }
        System.out.println("Done: [" + result.getResults().toString(2) + "]");
    }

}