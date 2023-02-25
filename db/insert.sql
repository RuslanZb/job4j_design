insert into role (name) values ('root');
insert into users (name, role) values ('Admin', 1);
insert into rules (rule) values ('read, write, delete');
insert into role_rules (role_id, rule_id) values (1,1);
insert into category (category) values ('daily');
insert into state (state) values ('completed');
insert into item (name, username, category, state) values ('change username', 1, 1, 1);
insert into comments (comment, item) values ('previous username: User1, new username: User2', 1);
insert into attachs (attach, item) values ('C:\order.pdf', 1);