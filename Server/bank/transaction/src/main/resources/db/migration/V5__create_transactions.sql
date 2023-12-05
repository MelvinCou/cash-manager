CREATE TABLE transactions
(
    id                UUID                        NOT NULL,
    payment_method_id UUID                        NOT NULL,
    status            SMALLINT                    NOT NULL,
    amount            DECIMAL(19, 2)              NOT NULL,
    date              TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    receiver          VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_transactions PRIMARY KEY (id)
);

ALTER TABLE transactions
    ADD CONSTRAINT FK_TRANSACTIONS_ON_PAYMENT_METHOD FOREIGN KEY (payment_method_id) REFERENCES payment_methods (id);