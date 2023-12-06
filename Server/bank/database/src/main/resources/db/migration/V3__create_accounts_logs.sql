CREATE TABLE accounts_logs
(
    id         UUID                        NOT NULL,
    account_id UUID                        NOT NULL,
    message    VARCHAR(255)                NOT NULL,
    date       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT pk_accounts_logs PRIMARY KEY (id)
);

ALTER TABLE accounts_logs
    ADD CONSTRAINT FK_ACCOUNTS_LOGS_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES accounts (id);
