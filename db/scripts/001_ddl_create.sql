CREATE TABLE IF NOT EXISTS accident_type
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS rule
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS accident
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(2000),
    text VARCHAR(2000),
    address VARCHAR(50),
    accident_type_id INT REFERENCES accident_type(id)
);
CREATE TABLE IF NOT EXISTS accident_rule
(
    accident_id INT REFERENCES accident(id),
    rule_id INT REFERENCES rule(id)
)