-- DROP DATABASE sales;
CREATE DATABASE sales;
USE sales;

CREATE TABLE status(
	id INT AUTO_INCREMENT,
	name VARCHAR(50),
	description VARCHAR(70),
	PRIMARY KEY(id)
);

CREATE TABLE customers(
	id INT AUTO_INCREMENT,
	first_name VARCHAR(90) NOT NULL,
	last_name VARCHAR(90) NOT NULL,
	email VARCHAR(70) NOT NULL,
	phone_number VARCHAR(15) NOT NULL,
	address VARCHAR(150),
	pass VARCHAR(32) NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE orders(
	id INT AUTO_INCREMENT,
	order_number VARCHAR(35) NOT NULL,
	customer_id INT NOT NULL,
	status_id INT NOT NULL,
	date_created DATETIME NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(customer_id) REFERENCES customers(id),
	FOREIGN KEY(status_id) REFERENCES status(id)
);

CREATE TABLE products(
	id INT AUTO_INCREMENT,
	name VARCHAR(80) NOT NULL,
	description TEXT NOT NULL,
	image TEXT NOT NULL,
	stock_quantity INT NOT NULL DEFAULT 0,
	price DECIMAL(10,2) NOT NULL DEFAULT 0,
	cost DECIMAL(10,2) NOT NULL DEFAULT 0,
	sale_price DECIMAL(10,2) NOT NULL DEFAULT 0,
	PRIMARY KEY(id)
);

CREATE TABLE orders_details(
	id INT AUTO_INCREMENT,
	order_id INT NOT NULL,
	product_id INT NOT NULL,
	quantity INT NOT NULL,
	total_line DECIMAL(10,2) NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY(order_id) REFERENCES orders(id),
	FOREIGN KEY(product_id) REFERENCES products(id)
);

INSERT INTO status (name, description)
VALUES
('creado', 'pedido creado'),
('pedido','pedido empacado'),
('entregado', 'pedido entregado'),
('fallido', 'pedido fallido');

ALTER TABLE customers ADD COLUMN created_date DATETIME;
ALTER TABLE customers ADD COLUMN modified_date DATETIME;
ALTER TABLE customers ADD COLUMN isDeleted TINYINT(1) DEFAULT 0;

ALTER TABLE status ADD COLUMN created_date DATETIME;
ALTER TABLE status ADD COLUMN modified_date DATETIME;
ALTER TABLE status ADD COLUMN isDeleted TINYINT(1) DEFAULT 0;

ALTER TABLE orders ADD COLUMN created_date DATETIME;
ALTER TABLE orders ADD COLUMN modified_date DATETIME;
ALTER TABLE orders ADD COLUMN isDeleted TINYINT(1) DEFAULT 0;
ALTER TABLE orders ADD COLUMN total DECIMAL(10,2) NOT NULL;

ALTER TABLE products ADD COLUMN created_date DATETIME;
ALTER TABLE products ADD COLUMN modified_date DATETIME;
ALTER TABLE products ADD COLUMN isDeleted TINYINT(1) DEFAULT 0;

ALTER TABLE orders_details ADD COLUMN created_date DATETIME;
ALTER TABLE orders_details ADD COLUMN modified_date DATETIME;
ALTER TABLE orders_details ADD COLUMN isDeleted TINYINT(1) DEFAULT 0;