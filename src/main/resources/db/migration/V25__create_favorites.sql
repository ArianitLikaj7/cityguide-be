CREATE TABLE public.favorites (
             id SERIAL PRIMARY KEY,
             name_of_user VARCHAR(255) NOT NULL,
             gastronome_id BIGINT NOT NULL,
             created_at TIMESTAMP,
             updated_at TIMESTAMP,
             FOREIGN KEY (gastronome_id) REFERENCES gastronomies(id)
);
