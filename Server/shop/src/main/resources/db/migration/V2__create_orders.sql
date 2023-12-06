CREATE TABLE orders
(
    id     UUID         NOT NULL,
    status VARCHAR(255) NOT NULL,
    date   TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_orders PRIMARY KEY (id)
);
