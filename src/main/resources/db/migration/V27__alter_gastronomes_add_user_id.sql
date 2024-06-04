ALTER TABLE gastronomies
    ADD COLUMN user_id BIGINT,
ADD CONSTRAINT fk_user_id
FOREIGN KEY (user_id) REFERENCES users(id);