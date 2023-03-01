create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure p_delete_data(i_price integer)
language 'plpgsql'
as $$
    BEGIN
    	delete from products
    	where price = i_price;
    END
$$;

create or replace function f_delete_data(i_count integer)
returns integer 
language 'plpgsql'
as $$
	declare
		result integer;
    BEGIN
		select into result SUM(count) from products
		where count < i_count;
    	delete from products
    	where count < i_count;
		return result;
    END
$$;

