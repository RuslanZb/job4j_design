create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
	type_id int references type(id),
	expired_date date,
    price float
);

INSERT INTO type(name)
VALUES ('СЫР'), 
		('МОЛОКО'),
		('МОРОЖЕНОЕ'),
		('СОК');
		
INSERT INTO product(name, type_id, expired_date, price)
VALUES ('Сыр плавленный', 1, '2023-04-01', 300), 
		('Сыр моцарелла', 1, '2023-02-20', 900), 
		('Адыгейский сыр', 1, '2023-03-26', 1000),
		('Сыр пармезан', 1, '2023-02-20', 1000),
		('Сыр чеддер', 1, '2023-03-20', 1000),
		('Сыр гауда', 1, '2023-03-20', 1000),
		('Сыр Рокфор', 1, '2023-03-20', 5000),
		('Косичка', 1, '2023-03-20', 500),
		('Брынза', 1, '2023-03-20', 500),
		('Сыр сулугуни', 1, '2023-03-20', 800),
		('Фета', 1, '2023-03-20', 500),
		('Топленое', 2, '2023-03-10', 100),
		('Деревенское', 2, '2023-02-10', 200),
		('Мороженое фруктовое', 3, '2023-04-10', 50),
		('Пломбир', 3, '2023-03-10', 100),
		('Мороженое мороженое', 3, '2023-03-10', 20),
		('Гранатовый сок', 4, '2023-03-10', 300),
		('Апельсиновый сок', 4, '2023-02-10', 200),
		('Мультифрукт', 4, '2023-04-10', 200),
		('Яблочный', 4, '2023-04-10', 100);

SELECT p.name, expired_date, price
FROM product AS p
JOIN type AS t ON t.id = p.type_id AND t.name = 'СЫР';

SELECT p.name, t.name AS type, expired_date, price
FROM product AS p
JOIN type AS t ON t.id = p.type_id AND p.name LIKE '%мороженое%';

SELECT p.name, t.name AS type, expired_date, price
FROM product AS p
JOIN type AS t ON t.id = p.type_id AND expired_date < CURRENT_DATE;

SELECT p.name, t.name AS type, expired_date, price
FROM product AS p
JOIN type AS t ON t.id = p.type_id
ORDER BY price DESC
LIMIT 1;

SELECT t.name AS имя_типа, COUNT(t.name) AS количество
FROM product AS p
JOIN type AS t ON t.id = p.type_id
GROUP BY t.name;

SELECT p.name, t.name AS type, expired_date, price
FROM product AS p
JOIN type AS t ON t.id = p.type_id
WHERE t.name IN('СЫР','МОЛОКО');

SELECT t.name 
FROM product AS p
JOIN type AS t ON t.id = p.type_id
GROUP BY t.name
HAVING COUNT(t.name) < 10;

SELECT p.name, t.name AS type, expired_date, price 
FROM product AS p
JOIN type AS t ON t.id = p.type_id;
