create table ingredients(
	id serial primary key,
	name varchar(255)
);

create table recipes(
	id serial primary key,
	name varchar(255),
	ingredient int references ingredient(id)
);