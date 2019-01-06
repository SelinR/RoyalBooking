/* rooms start */
DROP TABLE IF EXISTS public.rooms;
DROP SEQUENCE IF EXISTS public.id_rooms_seq;
CREATE SEQUENCE public.id_rooms_seq
  INCREMENT 1
  START 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  CACHE 1;
CREATE TABLE public.rooms
  (
  id INTEGER DEFAULT NEXTVAL('public.id_rooms_seq') NOT NULL,
  room_type CHARACTER VARYING(10) NOT NULL,
  beds_amount INTEGER NOT NULL,
  area REAL NOT NULL,
  daily_cost REAL NOT NULL,
  additional_info CHARACTER VARYING(50),
  CONSTRAINT prim PRIMARY KEY(id)
  );
INSERT INTO public.rooms (room_type, beds_amount, area, daily_cost, additional_info) VALUES
  ('BASIC', 1, 1, 1, 'Room #1'),
  ('FAMILY', 2, 2, 2, 'Room #2'),
  ('LUXURY', 3, 3, 3, 'Room #3'),
  ('PENTHOUSE', 4, 4, 4, 'Room #4');
/* rooms finish */