CREATE TABLE book(
    id serial PRIMARY KEY,
    name varchar(30),
	author varchar(30), 
	price real
);

INSERT INTO book(name, author, price)
VALUES ('Преступление и наказание', 'Достаевский Ф.М.', 500), 
		('Идиот', 'Достаевский Ф.М.', 600), 
		('Капитанская дочь', 'Пушкин А.С.', 200),
		('Золотая рыбка', 'Пушкин А.С.', 50),
		('Герой нашего времени', 'Лермонтов М.Ю.', 150);

begin transaction;
insert into book(name, author, price)
values ('Игрок', 'Достаевский Ф.М.', 700);
savepoint first_savepoint;
delete from book where price = 50;
select * from book;
rollback to first_savepoint;
select * from book;
commit;
begin transaction;
insert into book(name, author, price)
values ('Игрок', 'Достаевский Ф.М.', 700);
savepoint first_savepoint;
delete from book where price = 50;
savepoint second_savepoint;
insert into book(name, author, price)
values ('Баязет', 'Пикуль В.С.', 800);
delete from book where price = 200;
savepoint third_savepoint;
update book set price = 2000 where name = 'Идиот';
select * from book;
rollback to first_savepoint;
select * from book;
delete from book where price = 50;
savepoint second_savepoint;
insert into book(name, author, price)
values ('Баязет', 'Пикуль В.С.', 800);
delete from book where price = 200;
savepoint third_savepoint;
update book set price = 2000 where name = 'Идиот';
select * from book;
rollback to second_savepoint;
select * from book;
rollback to first_savepoint;
select * from book;
commit;