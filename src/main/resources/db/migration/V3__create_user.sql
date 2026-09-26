CREATE TABLE `user`
(
    user_id            BIGINT       NOT NULL AUTO_INCREMENT,
    email              VARCHAR(255) NOT NULL,
    enabled            BIT(1)       NOT NULL,
    first_name         VARCHAR(255) DEFAULT NULL,
    last_name          VARCHAR(255) DEFAULT NULL,
    password           VARCHAR(255) DEFAULT NULL,
    phone              VARCHAR(255) DEFAULT NULL,
    username           VARCHAR(255) DEFAULT NULL,
    primary_account_id BIGINT       DEFAULT NULL,
    savings_account_id BIGINT       DEFAULT NULL,
    PRIMARY KEY (user_id),
    UNIQUE KEY uk_user_email (email),
    CONSTRAINT fk_user_primary_account FOREIGN KEY (primary_account_id) REFERENCES primary_account (id),
    CONSTRAINT fk_user_savings_account FOREIGN KEY (savings_account_id) REFERENCES savings_account (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
