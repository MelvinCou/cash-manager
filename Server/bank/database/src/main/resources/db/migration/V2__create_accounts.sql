CREATE TABLE accounts
(
    id           UUID                        NOT NULL,
    user_id      UUID                        NOT NULL,
    opening_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    state        SMALLINT                    NOT NULL,
    balance      DECIMAL(19, 4)              NOT NULL,
    CONSTRAINT pk_accounts PRIMARY KEY (id)
);

ALTER TABLE accounts
    ADD CONSTRAINT FK_ACCOUNTS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);
