# Spring Boot App

### Setup 

Use Google Jib to create a Docker Image `order-service:0.0.1-SNAPSHOT` `payment-service:0.0.1-SNAPSHOT` that you see in `docker-compose.yml` file.


1. To create `order-service:0.0.5-SNAPSHOT` by using `gradlew` with `Java 17`
```shell
mvn clean package jib:dockerBuild -DskipTests --offline
```

2. Then, to spin up all the `docker-compose.yml`
```shell
docker compose up -d
```
### Steps to Execute the API requests using curl

1. Send sample requests to `Order Service`
    
    ```shell 
    curl -X GET http://localhost:8080/orders/1
    ```
     ```shell 
    curl -X GET http://127.0.0.1:37353/orders/2
    ```
   
    ```shell 
    curl -X GET http://localhost:8080/orders/2
    ```
    ```shell 
    curl -X GET http://localhost:8080/orders/4
    ```
    ```shell 
    curl -X POST http://localhost:8080/orders \
         -H "Content-Type: application/json" \
         -d '{
               "id": 4,
               "customerId": 4,
               "orderDate": "2023-11-25T23:33:12.130+02:00",
               "totalAmount": 100.50
             }'
    ```
   ```shell 
   curl -X POST http://127.0.0.1:37353/orders \
   -H "Content-Type: application/json" \
   -d '{
   "id": 34,
   "customerId": 4,
   "orderDate": "2023-11-25T23:33:12.130+02:00",
   "totalAmount": 100.50
   }'
    ```

    ```shell 
    curl -X GET http://localhost:8080/orders/4
    ```
    ```shell 
    curl -X PATCH http://localhost:8080/orders/1 \
         -H "Content-Type: application/json" \
         -d '{
               "totalAmount": 120.75
             }'
    ```
    ```shell 
    curl -X GET http://localhost:8080 
    ```
    ```shell 
    curl -X DELETE http://localhost:8080/orders/4
    ```
2. Send sample requests to `Payment Service` on api: `getAllOrders`

    ```shell 
    curl -X GET http://localhost:8081/payments/getAllOrders
    ```
```shell 
   curl -X GET http://127.0.0.1:34151/payments/getAllOrders
    ```
    
    * http request to process `payment-service` for <`OrderId`> . 
       
         * Low CPU test, execute the below curl for values from 1 to 10.
           ```shell
           curl -X GET http://localhost:8081/payments/processPayment/3
            ```
         * For High CPU test : don't go above `processPayment/45`
           ```shell
              curl -X GET http://localhost:8081/payments/processPayment/39
            ```
### To run Gatling tests
1. To execute  OrderService Load tests :
   ```shell 
    cd order-service
   ```
   ```shell 
     mvn gatling:test -pl order-service -Dgatling.simulationClass=ews.ondemand.services.orderservice.OrderServiceSimulation
   ```
   
   ```shell 
    mvn gatling:test -pl payment-service -Dgatling.simulationClass=ews.ondemand.services.paymentservice.PaymentServiceSimulation    
