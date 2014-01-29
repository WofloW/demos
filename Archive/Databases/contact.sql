CREATE TABLE contact_users (
userid   INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
first    VARCHAR(20) NOT NULL,
last     VARCHAR(20) NOT NULL,
position VARCHAR(20)
);

CREATE TABLE contact_phones (
phoneid INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
userid  INTEGER NOT NULL,
area    CHAR(3) DEFAULT '415',
phone   CHAR(8) NOT NULL,
FOREIGN KEY (userid)
REFERENCES  contact_users(userid)
);

CREATE TABLE contact_emails (
emailid INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
userid  INTEGER NOT NULL,
email   VARCHAR(256),
FOREIGN KEY (userid)
REFERENCES  contact_users(userid)
);

CREATE TABLE contact_websites (
webid   INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
userid  INTEGER NOT NULL,
website VARCHAR(256),
FOREIGN KEY (userid)
REFERENCES  contact_users(userid)
);

INSERT INTO contact_users
(first, last, position) VALUES
('Greg', 'Benson', 'Professor'),
('Chris', 'Brooks', 'Associate Professor'),
('Jeff', 'Buckwalter', 'Associate Professor'),
('Sophie', 'Engle', 'Assistant Professor'),
('Patricia', 'Francis-Lyon', 'Assistant Professor'),
('David', 'Galles', 'Associate Professor'),
('EJ', 'Jung', 'Assistant Professor'),
('Peter', 'Pacheco', 'Professor'),
('Terence', 'Parr', 'Associate Professor'),
('Sami', 'Rollins', 'Associate Professor'),
('David', 'Wolber', 'Professor');

INSERT INTO contact_phones
(userid, phone) VALUES
(1, '422-5066'),
(2, '422-5221'),
(3, '422-6176'),
(4, '422-4174'),
(5, '422-2810'),
(6, '422-5951'),
(7, '422-5422'),
(8, '422-6630'),
(9, '422-5707'),
(10, '422-2024'),
(11, '422-6451');

INSERT INTO contact_emails
(userid, email) VALUES
(1, 'benson@usfca.edu'),
(2, 'cbrooks@usfca.edu'),
(2, 'cbrooks@cs.usfca.edu'),
(3, 'buckwalter@usfca.edu'),
(4, 'sjengle@usfca.edu'),
(4, 'sjengle@cs.usfca.edu'),
(5, 'pafrancislyon@usfca.edu'),
(5, 'pfrancislyon@cs.usfca.edu'),
(6, 'galles@usfca.edu'),
(7, 'ejung2@usfca.edu'),
(7, 'ejung@cs.usfca.edu'),
(8, 'peter@usfca.edu'),
(9, 'tjparr@usfca.edu'),
(9, 'parrt@cs.usfca.edu'),
(10, 'snrollins@usfca.edu'),
(10, 'srollins@cs.usfca.edu'),
(11, 'wolberd@usfca.edu');

INSERT INTO contact_websites
(userid, website) VALUES
(1, 'http://benson.cs.usfca.edu/'),
(2, 'http://www.cs.usfca.edu/~brooks/'),
(4, 'http://www.cs.usfca.edu/~sjengle/'),
(6, 'http://www.cs.usfca.edu/~galles/'),
(7, 'http://www.cs.usfca.edu/~ejung/'),
(8, 'http://www.cs.usfca.edu/~peter/'),
(9, 'http://www.cs.usfca.edu/~parrt/'),
(10, 'http://www.cs.usfca.edu/~srollins/'),
(11, 'http://sites.google.com/site/wolberd/');
