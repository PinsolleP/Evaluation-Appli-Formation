CREATE TABLE Formation(
   id_formation INT,
   name VARCHAR(50),
   description VARCHAR(200),
   duration INT,
   type VARCHAR(50),
   price DECIMAL(5,2),
   PRIMARY KEY(id_formation)
);

CREATE TABLE Person(
   id_person INT,
   first_name VARCHAR(50),
   last_name VARCHAR(50),
   PRIMARY KEY(id_person)
);

CREATE TABLE User_(
   id_user INT,
   login VARCHAR(50),
   password VARCHAR(50),
   id_person INT NOT NULL,
   PRIMARY KEY(id_user),
   UNIQUE(id_person),
   FOREIGN KEY(id_person) REFERENCES Person(id_person)
);

CREATE TABLE Client(
   id_client INT,
   email VARCHAR(50),
   address VARCHAR(50),
   tel_number INT,
   id_person INT NOT NULL,
   PRIMARY KEY(id_client),
   UNIQUE(id_person),
   FOREIGN KEY(id_person) REFERENCES Person(id_person)
);

CREATE TABLE order_(
   id_order INT,
   quantity INT NOT NULL,
   date_ DATE,
   id_formation INT NOT NULL,
   id_client INT NOT NULL,
   id_user INT NOT NULL,
   PRIMARY KEY(id_order),
   FOREIGN KEY(id_formation) REFERENCES Formation(id_formation),
   FOREIGN KEY(id_client) REFERENCES Client(id_client),
   FOREIGN KEY(id_user) REFERENCES User_(id_user)
);


INSERT INTO person (id_person, first_name, last_name)
VALUES
(1, 'Didier', 'Lacaze'),
(2, 'Francoise', 'Poiret'),
(3, 'Tristan', 'Laclau'),
(4, 'Samuel', 'Curran'),
(5, 'Philippe', 'Rizzoli'),
(6, 'Pierre', 'Fabre'),
(7, 'Emmanuel', 'Chain'),
(8, 'Camille', 'Lelouche'),
(9, 'Pascal', 'obispo'),
(10, 'Christine', 'Bravo'),
(11, 'Boris', 'Vasseur'),
(12, 'Louise', 'Lamarque'),
(13, 'Ananda', 'Leer'),
(14, 'Sonia', 'Chevalier'),
(15, 'Anne', 'Icordi');

INSERT INTO client (id_client, email, address, tel_number, id_person)
VALUES
(1, 'didier.lacaze@free.com', '1 rue de la route 40250 Dax', 0685321478, 1),
(2, 'francoise.poiret@orange.fr', '12 rue de france 41456 Palmier', 0641183897, 2),
(3, 'tristan.laclau@gmail.com', '356 avenue de Hardy 40100 Azur', 0645853201, 3),
(4, 'samuel.chain@m6.fr', '12 rue des champs elysee 75000 Paris', 0663966396, 4),
(5, 'philippe.rizzoli@tf1.com', '24 rue du juste prix 64500 Anglet', 0614789632, 5),
(6, 'pierre.favre@co.fr', 'résidence castraise 84520 castres', 0602030405, 6),
(7, 'camille.lelouche@yahoo.com', '64 avenue du rire 31200 Toulouse', 0674757677, 8),
(8, 'emmanuel.chain@tele.com', '1 route du monosourcil 47200 Marmande', 0668646266, 7),
(9, 'pascal.obispo@micro.fr', '488 impasse du cheveu 56900 Troyes', 0681828384, 9),
(10, 'christine.bravo@ gmail.com', '12 impasse des bois 49250 Aix', 0633399852, 10),
(11, 'boris.vasseur@orange.com', '14 rue du chateau d''eau 40140 Soustons', 0655572200, 11),
(12, 'louise.lamarque@hotmail.com', 'chemin de saint Pierre 40230 Tyrosse', 0644771110, 12);

INSERT INTO user_ (id_user, login, password, id_person)
VALUES
(1, 'amanda55','fezaazuia25!', 13),
(1, 'soniache77', 'juihzagfuyzgu25!', 14),
(1, 'anniecord66', 'ezgfezaze66!', 15);

INSERT INTO formation (id_formation, name, description, duration, type, price)
VALUES
(1, 'Java', 'Java SE 8 : Syntaxe & Poo', 25, 'presentiel', 302.00),
(2, 'Java avancé', 'Exceptions, fichiers, Jdbc, thread...', 40, 'distanciel', 255.00),
(3, 'Spring', 'Spring Core/Mvc/Security', 18, 'presentiel', 180.00),
(4, 'Php frameworks', 'Symphony', 15, 'distanciel', 205.00),
(5, 'C#', 'DotNet Core', 28, 'presentiel', 175.00),
(6, 'Base de données', 'Requête, gestion comptes utilisateurs', 12, 'distanciel', 140.00),
(7, 'Algoritmie', 'logique, boucle...', 14, 'presentiel', 150.00),
(8, 'Gestion de projet', 'organisation, livrables et contrôle', 14, 'distanciel', 95.00),
(9, 'Python', 'Poo & Python avancé ', 8, 'presentiel', 125.00),
(10, 'Développeur Web', 'maquetter et réaliser interfaces utilisateur', 20, 'distanciel', 275.00);