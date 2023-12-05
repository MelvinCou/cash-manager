CREATE TABLE payment_methods
(
    id                 UUID     NOT NULL,
    account_id         UUID     NOT NULL,
    type               SMALLINT NOT NULL,
    credit_card_number VARCHAR(19),
    cvc                VARCHAR(3),
    validity_date      TIMESTAMP WITHOUT TIME ZONE,
    check_number       INTEGER,
    CONSTRAINT pk_payment_methods PRIMARY KEY (id)
);

ALTER TABLE payment_methods
    ADD CONSTRAINT uc_payment_methods_credit_card_number UNIQUE (credit_card_number);

ALTER TABLE payment_methods
    ADD CONSTRAINT FK_PAYMENT_METHODS_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES accounts (id);