CREATE TABLE IF NOT EXISTS authentication(
    user_name VARCHAR(100) UNIQUE PRIMARY KEY,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS account(
    user_name VARCHAR(100) UNIQUE PRIMARY KEY,
    enabled BOOLEAN,
    expired_account BOOLEAN,
    expired_credentials BOOLEAN,
    locked_account BOOLEAN
);

CREATE TABLE IF NOT EXISTS user_rights (
    user_name VARCHAR(100),
    rights VARCHAR(255),
    PRIMARY KEY (user_name, rights)
);