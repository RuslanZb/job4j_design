create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

INSERT INTO devices(name, price)
VALUES ('Телефон', 500), 
		('Наушники', 100), 
		('Ноутбук', 2000),
		('Гравипушка', 10000),
		('Велосипед', 3000);

INSERT INTO people(name)
VALUES ('Джонни'), 
		('Ли'), 
		('Адольф'),
		('Сэм');

INSERT INTO devices_people(device_id, people_id)
VALUES (1,1), (2,1), (5,1),
	 	(2,2), (3,2), (4,2), (5,2),
		(2,3), (3,3), (1,3), (5,3),
		(1,4);
		
SELECT AVG(price) AS "Средняя стоимость"
FROM devices;

SELECT p.name, AVG(price) AS "Средняя стоимость"
FROM devices AS d
JOIN devices_people AS dp ON d.id = dp.device_id
JOIN people AS p ON p.id = dp.people_id
GROUP BY p.name;

SELECT p.name, AVG(price) AS "Средняя стоимость"
FROM devices AS d
JOIN devices_people AS dp ON d.id = dp.device_id
JOIN people AS p ON p.id = dp.people_id
GROUP BY p.name
HAVING AVG(price) > 3000;


