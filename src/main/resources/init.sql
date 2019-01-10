/* rooms start */
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
INSERT INTO rooms (room_type, beds_amount, area, daily_cost, additional_info) VALUES
('BASIC', 10, 10, 10, 'Room #1'),
('FAMILY', 20, 20, 20, 'Room #2'),
('LUXURY', 30, 30, 30, 'Room #3'),
('PENTHOUSE', 40, 40, 40, 'Room #4');
/* rooms finish */
/* users beginning*/
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
  user_type VARCHAR NOT NULL,
  CONSTRAINT user_prim PRIMARY KEY(id)
);
INSERT INTO users (name, surname, country, birthday, phone, email, user_type) VALUES
('Vasiliy', 'Semenov', 'Russia', '1972-01-17', '+79214232512','semenovVV@mail.ru', 'USER'),
('Jeorge', 'Bush', 'USA', '1946-07-06', '+12345236612','presidentBUSHJR@gmail.com', 'ADMIN'),
('Yulia', 'Kim', 'South Korea', '1998-11-07', '+829431234123','YKimSempai@southkoreanmail.com', 'USER'),
('Mysterious', 'Stranger', 'Secret', CURRENT_DATE, '0','MySterMysterious@mystery.org', 'USER');

/* users end */
/*orders*/
DROP TABLE IF EXISTS orders CASCADE;

CREATE TABLE orders (
  id SERIAL PRIMARY KEY,
  status CHARACTER VARYING NOT NULL,
  booked_room_id  INTEGER REFERENCES rooms(id) ON DELETE CASCADE ON UPDATE CASCADE,
  entry_date date NOT NULL,
  leave_date date NOT NULL,
  total_price REAL NOT NULL,
  user_id INTEGER REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE

);

INSERT INTO orders(status, booked_room_id, entry_date, leave_date, total_price, user_id) VALUES
('ACCEPTED', 1, CURRENT_DATE, CURRENT_DATE + INTERVAL '1 DAY', 555.0, 1),
('ACCEPTED', 2, CURRENT_DATE, CURRENT_DATE + INTERVAL '1 DAY', 555.0, 1),
('ACCEPTED', 3, CURRENT_DATE, CURRENT_DATE + INTERVAL '1 DAY', 555.1, 1),
('ACCEPTED', 4, CURRENT_DATE, CURRENT_DATE + INTERVAL '1 DAY', 555.5, 1);
/*orders*/
