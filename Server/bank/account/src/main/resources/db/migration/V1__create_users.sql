CREATE TABLE users
(
    id       UUID         NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     SMALLINT     NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);
