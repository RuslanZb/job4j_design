create table role(
	id serial primary key,
	name varchar(255)
);

create table users(
	id serial primary key,
	name varchar(255),
	role int references role(id)
);

create table rules(
	id serial primary key,
	rule varchar(255)
);

create table role_rules(
	id serial primary key,
	role_id int references role(id),
	rule_id int references rules(id)
);

create table category(
	id serial primary key,
	category varchar(255)
);

create table state(
	id serial primary key,
	state varchar(255)
);

create table item(
	id serial primary key,
	name varchar(255),
	username int references users(id),
	category int references category(id),
	state int references state(id)
);

create table comments(
	id serial primary key,
	comment text,
	item int references item(id)
);

create table attachs(
	id serial primary key,
	attach varchar(255),
	item int references item(id)
);





