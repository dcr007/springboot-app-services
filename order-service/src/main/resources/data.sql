INSERT INTO orders.orders(id, customer_id, order_date, total_amount)
VALUES (1, 1, '2023-11-25T23:33:12.130+02:00', 10)
ON CONFLICT (id) DO NOTHING;

INSERT INTO orders.orders(id, customer_id, order_date, total_amount)
VALUES(2, 2, '2023-11-25T23:33:12.130+02:00', 30)
ON CONFLICT (id) DO NOTHING;