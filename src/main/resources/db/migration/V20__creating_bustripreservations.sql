CREATE TABLE bustripreservations(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    lastname VARCHAR,
    telephone INTEGER,
    username VARCHAR,
    bustrip_id INTEGER,
    FOREIGN KEY (bustrip_id) REFERENCES bustrips(id)
);