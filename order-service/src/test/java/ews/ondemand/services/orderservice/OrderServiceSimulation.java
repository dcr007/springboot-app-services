package ews.ondemand.services.orderservice;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
import io.gatling.javaapi.core.FeederBuilder;

/**
 * @author Chandu D
 */
public class OrderServiceSimulation extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .acceptHeader("application/json");

    int orderID = randomNumberBetween(4,100);
    int customerId = randomNumberBetween(1000,5000);





    private ScenarioBuilder orderServiceScenario = scenario("Order Service Load Test")

            .exec(http("Get Order").get("/orders/1"))
            .pause(1)
            .exec(http("Get Order").get("/orders/2"))
            .pause(1)
            .exec(http("Get Order").get("/orders/4"))
            .pause(1)
            .exec(http("Create Order").post("/orders")
                    .header("Content-Type", "application/json")
                    .body(StringBody("""
                                {
                                  "id": 5,
                                  "customerId": 23,
                                  "orderDate": "2023-11-25T23:33:12.130+02:00",
                                  "totalAmount": 100.50
                                }
                            """)))
            .pause(1)
            .exec(http("Get Created Order").get("/orders/5"))
            .pause(1)
            .exec(http("Update Order").patch("/orders/1")
                    .header("Content-Type", "application/json")
                    .body(StringBody("""
                                {
                                  "totalAmount": 120.75
                                }
                            """)))
            .pause(1)
            .exec(http("Delete Order").delete("/orders/1"));

    {
        setUp(
                orderServiceScenario.injectOpen(rampUsers(10).during(10))
        ).protocols(httpProtocol);
    }

    public static int randomNumberBetween(int min, int max){
        return new Random().nextInt(max - min + 1) + min;
    }

}



