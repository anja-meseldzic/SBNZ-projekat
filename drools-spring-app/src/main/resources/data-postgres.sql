insert into users(id, name, surname, email, password, role, category) values (1, 'Anja', 'Tosic', 'a@gmail.com', '1234', 0,0);
insert into users(id, name, surname, email, password, role, category) values (2, 'Mika', 'Antic', 'm@gmail.com', '1234', 0,0);
insert into users(id, name, surname, email, password, role, category) values (3, 'Vuk', 'Jovic', 'v@gmail.com', '1234', 0,0);
insert into users(id, name, surname, email, password, role, category) values (4, 'Luka', 'Lukic', 'l@gmail.com', '1234', 0,0);
alter sequence users_id_seq restart with 5;

insert into restaurant(id, cuisine_type,delivery,location, name, price_category) values (1,6,1,2,'McDonalds',null);
insert into restaurant(id, cuisine_type,delivery,location, name, price_category) values (2,6,1,1,'KFC',null);
insert into restaurant(id, cuisine_type,delivery,location, name, price_category) values (3,3,3,2,'La Forza',null);
insert into restaurant(id, cuisine_type,delivery,location, name, price_category) values (4,0,2,2,'Plava frajla',null);


alter sequence restaurant_id_seq restart with 5;

insert into dish(id, dish_type, food_type, name) values (1, 1,1,'cheesburger');
insert into dish(id, dish_type, food_type, name) values (2, 1,1,'hamburger');
insert into dish(id, dish_type, food_type, name) values (3, 1,1,'chicken wings');
insert into dish(id, dish_type, food_type, name) values (4, 1,1,'pizza');
insert into dish(id, dish_type, food_type, name) values (5, 3,1,'icecream');
insert into dish(id, dish_type, food_type, name) values (6, 6,0,'chicken salad');
insert into dish(id, dish_type, food_type, name) values (7, 4,4,'beef soup');

alter sequence dish_id_seq restart with 8;

insert into price(id, price, price_category, dish_id, restaurant_id) values (1, 350, null, 1,1);
insert into price(id, price, price_category, dish_id, restaurant_id) values (2, 250, null, 1,2);
insert into price(id, price, price_category, dish_id, restaurant_id) values (3, 400, null, 2,1);
insert into price(id, price, price_category, dish_id, restaurant_id) values (4, 380, null, 2,2);
insert into price(id, price, price_category, dish_id, restaurant_id) values (5, 348, null, 3,2);
insert into price(id, price, price_category, dish_id, restaurant_id) values (6, 650, null, 4,3);
insert into price(id, price, price_category, dish_id, restaurant_id) values (7, 280, null, 5,1);
insert into price(id, price, price_category, dish_id, restaurant_id) values (8, 322, null, 5,2);
insert into price(id, price, price_category, dish_id, restaurant_id) values (9, 420, null, 6,1);
insert into price(id, price, price_category, dish_id, restaurant_id) values (10, 600, null, 6,3);
insert into price(id, price, price_category, dish_id, restaurant_id) values (11, 400, null, 7,4);
insert into price(id, price, price_category, dish_id, restaurant_id) values (12, 200, null, 7,3);

alter sequence price_id_seq restart with 13;


insert into restaurant_menu(restaurant_id, menu_id) values (1,1);
insert into restaurant_menu(restaurant_id, menu_id) values (2,1);
insert into restaurant_menu(restaurant_id, menu_id) values (1,2);
insert into restaurant_menu(restaurant_id, menu_id) values (2,2);
insert into restaurant_menu(restaurant_id, menu_id) values (2,3);
insert into restaurant_menu(restaurant_id, menu_id) values (3,4);
insert into restaurant_menu(restaurant_id, menu_id) values (1,5);
insert into restaurant_menu(restaurant_id, menu_id) values (2,5);
insert into restaurant_menu(restaurant_id, menu_id) values (1,6);
insert into restaurant_menu(restaurant_id, menu_id) values (3,6);
insert into restaurant_menu(restaurant_id, menu_id) values (4,7);
insert into restaurant_menu(restaurant_id, menu_id) values (3,7);

insert into discount(id, discount, start_date, end_date, dish_id, restaurant_id) values (1, 10, '2021-09-10 08:00:00', '2021-09-20 08:00:00',1,1);
insert into discount(id, discount, start_date, end_date, dish_id, restaurant_id) values (2, 10, '2021-09-10 08:00:00', '2021-09-20 08:00:00' ,2,1);
