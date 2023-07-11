create database application;


use application;

CREATE TABLE customer (
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(50),
  last_name VARCHAR(50),
  phone_number VARCHAR(50)
);


CREATE TABLE master (
  ID bigint PRIMARY KEY AUTO_INCREMENT,
  first_name varchar(255),
  last_name varchar(255),
  phone_number varchar(20)
);

CREATE TABLE service (
  ID bigint PRIMARY KEY AUTO_INCREMENT,
  name varchar(50),
  cost double
);

CREATE TABLE service_provider (
  ID bigint PRIMARY KEY AUTO_INCREMENT,
  serviceID bigint,
  masterID bigint,
  FOREIGN KEY (serviceID) REFERENCES service(ID),
  FOREIGN KEY (masterID) REFERENCES master(ID)
);

CREATE TABLE `order` (
  ID bigint PRIMARY KEY AUTO_INCREMENT,
  customerID bigint,
  serviceID bigint,
  masterID bigint,
  `time` TIMESTAMP,
  FOREIGN KEY (customerID) REFERENCES customer(ID),
  FOREIGN KEY (serviceID) REFERENCES service(ID),
  FOREIGN KEY (masterID) REFERENCES master(ID)
);

insert into customer (first_name, last_name, phone_number)
values ('Guy','Elliott','+375291236987') ,
       ('Charley','Christian','+375444125478'),
       ('Yuvraj','Johns', '+375447874123');

insert into master (first_name, last_name, phone_number)
values ('Zachery','King','+375294582369') ,
       ('Preston','Howard','+375295582261'),
       ('Marshall','Spence', '+375295242665');

insert into service (name, cost)
values ('Cheap service','100'),
       ('Middle cost service','500'),
       ('Expensive ','15000');

insert into service_provider(serviceID, masterID)
VALUES  (1,1),
        (2,2),
        (3,3),
        (1,2),
        (1,3);


