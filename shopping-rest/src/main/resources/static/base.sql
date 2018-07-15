CREATE TABLE Product
(
  id          INT unsigned NOT NULL AUTO_INCREMENT,
  name        VARCHAR(30)  NOT NULL,
  amount      int unsigned not null,
  description varchar(150),
  PRIMARY KEY (id)
);

insert into Product (name, amount, description) values ('Apple iPhone 7', 500, 'Best smartphone available');
insert into Product (name, amount, description) values ('OnePlus 5t', 300, 'Fastest phone');
insert into Product (name, amount, description) values ('Smasung Galaxy S8', 500, 'Curved screen');

CREATE table shopping_order
(
  id              int unsigned not null AUTO_INCREMENT,
  customerName    varchar(30)  not null,
  customerAddress varchar(100) not null,
  date            date,
  primary key (id)
);

create table shopping_order_row
(
  order_id   int unsigned not null,
  product_id int unsigned not null
);