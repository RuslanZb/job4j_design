create table car_bodies(
	id serial primary key,
	name varchar(30)
);

create table car_engines(
	id serial primary key,
	name varchar(30)
);

create table car_transmissions(
	id serial primary key,
	name varchar(30)
);

create table cars(
	id serial primary key,
	name varchar(30),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

INSERT INTO car_bodies(name)
VALUES ('седан'), 
		('хэтчбек'),
		('пикап'),
		('кабриолет');
		
INSERT INTO car_engines(name)
VALUES ('бензиновый'), 
		('дизельный'),
		('газовый'),
		('электрический');
		
INSERT INTO car_transmissions(name)
VALUES ('механическая'), 
		('автоматическая'),
		('роботизированная'),
		('вариативная');
		
INSERT INTO cars(name, body_id, engine_id, transmission_id)
VALUES ('Лада Малина', 4, 4, 2), 
		('Форд Фокус', 1, 1, 1),
		('Хендай Солярис', 1, 2, 3),
		('КАМАЗ мини', 3, 2, 1),
		('БМВ E34', 1, 1, 1),
		('Газель', null, null, null);
		