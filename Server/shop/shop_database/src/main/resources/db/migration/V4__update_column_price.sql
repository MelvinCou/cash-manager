ALTER TABLE products
DROP
COLUMN price;

ALTER TABLE products
    ADD price DECIMAL NOT NULL;