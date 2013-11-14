CREATE TABLE web_users (
userid    INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
username  VARCHAR(15) NOT NULL UNIQUE,
firstname VARCHAR(15) NOT NULL,
lastname  VARCHAR(15) NOT NULL,
age       INTEGER NOT NULL,
gender    ENUM('female', 'male') NOT NULL);

INSERT INTO web_users VALUES
(1, 'cworth', 'Cathy', 'Worth', 58, 'female'),
(2, 'azayne', 'Alice', 'Zayne', 14, 'female'),
(3, 'dverne', 'David', 'Verne', 27, 'male'),
(4, 'byoung', 'Billy', 'Young', 42, 'male'),
(5, 'euriel', 'Emily', 'Uriel', 39, 'female');
