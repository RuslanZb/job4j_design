create table wives(
	id serial primary key,
	name varchar(255)
);

create table husbands(
	id serial primary key,
	name varchar(255)
);

create table wives_husbands(
	id serial primary key,
	wife_id int references wives(id) unique,
	husband_id int references husbands(id) unique
);