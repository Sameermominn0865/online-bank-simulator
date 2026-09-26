CREATE TABLE role
(
    role_id INT          NOT NULL,
    name    VARCHAR(255) DEFAULT NULL,
    PRIMARY KEY (role_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO role (role_id, name)
VALUES (0, 'ROLE_USER'),
       (1, 'ROLE_ADMIN');
