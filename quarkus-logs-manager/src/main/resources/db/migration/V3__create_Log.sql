CREATE TABLE log (
                       id int8 PRIMARY KEY,
                       timestamp VARCHAR(50) NOT NULL UNIQUE,
                       log_level VARCHAR(255) NOT NULL,
                       service_name VARCHAR(255) NOT NULL,
                       message VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX idx_timestamp ON log (timestamp);
CREATE INDEX idx_log_level ON log (log_level);
CREATE INDEX idx_service_name ON log (service_name);