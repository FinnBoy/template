CREATE SEQUENCE seq;
CREATE TABLE star (id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
   firstname VARCHAR(20),
   lastname VARCHAR(20))
;
CREATE TABLE movies (starid INTEGER, movieid INTEGER PRIMARY KEY, title VARCHAR(40))
;