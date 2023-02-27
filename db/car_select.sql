SELECT c.id, c.name car_name, b.name body_name, 
		e.name engine_name, t.name transmission_name
FROM cars c
LEFT JOIN car_bodies b ON c.body_id = b.id 
LEFT JOIN car_engines e ON c.engine_id = e.id 
LEFT JOIN car_transmissions t  ON c.transmission_id = t.id;

SELECT b.name body_name
FROM car_bodies b
LEFT JOIN cars c ON c.body_id = b.id
WHERE c.body_id is null;

SELECT e.name engine_name
FROM car_engines e
LEFT JOIN cars c ON c.engine_id = e.id
WHERE c.engine_id is null;

SELECT t.name transmission_name
FROM car_transmissions t
LEFT JOIN cars c ON c.transmission_id = t.id
WHERE c.transmission_id is null;
