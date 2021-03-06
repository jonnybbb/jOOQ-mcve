DROP SCHEMA IF EXISTS mcve CASCADE;

CREATE SCHEMA mcve;

CREATE TABLE mcve.test (
  id    SERIAL PRIMARY KEY,
  value INT
);

CREATE OR REPLACE FUNCTION mcve.foo(i integer) RETURNS integer AS $$
BEGIN
RETURN i + 1;
END;
$$ LANGUAGE plpgsql;