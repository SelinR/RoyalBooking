/* rooms start */
DROP TABLE IF EXISTS rooms;

CREATE TABLE rooms
(
  id serial,
  room_type CHARACTER VARYING(10) NOT NULL,
  beds_amount INTEGER NOT NULL,
  area REAL NOT NULL,
  daily_cost REAL NOT NULL,
  additional_info CHARACTER VARYING(50),
  CONSTRAINT prim PRIMARY KEY(id)
);
INSERT INTO rooms (room_type, beds_amount, area, daily_cost, additional_info) VALUES
('BASIC', 10, 10, 10, 'Room #1'),
('FAMILY', 20, 20, 20, 'Room #2'),
('LUXURY', 30, 30, 30, 'Room #3'),
('PENTHOUSE', 40, 40, 40, 'Room #4');
/* rooms finish */

/*orders*/
drop table if exists orders;

create table orders (
  id serial primary key,
  status character varying not null,
  booked_room_id  integer references rooms(id),
  entry_date date not null,
  leave_date date not null,
  total_price real not null,
  user_id integer not null /*here will be foreign key with reference to user id*/

  /*foreign key (booked_room_id) references rooms(id)*/
);

insert into orders(status, booked_room_id, entry_date, leave_date, total_price, user_id) values
('ACCEPTED', 1, CURRENT_DATE, CURRENT_DATE + INTERVAL '1 DAY', 555.0, 1),
('ACCEPTED', 2, CURRENT_DATE, CURRENT_DATE + INTERVAL '1 DAY', 555.0, 1),
('ACCEPTED', 3, CURRENT_DATE, CURRENT_DATE + INTERVAL '1 DAY', 555.1, 1),
('ACCEPTED', 4, CURRENT_DATE, CURRENT_DATE + INTERVAL '1 DAY', 555.5, 1);
/*orders*/