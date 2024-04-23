ALTER TABLE cities
DROP COLUMN taxi_phone_number;

ALTER TABLE cities
    ADD COLUMN taxi_phone_numbers VARCHAR[];