CREATE TABLE ordered_orders
(
    id         UUID    NOT NULL,
    quantity   INTEGER NOT NULL,
    product_id UUID    NOT NULL,
    order_id   UUID    NOT NULL,
    CONSTRAINT pk_ordered_orders PRIMARY KEY (id)
);

ALTER TABLE ordered_orders
    ADD CONSTRAINT FK_ORDERED_ORDERS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE ordered_orders
    ADD CONSTRAINT FK_ORDERED_ORDERS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (id);