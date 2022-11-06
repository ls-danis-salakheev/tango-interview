create table accounts
(
    id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(32) NOT NULL UNIQUE -- and etc
);

CREATE TABLE regions
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(256) NOT NULL UNIQUE -- and etc
);

create table viewer_stats
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    created    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    account_id BIGINT,
    region_id  BIGINT,
    FOREIGN KEY (account_id) REFERENCES accounts,
    FOREIGN KEY (region_id) REFERENCES regions
);


