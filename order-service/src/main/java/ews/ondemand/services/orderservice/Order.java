package ews.ondemand.services.orderservice;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
/**
 * @author Chandu D
 */


@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    Long id;
    @Column(name = "customer_id")
    Long customerId;
    @Column(name = "order_date")
    ZonedDateTime orderDate;
    @Column(name = "total_amount")
    BigDecimal totalAmount;
}
