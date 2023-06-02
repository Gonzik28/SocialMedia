CREATE TABLE IF NOT EXISTS friend_request (
    id SERIAL PRIMARY KEY,
    sender_user_name VARCHAR(100),
    receiver_user_name VARCHAR(100),
    friend BOOLEAN
);