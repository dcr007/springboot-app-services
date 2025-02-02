package ews.ondemand.services.orderservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ews.ondemand.services.orderservice.exception.OrderNotFoundException;

/**
 * @author Chandu D
 */

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderRepository orderRepository;

    @GetMapping("/{id}")
    public Order findById(@PathVariable Long id) {
        log.info("Searching for Order: {}", id);
        return this.orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Order id :{} does not exist",id);
                    return new OrderNotFoundException(id);
                });
    }
    //    http://localhost:8080/orders?page=0&size=3
    @GetMapping
    public Page<Order> getAll(Pageable pageable) {
        log.info("Fetching orders with pagination. Page: {}, Size: {}", pageable.getPageNumber(), pageable.getPageSize());

        // Ensure that the maximum page size is 5
        Pageable pageRequest = PageRequest.of(pageable.getPageNumber(), Math.min(pageable.getPageSize(), 5));

        return this.orderRepository.findAll(pageRequest);
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        // You can add validation or other logic here
        Order savedOrder = orderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        // Update fields based on the received orderDetails
        if (orderDetails.getTotalAmount() != null) {
            existingOrder.setTotalAmount(orderDetails.getTotalAmount());
        }
        if (orderDetails.getOrderDate() != null) {
            existingOrder.setOrderDate(orderDetails.getOrderDate());
        }
        if (orderDetails.getCustomerId() != null) {
            existingOrder.setCustomerId(orderDetails.getCustomerId());
        }
        // Add other fields to be updated as needed

        Order updatedOrder = orderRepository.save(existingOrder);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));

        orderRepository.delete(existingOrder);
        return ResponseEntity.noContent().build();
    }

}
