CREATE TABLE customers(
    id serial primary key,
    first_name text,
    last_name text,
    age int,
    country text
);

insert into customers (first_name, last_name, age, country) 
values ('Евгений', 'Онегин', 30, 'Бразилия'),
	('Брюс', 'Ли', 14, 'США'),
	('Гарри', 'Поттер', 51, 'ЮАР'),
	('Михаил', 'Дак', 8, 'Непал'),
	('Петр', 'Первый', 8, 'Израиль');

select *
from customers c
where age = (select min(age) from customers);

CREATE TABLE orders(
    id serial primary key,
    amount int,
    customer_id int references customers(id)
);

insert into orders (amount, customer_id) 
values (3, 1),
	(2, 2),
	(2, 3);

select *
from customers c
where id not in (select customer_id from orders
				 where c.id = customer_id);
