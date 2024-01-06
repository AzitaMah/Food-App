-- Tables
CREATE TABLE IF NOT EXISTS food
(
    id   INTEGER      NOT NULL,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE person
(
    id         BIGINT       NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    birth_date DATE         NOT NULL,
    contact    VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    username   VARCHAR(255) NOT NULL UNIQUE,
    food_id    INTEGER,
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
    id BIGINT PRIMARY KEY,
    person_id      BIGINT REFERENCES person (id),
    partner_id      BIGINT REFERENCES person (id)
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
-- DATA --
--- INSERT food ---
INSERT INTO food (id, name)
VALUES (0, 'Pizza');
INSERT INTO food (id, name)
VALUES (1, 'Pasta');
INSERT INTO food (id, name)
VALUES (2, 'Hot Pot');
INSERT INTO food (id, name)
VALUES (3, 'Salad');
INSERT INTO food (id, name)
VALUES (4, 'Burger');
INSERT INTO food (id, name)
VALUES (5, 'Sushi');
INSERT INTO food (id, name)
VALUES (6, 'Ramen');
INSERT INTO food (id, name)
VALUES (7, 'Tacos');
INSERT INTO food (id, name)
VALUES (8, 'Fries');
INSERT INTO food (id, name)
VALUES (9, 'Curry');
INSERT INTO food (id, name)
VALUES (10, 'Lasagne');
INSERT INTO food (id, name)
VALUES (11, 'Steak');
INSERT INTO food (id, name)
VALUES (12, 'Dumplings');
INSERT INTO food (id, name)
VALUES (13, 'Falafel Wrap');
INSERT INTO food (id, name)
VALUES (14, 'Fish and Chips');
INSERT INTO food (id, name)
VALUES (15, 'Pho');
INSERT INTO food (id, name)
VALUES (16, 'Tiramisu');
INSERT INTO food (id, name)
VALUES (17, 'Chicken Wings');
INSERT INTO food (id, name)
VALUES (18, 'Caesar Salad');
INSERT INTO food (id, name)
VALUES (19, 'Hamburger');
INSERT INTO food (id, name)
VALUES (20, 'Pad Thai');

-- roles --
INSERT INTO roles (id, name)
VALUES (1, 'ADMIN');
INSERT INTO roles (id, name)
VALUES (2, 'USER');

-- person
INSERT INTO person (id, first_name, last_name, birth_date, contact, password, username, food_id)
VALUES (1, 'admin', 'admin', '2000-01-01', '+12333333', '$2a$10$aQx5FdSnIX9dtA.v7T6Z1OTXHA3eRSZ8x959pS.6g7RcXgBX5o4.S',
        'admin', null);
INSERT INTO person (id, first_name, last_name, birth_date, contact, password, username, food_id)
VALUES (2, 'Alice', 'Smith', '1985-05-15', '+9876543210',
        '$2a$10$hq8goECfABE4Nsa7ENgd1uctWLTcdnSHvajTBaXkfXwkziFpUYPnW', 'alice.smith', null);
INSERT INTO person (id, first_name, last_name, birth_date, contact, password, username, food_id)
VALUES (3, 'Michael', 'Johnson', '1982-09-22', '+1122334455',
        '$2a$10$Su8Ul4VPqXSNA/0JuYoD1eLFaU12WoyYe099TgOCK6Ua14Eg65ZAi', 'michael.j', 1);
INSERT INTO person (id, first_name, last_name, birth_date, contact, password, username, food_id)
VALUES (4, 'Emily', 'Williams', '1995-07-08', '+9876543210',
        '$2a$10$CmrObK9KoeycfgeB5tL.wez74E0HS4ZJ4Hk6malwK9CYMYh2/b41u', 'emily.w', 1);
INSERT INTO person (id, first_name, last_name, birth_date, contact, password, username, food_id)
VALUES (5, 'John', 'Doe', '1990-01-01', '+1234567890', '$2a$10$h1nVs7DnnFW0u9S3x6aC1OUetWZCW99eW9ZCmKzUhY6FkJiSjYS6e',
        'john.doe', 1);
INSERT INTO person (id, first_name, last_name, birth_date, contact, password, username, food_id)
VALUES (6, 'Liam', 'Taylor', '1999-11-08', '+1888777666',
        '$2a$10$FW.HdfX5W.QUzY4txk4qPOlrg4.n5LdJZ69TsV8NdpHwVK/njtDVy', 'liamt', 1);
INSERT INTO person (id, first_name, last_name, birth_date, contact, password, username, food_id)
VALUES (7, 'Noah', 'Clark', '1997-05-12', '+1777666555', '$2a$10$/ZOE2DJzgbnzgemEfrojA./cRjYFRdr3thD1uDQesctkoROXK8nmi',
        'noahc', 2);
INSERT INTO person (id, first_name, last_name, birth_date, contact, password, username, food_id)
VALUES (8, 'Olivia', 'Martin', '2001-02-28', '+1999888777',
        '$2a$10$BMjtVy7fgn8e0bI4ywls5.AOI89zQXIMD9MvgbmAco8pl/rXatVny', 'oliviam_22', 2);
INSERT INTO person (id, first_name, last_name, birth_date, contact, password, username, food_id)
VALUES (9, 'Sophia', 'Miller', '1996-08-17', '+1666777888',
        '$2a$10$cv0tfrfAOtjUHGDGoG1ZQOhmm.1itcBfJci4ZUYo8zYlx1r3tIAqy', 'sophiam', 2);
INSERT INTO person (id, first_name, last_name, birth_date, contact, password, username, food_id)
VALUES (10, 'Ava', 'Baker', '1997-12-12', '+1999333444', '$2a$10$7s9E1Acwx0tF4.eZsV8gx.c6RwThj0ShOizk/nRnZU/c1KsfeEGW.',
        'avab', 2);
INSERT INTO person (id, first_name, last_name, birth_date, contact, password, username, food_id)
VALUES (11, 'Lucas', 'Fisher', '1996-02-10', '+1555666777',
        '$2a$10$tM4Wmn/57ejQzwPtbfGYyOYBjQSTqCmw17sydmlIRBU7cmPQ1lS1O', 'lucasf', 2);

-- user_roles
INSERT INTO user_roles (role_id, user_id)
VALUES (2, 5);
INSERT INTO user_roles (role_id, user_id)
VALUES (2, 2);
INSERT INTO user_roles (role_id, user_id)
VALUES (2, 3);
INSERT INTO user_roles (role_id, user_id)
VALUES (2, 4);
INSERT INTO user_roles (role_id, user_id)
VALUES (1, 1);
INSERT INTO user_roles (role_id, user_id)
VALUES (2, 6);
INSERT INTO user_roles (role_id, user_id)
VALUES (2, 7);
INSERT INTO user_roles (role_id, user_id)
VALUES (2, 8);
INSERT INTO user_roles (role_id, user_id)
VALUES (2, 9);
INSERT INTO user_roles (role_id, user_id)
VALUES (1, 10);
INSERT INTO user_roles (role_id, user_id)
VALUES (1, 11);

INSERT INTO match (id, person_id, partner_id) VALUES (1, 2, 6);
INSERT INTO match (id, person_id, partner_id) VALUES (2, 2, 5);
INSERT INTO match (id, person_id, partner_id) VALUES (3, 2, 6);
INSERT INTO match (id, person_id, partner_id) VALUES (4, 2, 7);
INSERT INTO match (id, person_id, partner_id) VALUES (5, 2, 10);
INSERT INTO match (id, person_id, partner_id) VALUES (6, 7, 2);
INSERT INTO match (id, person_id, partner_id) VALUES (7, 10, 2);


