DROP TABLE IF EXISTS penalty;
DROP TABLE IF EXISTS car;

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

