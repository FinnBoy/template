
INSERT INTO star (id, firstname, lastname) VALUES (DEFAULT, 'Felix', 'the Cat');

INSERT INTO movies (starid, movieid, title) VALUES (CURRENT VALUE FOR seq, 10, 'Felix in Hollywood');