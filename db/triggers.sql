create or replace function tax_statement()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
		where id = (select id from inserted);
        return new;		
    END;
$$
LANGUAGE 'plpgsql';

create or replace function tax_row()
    returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.2;
		return new;		
    END;
$$
LANGUAGE 'plpgsql';


create trigger tax_trigger_after
    after insert on products
	referencing new table as inserted
    for each statement
    execute procedure tax_statement();
	

create trigger tax_trigger_before
    before insert on products
    for each row
    execute procedure tax_row();
	

create or replace function set_table()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name, price, date)
        values(new.name, new.price, current_date);
		return new;		
    END;
$$
LANGUAGE 'plpgsql';

create trigger set_trigger
    after insert on products
    for each row
    execute procedure set_table();