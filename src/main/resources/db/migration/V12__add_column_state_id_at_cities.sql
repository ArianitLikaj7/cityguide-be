ALTER TABLE cities
ADD COLUMN state_id INTEGER;

ALTER TABLE cities
ADD CONSTRAINT fk_state_id
FOREIGN KEY (state_id)
REFERENCES states(id);
