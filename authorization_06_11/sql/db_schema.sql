DROP TABLE IF EXISTS penalty;
DROP TABLE IF EXISTS car;
DROP TABLE IF EXISTS user_data;

CREATE TABLE car
(
    id    SERIAL PRIMARY KEY,
    title VARCHAR(255)
);

CREATE TABLE penalty
(
    id      SERIAL PRIMARY KEY,
    car_id  INTEGER NOT NULL REFERENCES car (id),
    amount  INTEGER,
    daytime DATE
);

CREATE TABLE user_data
(
    id            SERIAL PRIMARY KEY,
    email         VARCHAR,
    hash_password VARCHAR,
    nickname      VARCHAR,
    token         VARCHAR,
    token_usage   INTEGER
);

