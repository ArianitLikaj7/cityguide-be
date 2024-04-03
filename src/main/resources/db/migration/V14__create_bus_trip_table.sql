CREATE TABLE bustrips(
                         id BIGSERIAL PRIMARY KEY,
                         state_id INTEGER,
                         number_of_days VARCHAR,
                         name_of_bus VARCHAR,
                         price DECIMAL,
                         FOREIGN KEY (state_id) REFERENCES states(id)
);
