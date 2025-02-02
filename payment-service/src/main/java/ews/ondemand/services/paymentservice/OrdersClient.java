package ews.ondemand.services.paymentservice;

/**
 * @author Chandu D
 */
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "orders",url="http://order-service:8080/orders")
public interface OrdersClient {

   @GetMapping
   OrderPageResponse getAllOrders();

}
