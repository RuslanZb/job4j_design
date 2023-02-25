create table shops(
	id serial primary key,
	name varchar(255)
);

create table products(
	id serial primary key,
	product varchar(255)
);

create table shops_products(
	id serial primary key,
	shop_id int references shops(id),
	product_id int references products(id)
);