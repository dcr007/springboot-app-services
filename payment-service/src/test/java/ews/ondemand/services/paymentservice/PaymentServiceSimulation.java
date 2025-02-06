package ews.ondemand.services.paymentservice;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;

/**
 * @author Chandu D
 */
public class PaymentServiceSimulation extends Simulation {

    private HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8081")
            .acceptHeader("application/json");

    private ScenarioBuilder paymentServiceScenario = scenario("PaymentService Load Test")
            .exec(http("Get All Orders").get("/payments/getAllOrders"))
            .pause(1)
            .exec(http("Process Payment - Low CPU Test").get("/payments/processPayment/3"))
            .pause(1)
            .exec(http("Process Payment - Low CPU Test").get("/payments/processPayment/6"))
            .pause(1)
            .exec(http("Process Payment - Low CPU Test").get("/payments/processPayment/16"))
            .pause(1)
            .exec(http("Process Payment - High CPU Test").get("/payments/processPayment/40"))
            .pause(1)
            .exec(http("Process Payment - High CPU Test").get("/payments/processPayment/45"));
    {
        setUp(
                paymentServiceScenario.injectOpen(rampUsers(10).during(10))
        ).protocols(httpProtocol);
    }


}



