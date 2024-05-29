insert into movie_rating (movie_id, user_email, rating)
values
    ((select id from movie where category='Best Picture' and nominee='The Apartment'), 'user12@gmail.com', 2),
    ((select id from movie where category='Best Picture' and nominee='West Side Story'), 'admin01@gmail.com', 6),
    ((select id from movie where category='Best Picture' and nominee='Lawrence of Arabia'), 'user01@gmail.com', 4),
    ((select id from movie where category='Best Picture' and nominee='The Hurt Locker'), 'user02@gmail.com',10),
    ((select id from movie where category='Best Picture' and nominee='Slumdog Millionaire'),'user03@gmail.com', 20),
    ((select id from movie where category='Best Picture' and nominee='No Country for Old Men'), 'user04@gmail.com',30),
    ((select id from movie where category='Best Picture' and nominee='The Departed'), 'user05@gmail.com',40),
    ((select id from movie where category='Best Picture' and nominee='Million Dollar Baby'), 'user06@gmail.com',50),
    ((select id from movie where category='Best Picture' and nominee='Chicago'),'user07@gmail.com', 60),
    ((select id from movie where category='Best Picture' and nominee='A Beautiful Mind'),'user08@gmail.com', 70),
    ((select id from movie where category='Best Picture' and nominee='Gladiator'),'user09@gmail.com', 80),
    ((select id from movie where category='Best Picture' and nominee='American Beauty'), 'user10@gmail.com',90),
    ((select id from movie where category='Best Picture' and nominee='Titanic'), 'user11@gmail.com',100);
