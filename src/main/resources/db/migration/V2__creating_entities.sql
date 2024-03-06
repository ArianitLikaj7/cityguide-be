

CREATE TABLE cities (
                        id SERIAL PRIMARY KEY,
                        name_of_city VARCHAR(255) NOT NULL,
                        cultural_heritage VARCHAR(255) NOT NULL,
                        city_prefix VARCHAR(255) NOT NULL UNIQUE,
                        created_at TIMESTAMP,
                        updated_at TIMESTAMP
);

CREATE TABLE gastronomies (
                              id SERIAL PRIMARY KEY,
                              name_of_Gastronome VARCHAR(255) NOT NULL,
                              schedule VARCHAR(255),
                              longitude DOUBLE PRECISION NOT NULL,
                              latitude DOUBLE PRECISION NOT NULL,
                              sponsored BOOLEAN,
                              created_at TIMESTAMP,
                              updated_at TIMESTAMP,
                              type_of_gastronome VARCHAR(255) CHECK (type_of_gastronome IN ('CAFFE', 'RESTAURANT', 'HOTEL')) NOT NULL,
                              city_id INTEGER,
                              FOREIGN KEY (city_id) REFERENCES cities(id)
);

CREATE TABLE gastronome_attachments (
                                        gastronome_id INTEGER,
                                        attachment VARCHAR(255),
                                        created_at TIMESTAMP,
                                        updated_at TIMESTAMP,
                                        FOREIGN KEY (gastronome_id) REFERENCES gastronomies(id)
);

CREATE TABLE reservations (
                              id SERIAL PRIMARY KEY,
                              gastronome_id INTEGER,
                              reservation_date VARCHAR(255),
                              number_of_people INTEGER,
                              special_requests VARCHAR(255),
                              phone_number VARCHAR(255) NOT NULL,
                              status VARCHAR(255),
                              created_at TIMESTAMP,
                              updated_at TIMESTAMP,
                              FOREIGN KEY (gastronome_id) REFERENCES gastronomies(id)
);
CREATE TABLE trips (
                       id SERIAL PRIMARY KEY,
                       num_of_days INT,
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP
);

CREATE TABLE trip_cities (
                             trip_id INTEGER,
                             city_id BIGINT,
                             created_at TIMESTAMP,
                             updated_at TIMESTAMP,
                             FOREIGN KEY (trip_id) REFERENCES trips(id)
);

CREATE TABLE trip_gastronomes (
                                  trip_id INTEGER,
                                  type_of_gastronome VARCHAR(255),
                                  created_at TIMESTAMP,
                                  updated_at TIMESTAMP,
                                  FOREIGN KEY (trip_id) REFERENCES trips(id)
);

insert into users(id, created_at, first_name, last_name, username, password, role)
values (2,
        current_timestamp,
        'Arianit',
        'Likaj',
        'arianit',
        '$2a$10$Cz9xGfJtHLAPEZIHsHQat.udW9TdYwHKlqP9OZT9dSpiThlzGN2dS',
        'ADMIN');