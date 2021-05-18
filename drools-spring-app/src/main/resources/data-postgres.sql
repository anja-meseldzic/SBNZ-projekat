insert into users(id, name, surname, email, password, role, category) values (1, 'Tamara', 'Rankovic', 'pajapataktevoli@gmail.com', '1234', 0,0);
insert into users(id, name, surname, email, password, role, category) values (2, 'Tamara', 'Rankovic', 'pajapataktevoli@gmail.com+1', '1234', 0,0);
insert into users(id, name, surname, email, password, role, category) values (3, 'Tamara', 'Rankovic', 'pajapataktevoli@gmail.com+2', '1234', 0,0);
alter sequence users_id_seq restart with 4;