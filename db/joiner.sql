CREATE TABLE author(
    id serial PRIMARY KEY,
    name varchar(30)
);

CREATE TABLE book(
    id serial PRIMARY KEY,
    name varchar(30),
	page int,
	author_id int REFERENCES author(id)    
);

INSERT INTO author(name)
VALUES ('Пушкин А.С.'), 
		('Лермонтов М.Ю.'), 
		('Достаевский Ф.М.');

INSERT INTO book(name, page, author_id)
VALUES ('Преступление и наказание', 500, 3), 
		('Идиот', 600, 3), 
		('Капитанская дочь', 200, 1),
		('Золотая рыбка', 50, 1),
		('Герой нашего времени', 150, 2);

SELECT b.name, a.name
FROM book AS b JOIN author AS a 
ON b.author_id = a.id;

SELECT b.name, page, a.name
FROM book AS b JOIN author AS a 
ON b.author_id = a.id and page >200;

SELECT b.name, page, a.name
FROM book AS b JOIN author AS a 
ON b.author_id = a.id
WHERE b.name LIKE '% %';
