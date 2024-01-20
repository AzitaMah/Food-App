-- Tables
CREATE TABLE IF NOT EXISTS food
(
    id           INTEGER      NOT NULL,
    name         VARCHAR(255) NOT NULL,
    image_base64 VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE person
(
    id            BIGINT       NOT NULL,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    birth_date    DATE         NOT NULL,
    contact       VARCHAR(255) NOT NULL,
    password      VARCHAR(255) NOT NULL,
    username      VARCHAR(255) NOT NULL UNIQUE,
    food_id       INTEGER,
    profile_image VARCHAR(255),
    PRIMARY KEY (id),
    CONSTRAINT fk_food FOREIGN KEY (food_id) REFERENCES food
);

CREATE TABLE roles
(
    id   SERIAL NOT NULL,
    name VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE user_roles
(
    role_id INTEGER NOT NULL,
    user_id BIGINT  NOT NULL,
    CONSTRAINT fk_roles FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_person FOREIGN KEY (user_id) REFERENCES person (id)
);

CREATE TABLE match
(
    id         BIGINT PRIMARY KEY,
    person_id  BIGINT REFERENCES person (id),
    partner_id BIGINT REFERENCES person (id)
);

-- Sequences
CREATE SEQUENCE IF NOT EXISTS candidate_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE;

CREATE SEQUENCE IF NOT EXISTS food_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE;

CREATE SEQUENCE IF NOT EXISTS person_seq
    START WITH 12
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;

CREATE SEQUENCE IF NOT EXISTS match_seq
    START WITH 8
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE;
