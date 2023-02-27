create table departments(
	id serial primary key,
	name varchar(30)
);

create table employees(
	id serial primary key,
	name varchar(30),
	department_id int references departments(id)
);

INSERT INTO departments(name)
VALUES ('Администрация'), 
		('Отдел кадров'),
		('Отдел логистики'),
		('Отдел продаж');
		
INSERT INTO employees(name, department_id)
VALUES ('Петров', 2), 
		('Брюс Ли', 1),
		('Якубович', 1),
		('Кутузов', 3),
		('Гарри Поттер', 3);
		
/*2. Выполнить запросы с left, right, full, cross соединениями*/
SELECT *
FROM employees AS e
LEFT JOIN departments AS d ON e.department_id = d.id;

SELECT *
FROM employees AS e
RIGHT JOIN departments AS d ON e.department_id = d.id;

SELECT *
FROM employees AS e
FULL JOIN departments AS d ON e.department_id = d.id;

SELECT *
FROM employees
CROSS JOIN departments;

/*3. Используя left join найти департаменты, у которых нет работников*/
SELECT *
FROM departments AS d
LEFT JOIN employees AS e ON d.id = e.department_id 
WHERE e.id is null;

/*4. Используя left и right join написать запросы, которые давали бы одинаковый результат 
(порядок вывода колонок в эти запросах также должен быть идентичный).*/
SELECT e.id, e.name, e.department_id, d.id, d.name
FROM departments AS d
RIGHT JOIN employees AS e ON e.department_id = d.id;

SELECT *
FROM employees AS e
LEFT JOIN departments AS d ON e.department_id = d.id;

/*5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
Используя cross join составить все возможные разнополые пары.*/
create table teens(
	id serial primary key,
	name varchar(30),
	gender varchar(3)
);

INSERT INTO teens(name, gender)
VALUES ('Петрова', 'ЖЕН'), 
		('Петров', 'МУЖ'), 
		('Брюс Ли', 'МУЖ'),
		('Якубович', 'МУЖ'),
		('Шаполкляк', 'ЖЕН'); 

SELECT (t1.name, t2.name) AS Пара 
FROM teens AS t1
CROSS JOIN teens AS t2
WHERE t1.gender <> t2.gender;
