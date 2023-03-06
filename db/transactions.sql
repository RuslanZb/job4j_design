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

