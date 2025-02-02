package ews.ondemand.services.paymentservice;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * @author Chandu D
 */

@Getter
@Setter
@ToString
public class Order {
    Long id;
    Long customerId;
    ZonedDateTime orderDate;
    BigDecimal totalAmount;
}
