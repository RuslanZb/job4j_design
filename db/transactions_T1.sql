begin transaction;
select * from book;
insert into book(name, author, price)
values ('Игрок', 'Достаевский Ф.М.', 700);
delete from book where price = 50;
update book set price = 100 where name = 'Капитанская дочь';
select * from book;
commit;
begin transaction isolation level repeatable;
insert into book(name, author, price)
values ('Преступление и наказание', 'Достаевский Ф.М.', 800);
delete from book where price = 50;
update book set price = 200 where name = 'Капитанская дочь';
commit;
begin transaction isolation level serializable;
select sum(price) from book;
update book set price = 1000 where name = 'Преступление и наказание';
commit;