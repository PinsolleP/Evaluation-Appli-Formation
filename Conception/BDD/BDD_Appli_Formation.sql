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
   id_client INT NOT NULL,
   id_user INT NOT NULL,
   PRIMARY KEY(id_order),
   FOREIGN KEY(id_client) REFERENCES Client(id_client),
   FOREIGN KEY(id_user) REFERENCES User_(id_user)
);

CREATE TABLE order_line(
   id_line INT,
   quantity_formation INT NOT NULL,
   id_formation INT NOT NULL,
   id_order INT NOT NULL,
   PRIMARY KEY(id_line),
   FOREIGN KEY(id_formation) REFERENCES Formation(id_formation),
   FOREIGN KEY(id_order) REFERENCES order_(id_order)
);

