
/**
 * @author Chandu D
 */
package ews.ondemand.services.orderservice.exception;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class OrderNotFoundException extends RuntimeException {

    private Long orderId;

    public OrderNotFoundException(Long orderId) {

        super("Order not found with id: " + orderId);
        log.debug("Invalid OrderID: {}",orderId);
        this.orderId = orderId;
    }

}
