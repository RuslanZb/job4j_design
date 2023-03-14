CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name)
values (1, 'Форд'),
	(2, 'Труд'),
	(3, 'Гугл'),
	(4, 'ГазМонтажСпецТехСервис'),
	(5, 'Сименс');
	
insert into person(id, name, company_id)
values (1, 'Джек Воробей', 5),
	(2, 'Брюс Ли', 1),
	(3, 'Иван Грозный', 3),
	(4, 'Стивен Джеррард', 1),
	(5, 'Алла Пугачева', 5),
	(6, 'Оби Ван Кеноби', 2);
	
select p.name person_name, c.name company_name
from person p
join company c ON c.id = p.company_id
where c.id <> 5;

select c.name company_name, count(p.name)
from person p
join company c ON c.id = p.company_id
group by company_id, c.name
having count(p.name) = (select count(p.name)
				  		from person p
				  		group by company_id
						order by count(p.name) desc
						limit 1
				  		);