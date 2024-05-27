drop sequence if exists movie_id_seq;
create sequence movie_id_seq
    increment 1
    minvalue 1
    maxvalue 9223372036854775807
    start 1
    cache 1;

create table if not exists movie
(
    "id"              int8    not null default nextval('movie_id_seq'::regclass),
    "year"            varchar not null,
    "category"        varchar not null,
    "nominee"         varchar not null,
    "additional_info" varchar not null,
    "won"             varchar not null,

    primary key (id)
);

create table if not exists movie_rating
(
    "movie_id" int8 not null,
    "rating"   int8,

    primary key (movie_id),
    foreign key (movie_id) references movie (id)
);