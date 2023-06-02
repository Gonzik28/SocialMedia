CREATE TABLE IF NOT EXISTS posts(
    id VARCHAR(100) UNIQUE PRIMARY KEY,
    header VARCHAR(100),
    post VARCHAR(500),
    postcard VARCHAR(100),
    user_name VARCHAR(100),
    FOREIGN KEY (user_name) REFERENCES account(user_name)
);