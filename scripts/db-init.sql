-- this script is just a helper for database initialization

CREATE USER db_user WITH ENCRYPTED PASSWORD 'db_password';

CREATE DATABASE household_inventory OWNER db_user;

GRANT ALL PRIVILEGES ON DATABASE household_inventory TO db_user;

\connect household_inventory

GRANT USAGE, CREATE ON SCHEMA public TO db_user;
ALTER SCHEMA public OWNER TO db_user;

ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO db_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON SEQUENCES TO db_user;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON FUNCTIONS TO db_user;