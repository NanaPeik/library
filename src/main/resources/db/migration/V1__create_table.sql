create table book
(
    id   serial not null
        constraint status_pk
            primary key,
    name varchar,
    author varchar,
    published_date varchar
);