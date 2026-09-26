CREATE TABLE user_role
(
    user_role_id BIGINT NOT NULL AUTO_INCREMENT,
    role_id      INT    DEFAULT NULL,
    user_id      BIGINT DEFAULT NULL,
    PRIMARY KEY (user_role_id),
    CONSTRAINT fk_user_role_role FOREIGN KEY (role_id) REFERENCES role (role_id),
    CONSTRAINT fk_user_role_user FOREIGN KEY (user_id) REFERENCES `user` (user_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
