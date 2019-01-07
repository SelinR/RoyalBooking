/* rooms start */
DROP TABLE IF EXISTS rooms;
DROP SEQUENCE IF EXISTS id_rooms_seq;
CREATE SEQUENCE id_rooms_seq
  INCREMENT 1
  START 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  CACHE 1;
CREATE TABLE rooms
  (
  id INTEGER DEFAULT NEXTVAL('id_rooms_seq') NOT NULL,
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