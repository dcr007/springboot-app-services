package ews.ondemand.services.orderservice;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Chandu D
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
