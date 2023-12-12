ALTER TABLE orders
    ADD order_id UUID;

ALTER TABLE products
    ADD product_id UUID;

DROP TABLE ordered_orders CASCADE;

ALTER TABLE orders
DROP
COLUMN id;

ALTER TABLE products
DROP
COLUMN id;

ALTER TABLE orders
    ADD CONSTRAINT pk_orders PRIMARY KEY (order_id);

ALTER TABLE products
    ADD CONSTRAINT pk_products PRIMARY KEY (product_id);