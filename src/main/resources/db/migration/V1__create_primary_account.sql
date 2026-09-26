CREATE TABLE primary_account
(
    id              BIGINT         NOT NULL AUTO_INCREMENT,
    account_balance DECIMAL(19, 2) DEFAULT NULL,
    account_number  INT            NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
