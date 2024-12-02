CREATE TABLE users (
                       id int8 PRIMARY KEY,
                       username VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_roles (
                            id int8 PRIMARY KEY,
                            user_id INT NOT NULL,
                            role VARCHAR(50) NOT NULL,
                            CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE INDEX idx_username ON users (username);
CREATE INDEX idx_user_roles_user_id ON user_roles (user_id);