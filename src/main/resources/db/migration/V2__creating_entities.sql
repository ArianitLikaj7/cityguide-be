CREATE TABLE cities (
                        cityId SERIAL PRIMARY KEY,
                        name_of_city VARCHAR(255) NOT NULL,
                        cultural_heritage VARCHAR(255) NOT NULL,
                        city_prefix VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE gastronomies (
                              gastronome_id SERIAL PRIMARY KEY,
                              name_of_Gastronome VARCHAR(255) NOT NULL,
                              schedule VARCHAR(255),
                              longitude DOUBLE PRECISION NOT NULL,
                              latitude DOUBLE PRECISION NOT NULL,
                              sponsored BOOLEAN,
                              type_of_gastronome VARCHAR(255) CHECK (type_of_gastronome IN ('CAFFE', 'RESTAURANT', 'HOTEL')) NOT NULL,
                              city_id INTEGER,
                              FOREIGN KEY (city_id) REFERENCES cities(cityId)
);

CREATE TABLE gastronome_attachments (
                                        gastronome_id INTEGER,
                                        attachment VARCHAR(255),
                                        FOREIGN KEY (gastronome_id) REFERENCES gastronomies(gastronome_id)
);

CREATE TABLE reservations (
                              reservation_id SERIAL PRIMARY KEY,
                              gastronome_id INTEGER,
                              reservationDate VARCHAR(255),
                              number_of_people INTEGER,
                              special_requests VARCHAR(255),
                              phone_number VARCHAR(255) NOT NULL,
                              status VARCHAR(255),
                              FOREIGN KEY (gastronome_id) REFERENCES gastronomies(gastronome_id)
);
CREATE TABLE trips (
                       id SERIAL PRIMARY KEY,
                       num_of_days INT
);

CREATE TABLE trip_cities (
                             trip_id INTEGER,
                             city_id BIGINT,
                             FOREIGN KEY (trip_id) REFERENCES trips(id)
);

CREATE TABLE trip_gastronomes (
                                  trip_id INTEGER,
                                  type_of_gastronome VARCHAR(255),
                                  FOREIGN KEY (trip_id) REFERENCES trips(id)
);


