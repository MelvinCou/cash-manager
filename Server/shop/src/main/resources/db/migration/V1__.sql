CREATE TABLE ordered_orders
(
    ordered_orders_id UUID NOT NULL,
    quantity          INTEGER,
    product_id        UUID NOT NULL,
    order_id          UUID NOT NULL,
    CONSTRAINT pk_ordered_orders PRIMARY KEY (ordered_orders_id)
);

CREATE TABLE orders
(
    order_id UUID NOT NULL,
    status   VARCHAR(255),
    date     TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_orders PRIMARY KEY (order_id)
);

CREATE TABLE products
(
    product_id  UUID NOT NULL,
    name        VARCHAR(255),
    price       INTEGER,
    product_url VARCHAR(255),
    stock       INTEGER,
    CONSTRAINT pk_products PRIMARY KEY (product_id)
);

ALTER TABLE ordered_orders
    ADD CONSTRAINT FK_ORDERED_ORDERS_ON_ORDER FOREIGN KEY (order_id) REFERENCES orders (order_id);

ALTER TABLE ordered_orders
    ADD CONSTRAINT FK_ORDERED_ORDERS_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES products (product_id);