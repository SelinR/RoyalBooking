DROP TABLE IF EXISTS rooms CASCADE;
CREATE TABLE rooms
(
  id serial,
  room_type CHARACTER VARYING(10) NOT NULL,
  beds_amount INTEGER NOT NULL,
  area REAL NOT NULL,
  daily_cost REAL NOT NULL,
  additional_info CHARACTER VARYING(50),
  CONSTRAINT room_prim PRIMARY KEY(id)
);

DROP TABLE IF EXISTS users CASCADE;
CREATE TABLE users
(
  id SERIAL,
  name VARCHAR NOT NULL,
  surname VARCHAR NOT NULL,
  country VARCHAR NOT NULL,
  birthday DATE NOT NULL,
  phone VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  password VARCHAR NOT NULL,
  user_type VARCHAR NOT NULL,
  CONSTRAINT user_prim PRIMARY KEY(id)
);

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders (
  id SERIAL PRIMARY KEY,
  status CHARACTER VARYING NOT NULL,
  booked_room_id  INTEGER REFERENCES rooms(id) ON DELETE CASCADE ON UPDATE CASCADE,
  entry_date date NOT NULL,
  leave_date date NOT NULL,
  total_price REAL NOT NULL,
  user_id INTEGER REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
  prepaid BOOLEAN NOT NULL
);

INSERT INTO rooms (room_type, beds_amount, area, daily_cost, additional_info) VALUES
('BASIC', 1, 20, 50, 'Room #1'),
('BASIC', 2, 30, 80, 'Room #2'),
('BASIC', 1, 30, 100, 'Room #3'),
('BASIC', 2, 35, 120, 'Room #4'),
('BASIC', 1, 30, 100, 'Room #5'),
('BASIC', 2, 40, 130, 'Room #6'),
('FAMILY', 3, 50, 160, 'Room #7'),
('FAMILY', 4, 55, 180, 'Room #8'),
('FAMILY', 4, 60, 200, 'Room #9'),
('LUXURY', 2, 70, 300, 'Room #10'),
('LUXURY', 3, 80, 400, 'Room #11'),
('LUXURY', 4, 90, 500, 'Room #12'),
('PENTHOUSE', 4, 100, 700, 'Room #13'),
('PENTHOUSE', 4, 150, 1000, 'Room #14');

INSERT INTO users (name, surname, country, birthday, phone, email, password, user_type) VALUES
('Jeorge', 'Bush', 'USA', '1946-07-06', '+12345236612','presidentBUSHJR@gmail.com', '$2a$04$x3rEDU510CvdKY6qmX324u9M0ff4RJaMWinhH964Jo4yIKMBG9hrC', 'ADMIN'),
('Vasiliy', 'Semenov', 'Russia', '1972-01-17', '+79214232512','semenovVV@mail.ru', '$2a$04$rih1/TCL5sJhFqc.YzY19u9ruelHQTAL7oCDougNaJqTUZs/oIVrO', 'USER'),
('Yulia', 'Kim', 'South Korea', '1998-11-07', '+829431234123','YKimSempai@southkoreanmail.com', '$2a$04$c9SdlQ0asYZEyU4gyt.7leqlOJRX7izg2z/Grw226TCAlnb3cvvzu', 'USER'),
('Mysterious', 'Stranger', 'Secret', CURRENT_DATE, '0','MySterMysterious@mystery.org', '$2a$04$9zxdZ4RkakshXHgZEJzbve5GrkLJZtsB9NGRDvv8Lw.78IHFqaM82', 'USER');

INSERT INTO orders(status, booked_room_id, entry_date, leave_date, total_price, user_id, prepaid) VALUES
('ACCEPTED', 1, CURRENT_DATE, CURRENT_DATE + INTERVAL '1 DAY', 100.0, 1, 'no'),
('ACCEPTED', 2, CURRENT_DATE, CURRENT_DATE + INTERVAL '2 DAY', 240.0, 2, 'no'),
('ACCEPTED', 3, CURRENT_DATE, CURRENT_DATE + INTERVAL '3 DAY', 400.0, 3, 'no'),
('ACCEPTED', 4, CURRENT_DATE, CURRENT_DATE + INTERVAL '4 DAY', 600.0, 4, 'yes'),
('ACCEPTED', 5, CURRENT_DATE + INTERVAL '2 DAY', CURRENT_DATE + INTERVAL '4 DAY', 300.0, 1, 'no'),
('ACCEPTED', 6, CURRENT_DATE + INTERVAL '3 DAY', CURRENT_DATE + INTERVAL '7 DAY', 650.0, 2, 'no'),
('ACCEPTED', 6, CURRENT_DATE - INTERVAL '2 DAY', CURRENT_DATE - INTERVAL '1 DAY', 260.0, 2, 'yes');