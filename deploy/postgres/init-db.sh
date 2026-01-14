#!/usr/bin/env bash
set -euo pipefail

echo "Postgres init script started"

# Validate required environment variables
: "${KC_DB_NAME:?Environment variable KC_DB_NAME is required}"
: "${POSTGRES_DB:?Environment variable POSTGRES_DB is required}"
: "${POSTGRES_USER:?Environment variable POSTGRES_USER is required}"

echo "Target database: ${KC_DB_NAME}"
echo "Postgres user: ${POSTGRES_USER}"
echo "Postgres database: ${POSTGRES_DB}"

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
    SELECT 'CREATE DATABASE $KC_DB_NAME OWNER $POSTGRES_USER'
    WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = '$KC_DB_NAME')\gexec
EOSQL

echo "KC_Database $KC_DB_NAME initialized successfully"
