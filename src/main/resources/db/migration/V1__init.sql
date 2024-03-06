CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                       first_name VARCHAR(255),
                       last_name VARCHAR(255),
                       username VARCHAR(255),
                       password VARCHAR(255),
                       role VARCHAR(255)
);
