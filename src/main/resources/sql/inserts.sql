INSERT INTO repair.role (name) VALUES('manager');
INSERT INTO repair.role (name) VALUES("master");
INSERT INTO repair.role (name) VALUES("admin");
SELECT * FROM repair.role;

INSERT INTO repair.user (login, password, surname, role_id) VALUES('brazuca', '1234', 'Kravchenko', 1);
INSERT INTO repair.user (login, password, surname, role_id) VALUES('troi', '1111', 'tsybukh', 2);
INSERT INTO repair.user (login, password, surname, role_id) VALUES('vvvv', '2222', 'tribunskiy', 3);
SELECT * FROM repair.user;