CREATE TABLE client (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    cpf VARCHAR(14)
);

CREATE TABLE product (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(1000),
    unit_price NUMERIC(20,2) 
);

CREATE TABLE purchase (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    client_id INTEGER REFERENCES client(id),
    purchase_date TIMESTAMP,
    status VARCHAR(20),
    total NUMERIC(20,2)
);

CREATE TABLE purchase_item (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    purchase_id INTEGER REFERENCES purchase(id),
    product_id INTEGER REFERENCES product(id),
    qty INTEGER
);

CREATE TABLE users (
	id INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,
    admin BOOLEAN DEFAULT FALSE
);