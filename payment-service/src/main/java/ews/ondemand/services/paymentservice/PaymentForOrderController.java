package ews.ondemand.services.paymentservice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Chandu D
 */
@RestController
@RequestMapping("/payments")
@Slf4j
public class PaymentForOrderController {
    @Autowired
    OrdersClient ordersClient;

    @GetMapping("/getAllOrders")
    public List<Order> fetchAllOrders(){
        List<Order> allOrders = ordersClient.getAllOrders().getContent();
        log.info("Orders fetched are : {}",allOrders.toString());
        return allOrders;

    }

    @GetMapping("/processPayment/{orderId}")
    public String processPayment(@PathVariable String orderId) {
        log.info("Processing Payment for orderId:  {} ",orderId);

        BigInteger fibonacci = new BigInteger(orderId);

        String paymentCalculated = getFibonacciNumber(fibonacci).toString();
        log.info("Payment to be made : {} ",paymentCalculated);

        return "paymentCalculated: $"+paymentCalculated;
    }

    public BigInteger getFibonacciNumber(BigInteger n){
        if(n == BigInteger.valueOf(0)){
            return BigInteger.valueOf(0);
        }
        if(BigInteger.ONE.equals(n) || BigInteger.valueOf(2).equals(n)){
            return BigInteger.ONE;
        }
        return getFibonacciNumber( n.subtract(BigInteger.valueOf(2)) )
                .add(getFibonacciNumber(n.subtract(BigInteger.ONE)));
    }

}
