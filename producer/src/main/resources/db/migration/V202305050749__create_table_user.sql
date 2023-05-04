CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE PEOPLE
(
    id UUID NOT NULL PRIMARY KEY default gen_random_uuid(),
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL
);

CREATE TABLE PEOPLE_OUTBOX
(
    id SERIAL PRIMARY KEY,
    person_id UUID NOT NULL,
    sent timestamp with time zone,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS PEOPLE_OUTBOX_SEQ;