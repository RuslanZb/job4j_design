CREATE TABLE fauna(
    id serial PRIMARY KEY,
    name text,
    avg_age int,
    discovery_date date
);

INSERT INTO fauna(name, avg_age, discovery_date)
VALUES ('Cow', 15000, '2000-01-01'),
	   ('Red fish', 2000, '1950-01-01'),
	   ('Cat', 3000, null),
	   ('Dog', 3000, '1350-01-01'),
	   ('Human', 20000, null),
	   ('Wolf', 11000, '0010-10-10');
	   
SELECT name, avg_age, discovery_date
FROM fauna
WHERE name LIKE '%fish%';

SELECT name, avg_age, discovery_date
FROM fauna
WHERE avg_age BETWEEN 10000 AND 21000;

SELECT name, avg_age, discovery_date
FROM fauna
WHERE discovery_date is null;

SELECT name, avg_age, discovery_date
FROM fauna
WHERE EXTRACT(YEAR FROM discovery_date) < 1950 AND discovery_date is not null;


