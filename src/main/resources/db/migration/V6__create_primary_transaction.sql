CREATE TABLE primary_transaction
(
    id                 BIGINT         NOT NULL AUTO_INCREMENT,
    amount             DOUBLE         NOT NULL,
    available_balance  DECIMAL(19, 2) DEFAULT NULL,
    date               DATETIME       DEFAULT NULL,
    description        VARCHAR(255)   DEFAULT NULL,
    status             VARCHAR(255)   DEFAULT NULL,
    type               VARCHAR(255)   DEFAULT NULL,
    primary_account_id BIGINT         DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_primary_transaction_account FOREIGN KEY (primary_account_id) REFERENCES primary_account (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
