INSERT INTO clients (name) VALUES
('John Doe'),
('Jane Doe'),
('Bob Smith'),
('Alice Johnson'),
('Chris Evans'),
('Natalie Portman'),
('Tom Hanks'),
('Scarlett Johansson'),
('Mark Ruffalo'),
('Brie Larson');


INSERT INTO planets (id,name) VALUES
('EA','Earth'),
('MARS','Mars'),
('VEN','Venus'),
('JUP','Jupiter'),
('SAT','Saturn');


INSERT INTO tickets (client_id, from_planet_id, to_planet_id) VALUES
(2, 'JUP', 'EA'),
(3, 'EA', 'SAT'),
(4, 'VEN', 'SAT'),
(5, 'SAT', 'MARS'),
(6, 'MARS', 'EA'),
(7, 'SAT', 'JUP'),
(8, 'JUP', 'MARS'),
(9, 'EA', 'VEN'),
(10, 'MARS', 'VEN');

