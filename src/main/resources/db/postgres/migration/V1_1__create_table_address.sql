CREATE TABLE IF NOT EXISTS ADDRESS (
   ID BIGINT PRIMARY KEY NOT NULL,
   STATE VARCHAR  NOT NULL,
   CITY VARCHAR NOT NULL,
   STREET VARCHAR NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS SEQ_ADDRESS
INCREMENT 1
START 1
OWNED BY ADDRESS.ID;

CREATE INDEX ON ADDRESS (STATE);
CREATE INDEX ON ADDRESS (CITY);

ALTER TABLE ADDRESS
ADD CONSTRAINT ABBREVIATION_STATE
CHECK (STATE IN (
    'AC',
    'AL',
    'AP',
    'AM',
    'BA',
    'CE',
    'DF',
    'ES',
    'GO',
    'MA',
    'MS',
    'MT',
    'MG',
    'PA',
    'PB',
    'PR',
    'PE',
    'PI',
    'RJ',
    'RN',
    'RS',
    'RO',
    'RR',
    'SC',
    'SP',
    'SE',
    'TO'
));