CREATE TABLE bustrips(
                         id SERIAL PRIMARY KEY ,
                         created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                         updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                         name_of_company VARCHAR,
                         start_station  VARCHAR,
                         destination VARCHAR,
                         start_time VARCHAR,
                         price DECIMAL,
                         description VARCHAR
);