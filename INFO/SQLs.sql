CREATE DATABASE IF NOT EXISTS `spring_web_mvc_contact`
/*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

CREATE TABLE spring_web_mvc_contact.contacts (
	id BIGINT UNSIGNED auto_increment NOT NULL,
	first_name varchar(128) NOT NULL,
	last_name varchar(128) NOT NULL,
	phone varchar(32) NOT NULL,
	CONSTRAINT contacts_pk PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_general_ci;
CREATE UNIQUE INDEX contacts_full_name_IDX USING BTREE ON spring_web_mvc_contact.contacts (first_name,last_name);