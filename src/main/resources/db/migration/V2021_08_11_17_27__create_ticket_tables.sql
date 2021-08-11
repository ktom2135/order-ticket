CREATE TABLE IF NOT EXISTS ticket
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP,
    status VARCHAR(255),
    meter_no VARCHAR(255)
);
