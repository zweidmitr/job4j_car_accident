CREATE TABLE IF NOT EXISTS accident_type
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(256) UNIQUE
);

CREATE TABLE IF NOT EXISTS rule
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(256) UNIQUE
);

CREATE TABLE IF NOT EXISTS accident
(
    id      SERIAL PRIMARY KEY,
    name    varchar(256),
    text    varchar(1024),
    address varchar(1024),
    type_id INT REFERENCES accident_type (id)
);

CREATE TABLE IF NOT EXISTS accident_rule
(
    id          SERIAL PRIMARY KEY,
    accident_id INT NOT NULL REFERENCES accident (id),
    rule_id     INT NOT NULL REFERENCES rule (id)
);

drop table accident_type cascade;
drop table rule cascade;
drop table accident cascade;

INSERT INTO accident_type (name)
values ('Две машины'),
       ('Машина и человек'),
       ('Машина и самокат');
INSERT INTO rule (name)
values ('Статья. 1'),
       ('Статья. 2'),
       ('Статья. 3');
