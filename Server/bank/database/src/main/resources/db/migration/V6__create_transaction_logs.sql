CREATE TABLE transaction_logs
(
    id             UUID                        NOT NULL,
    transaction_id UUID                        NOT NULL,
    date           TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    severity       SMALLINT                    NOT NULL,
    message        VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_transaction_logs PRIMARY KEY (id)
);

ALTER TABLE transaction_logs
    ADD CONSTRAINT FK_TRANSACTION_LOGS_ON_TRANSACTION FOREIGN KEY (transaction_id) REFERENCES transactions (id);