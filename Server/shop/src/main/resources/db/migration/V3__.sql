CREATE TABLE orders
(
    id     UUID NOT NULL,
    status VARCHAR(255),
    date   TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);

ALTER TABLE products
    ADD stock INTEGER;