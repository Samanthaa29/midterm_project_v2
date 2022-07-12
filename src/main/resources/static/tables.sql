DROP SCHEMA IF EXISTS midterm_project_test;
CREATE SCHEMA midterm_project_test;
USE midterm_project_test;

DROP SCHEMA IF EXISTS midterm_project;
CREATE SCHEMA midterm_project;
USE midterm_project;

CREATE TABLE user (
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR (255),
    password VARCHAR(255),
    PRIMARY KEY(id)
);

INSERT INTO user (name, password) VALUES
('Samantha','$2a$10$pA9dFNVJuQpsKc.mmTO2GeNzPm.d17IvAWGsBlUxHaRO6yFhagpZi'),
('Iñaki','$2a$10$RF3qixgdrEWAc.SPED/.n.azg2nkjv5bIiPht340LJpUB1aMciUsS'),
('Eric','$2a$10$uCrKWTQuzGDkzlnf2Qe94uQ6vj/Zpt.sGTRG5egynj8oZQR6mr4de');

CREATE TABLE account_holder(
	id BIGINT NOT NULL,
	date_of_birth DATE,
    street VARCHAR (255),
    number INT,
    city VARCHAR (255),
    postal_code INT,
	mailing_address_city VARCHAR(255),
    mailing_address_number INT,
    mailing_address_postal_code INT,
    mailing_address_street VARCHAR(255),
    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES user(id)
);

INSERT INTO account_holder (id, date_of_birth, street, number, city, postal_code, mailing_address_city, mailing_address_number, mailing_address_postal_code, mailing_address_street) VALUES
(1,'1984-03-02','can torres',6,'barcelona',08630,'barcelona',6,08630,'can torres'),
(2,'1998-07-20','rubio',12,'barcelona',08960,'barcelona',24,08960,'rubio');


CREATE TABLE account (
  id BIGINT NOT NULL AUTO_INCREMENT,
  amount DECIMAL(19,2),
  currency VARCHAR(255),
  creation_date DATE,
  primary_owner_id BIGINT,
  secondary_owner_id BIGINT,
  penalty_amount DECIMAL(19,2),
  penalty_currency VARCHAR(255),
  secret_key VARCHAR(255),
  PRIMARY KEY (id),
  FOREIGN KEY (primary_owner_id) REFERENCES account_holder(id),
  FOREIGN KEY (secondary_owner_id) REFERENCES account_holder(id)
);

INSERT INTO account (amount, currency, creation_date, primary_owner_id, secondary_owner_id, penalty_amount, penalty_currency) VALUES
(1800.3,'€','2020-07-20',1,2,30.0,'€'),
(30000.14,'€','2022-04-20',2,1,100.0,'€');


CREATE TABLE credit_card (
  id BIGINT NOT NULL,
  amount DECIMAL(19,2),
  currency VARCHAR(255),
  last_interests_modification_date DATE,
  interest_rate DECIMAL(19,2),
  PRIMARY KEY (id),
  FOREIGN KEY(id) REFERENCES account(id)
);


CREATE TABLE role (
  id BIGINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  user_id BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY(user_id) REFERENCES user(id)
);

INSERT INTO role(name, user_id) VALUES
('ADMIN', 1),
('USER', 1),
('USER', 2),
('USER', 3);
  
CREATE TABLE savings (
  id BIGINT NOT NULL,
  amount DECIMAL(19,2),
  currency VARCHAR(255),
  status VARCHAR(255),
  interest_rate DECIMAL(19,2),
  last_interests_modification_date DATE,
  PRIMARY KEY (id),
  FOREIGN KEY(id) REFERENCES account(id)
);


CREATE TABLE student_checking (
  id BIGINT NOT NULL,
  creation_date DATE,
  status VARCHAR(255),
  PRIMARY KEY (id),
  FOREIGN KEY(id) REFERENCES account(id)
);

CREATE TABLE checking (
  id BIGINT NOT NULL AUTO_INCREMENT,
  amount DECIMAL(19,2),
  currency VARCHAR(255),
  monthly_maintenance_fee_amount DECIMAL(19,2),
  monthly_maintenance_fee_currency VARCHAR(255),
  status VARCHAR(255),
  PRIMARY KEY (id),
  FOREIGN KEY(id) REFERENCES account(id)
);


INSERT INTO checking (amount, currency, monthly_maintenance_fee_amount, monthly_maintenance_fee_currency, status) VALUES
(30789.62,'€',10,'€','ACTIVE');

CREATE TABLE admin(
	id BIGINT NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES user(id)
);

INSERT INTO admin(id) VALUES
(1);

CREATE TABLE third_party(
	id BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR (255),
    hashed_key VARCHAR (255),
    PRIMARY KEY(id)
);

INSERT INTO third_party(id, name, hashed_key) VALUES
(1, 'Laura Garcia', '9876432');

SELECT * FROM user;
SELECT * FROM account_holder;
SELECT * FROM account;
SELECT * FROM checking;

