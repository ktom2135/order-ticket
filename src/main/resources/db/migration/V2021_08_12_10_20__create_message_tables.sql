CREATE TABLE IF NOT EXISTS message
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    message_type VARCHAR(255),
    content VARCHAR(255),
    send_status VARCHAR(255)
);