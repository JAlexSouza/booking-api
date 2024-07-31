CREATE TABLE IF NOT EXISTS CLIENT (
   ID BIGINT PRIMARY KEY NOT NULL,
   FIRST_NAME VARCHAR NOT NULL,
   LAST_NAME VARCHAR NOT NULL,
   EMAIL VARCHAR NOT NULL UNIQUE
);

CREATE SEQUENCE IF NOT EXISTS SEQ_CLIENT
INCREMENT 1
START 1
OWNED BY CLIENT.ID;

CREATE INDEX ON CLIENT (EMAIL);