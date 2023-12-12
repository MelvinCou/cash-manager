ALTER TABLE ordered_orders
    ADD product_id UUID;

ALTER TABLE ordered_orders
    ALTER COLUMN product_id SET NOT NULL;

ALTER TABLE ordered_orders
    ADD CONSTRAINT FK_ORDERED_ORDERS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (product_id);