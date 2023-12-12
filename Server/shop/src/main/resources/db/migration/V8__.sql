ALTER TABLE ordered_orders
    ADD order_id UUID;

ALTER TABLE ordered_orders
    ALTER COLUMN order_id SET NOT NULL;

ALTER TABLE ordered_orders
    ADD CONSTRAINT FK_ORDERED_ORDERS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (order_id);