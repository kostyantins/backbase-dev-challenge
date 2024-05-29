create sequence movie_id_seq
    increment 1
    minvalue 1
    maxvalue 9223372036854775807
    start 1
    cache 1;

create sequence rating_id_seq
    increment 1
    minvalue 1
    maxvalue 9223372036854775807
    start 1
    cache 1;

create table movie
(
    "id"              int8    not null default nextval('movie_id_seq'::regclass),
    "year"            varchar not null,
    "category"        varchar not null,
    "nominee"         varchar not null,
    "additional_info" varchar not null,
    "won"             varchar not null,

    primary key (id)
);

create table movie_rating
(
    "id"         int8    not null default nextval('rating_id_seq'::regclass),
    "movie_id"   int8    not null,
    "user_email" varchar not null,
    "rating"     int8    not null,

    primary key (id),
    foreign key (movie_id) references movie (id),
    constraint movie_rating_constraint unique (movie_id, user_email)
);