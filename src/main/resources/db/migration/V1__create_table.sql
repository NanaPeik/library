drop schema if exists book cascade;
create schema if not exists book;
create table book.book
(
    id   serial not null
        constraint status_pk
            primary key,
    name varchar,
    author varchar,
    published_date timestamp,
    genre varchar,
    quantity integer
);


drop schema if exists users cascade;
create schema if not exists users;
create table users.users
(
    id   serial not null
        constraint status_pk
            primary key,
    user_name varchar,
    password varchar,
    email varchar
);