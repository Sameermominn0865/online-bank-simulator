CREATE TABLE savings_transaction
(
    id                 BIGINT         NOT NULL AUTO_INCREMENT,
    amount             DOUBLE         NOT NULL,
    available_balance  DECIMAL(19, 2) DEFAULT NULL,
    date               DATETIME       DEFAULT NULL,
    description        VARCHAR(255)   DEFAULT NULL,
    status             VARCHAR(255)   DEFAULT NULL,
    type               VARCHAR(255)   DEFAULT NULL,
    savings_account_id BIGINT         DEFAULT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_savings_transaction_account FOREIGN KEY (savings_account_id) REFERENCES savings_account (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
