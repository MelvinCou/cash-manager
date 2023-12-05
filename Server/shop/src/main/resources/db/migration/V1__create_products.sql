CREATE TABLE products
(
    id          UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    price       INTEGER      NOT NULL,
    product_url VARCHAR(255) NOT NULL,
    stock       INTEGER      NOT NULL,
    CONSTRAINT pk_products PRIMARY KEY (id)
);