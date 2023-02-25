create table planner(
	id serial primary key,
	task text,
	deadline date,
	done bool
);
insert into planner(task, deadline, done) values ('Создать БД', '2023-02-25', false);
update planner set done = true;
delete from planner;