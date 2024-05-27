insert into movie_rating (movie_id, rating)
values
    ((select id from movie where category='Best Picture' and nominee='The Apartment'), 2),
    ((select id from movie where category='Best Picture' and nominee='West Side Story'), 6),
    ((select id from movie where category='Best Picture' and nominee='Lawrence of Arabia'), 4),
    ((select id from movie where category='Best Picture' and nominee='The Hurt Locker'), 10),
    ((select id from movie where category='Best Picture' and nominee='Slumdog Millionaire'), 20),
    ((select id from movie where category='Best Picture' and nominee='No Country for Old Men'), 30),
    ((select id from movie where category='Best Picture' and nominee='The Departed'), 40),
    ((select id from movie where category='Best Picture' and nominee='Million Dollar Baby'), 50),
    ((select id from movie where category='Best Picture' and nominee='Chicago'), 60),
    ((select id from movie where category='Best Picture' and nominee='A Beautiful Mind'), 70),
    ((select id from movie where category='Best Picture' and nominee='Gladiator'), 80),
    ((select id from movie where category='Best Picture' and nominee='American Beauty'), 90),
    ((select id from movie where category='Best Picture' and nominee='Titanic'), 100);
