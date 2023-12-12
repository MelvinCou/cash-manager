CREATE TABLE ordered_orders
(
    ordered_orders_id UUID NOT NULL,
    quantity          INTEGER,
    CONSTRAINT pk_ordered_orders PRIMARY KEY (ordered_orders_id)
);