package ews.ondemand.services.paymentservice;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Chandu D
 */
@Getter
@Setter
public class OrderPageResponse {
    private List<Order> content;
}
