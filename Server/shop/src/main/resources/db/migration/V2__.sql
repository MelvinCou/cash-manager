CREATE TABLE products
(
    id          UUID NOT NULL,
    name        VARCHAR(255),
    price       INTEGER,
    product_url VARCHAR(255),
    CONSTRAINT pk_products PRIMARY KEY (id)
);
