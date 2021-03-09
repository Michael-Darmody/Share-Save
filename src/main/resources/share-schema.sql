DROP TABLE IF EXISTS `share` CASCADE;
CREATE TABLE share (
    id BIGINT AUTO_INCREMENT,
    amount DOUBLE NOT NULL,
    name VARCHAR(255),
    price DOUBLE NOT NULL,
    PRIMARY KEY (id)
);