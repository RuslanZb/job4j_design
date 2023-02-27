create table students (
    id serial primary key,
    name varchar(50)
);

insert into students (name) values ('Иван Иванов');
insert into students (name) values ('Петр Петров');
insert into students (name) values ('Джеймс Бонд');

create table authors (
    id serial primary key,
    name varchar(50)
);

insert into authors (name) values ('Александр Пушкин');
insert into authors (name) values ('Николай Гоголь');
insert into authors (name) values ('Федор Достаевский');

create table books (
    id serial primary key,
    name varchar(200),
    author_id integer references authors(id)
);

insert into books (name, author_id) values ('Евгений Онегин', 1);
insert into books (name, author_id) values ('Капитанская дочка', 1);
insert into books (name, author_id) values ('Дубровский', 1);
insert into books (name, author_id) values ('Мертвые души', 2);
insert into books (name, author_id) values ('Вий', 2);
insert into books (name, author_id) values ('Тарас Бульба', 2);
insert into books (name, author_id) values ('Идиот', 3);
insert into books (name, author_id) values ('Преступление и наказание', 3);
insert into books (name, author_id) values ('Игрок', 3);

create table orders (
    id serial primary key,
    active boolean default true,
    book_id integer references books(id),
    student_id integer references students(id)
);

insert into orders (book_id, student_id) values (1, 1);
insert into orders (book_id, student_id) values (3, 1);
insert into orders (book_id, student_id) values (5, 2);
insert into orders (book_id, student_id) values (4, 1);
insert into orders (book_id, student_id) values (2, 2);
insert into orders (book_id, student_id) values (6, 3);
insert into orders (book_id, student_id) values (7, 3);
insert into orders (book_id, student_id) values (8, 3);

/*Самый читаемый(популярный) автор*/
CREATE VIEW popular_authors
	AS SELECT a.id author_id, a.name author_name, COUNT(o.book_id) books_in_order 
	FROM orders o 
	JOIN books b ON o.book_id = b.id
	JOIN authors a ON b.author_id = a.id
	GROUP BY a.id
	HAVING COUNT(o.book_id) IN  
		(SELECT MAX(o.book_id)
		FROM orders o
		JOIN books b ON o.book_id = b.id
		GROUP BY b.author_id);
		
SELECT *
FROM popular_authors;

/*Студенты, которые читают самых популярных авторов*/
SELECT s.name
FROM popular_authors, students s  
JOIN orders o ON o.student_id = s.id
JOIN books b ON o.book_id = b.id
WHERE b.author_id IN (popular_authors.author_id)
GROUP BY s.id;