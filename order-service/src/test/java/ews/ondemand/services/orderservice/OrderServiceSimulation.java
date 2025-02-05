package ews.ondemand.services.orderservice;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;
/**
 * @author Chandu D
 */
public class OrderServiceSimulation extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .acceptHeader("application/json");

    private ScenarioBuilder orderServiceScenario = scenario("Order Service Load Test")
            .exec(http("Get Order 1").get("/orders/1"))
            .pause(1)
            .exec(http("Get Order 2").get("/orders/2"))
            .pause(1)
            .exec(http("Get Order 4").get("/orders/4"))
            .pause(1)
            .exec(http("Create Order 4").post("/orders")
                    .header("Content-Type", "application/json")
                    .body(StringBody("""
                                {
                                  "id": 4,
                                  "customerId": 4,
                                  "orderDate": "2023-11-25T23:33:12.130+02:00",
                                  "totalAmount": 100.50
                                }
                            """)))
            .pause(1)
            .exec(http("Get Created Order 4").get("/orders/4"))
            .pause(1)
            .exec(http("Update Order 1").patch("/orders/1")
                    .header("Content-Type", "application/json")
                    .body(StringBody("""
                                {
                                  "totalAmount": 120.75
                                }
                            """)))
            .pause(1)
            .exec(http("Get All Orders").get("/"))
            .pause(1)
            .exec(http("Delete Order 4").delete("/orders/4"));

    {
        setUp(
                orderServiceScenario.injectOpen(rampUsers(2).during(10))
        ).protocols(httpProtocol);
    }


}



