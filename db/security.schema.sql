CREATE TABLE authorities
(
    id        SERIAL PRIMARY KEY,
    authority VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users(
    id SERIAL PRIMARY KEY ,
    username VARCHAR(50) NOT NULL UNIQUE ,
    password VARCHAR(100) NOT NULL ,
    enabled BOOLEAN DEFAULT TRUE,
    authority_id INT NOT NULL REFERENCES authorities(id)
);

INSERT INTO authorities(authority) values ('ROLE_USER');
INSERT INTO authorities(authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$eH2cTvNTi26pYxCFq6fpMOQ69clohtaViAJ9OFF4.8gmYPXxzVriK',
        (select id from authorities where authority = 'ROLE_ADMIN'));

drop table authorities;
drop table users cascade ;