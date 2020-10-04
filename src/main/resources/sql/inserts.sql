INSERT INTO repair.role (name) VALUES("manager");
INSERT INTO repair.role (name) VALUES("master");
INSERT INTO repair.role (name) VALUES("admin");
SELECT * FROM repair.role;

INSERT INTO repair.user (email, login, password, name, surname, role_id) VALUES('brazuca.egor@gmail.com','brazuca', '1234', 'Egor','Kravchenko', 1);
INSERT INTO repair.user (email, login, password, name,  surname, role_id) VALUES('troi.igor@gmail.com', 'troi', '1111', 'Igor', 'Tsybukh', 2);
INSERT INTO repair.user (email, login, password, name, surname, role_id) VALUES('tribun.bl@gmail.com','vvvv', '2222','Vlad', 'Tribunskiy', 3);
INSERT INTO repair.user (email, login, password, name, surname, role_id) VALUES('master_default@gmail.com','master', 'master','master', 'default', 2);

SELECT * FROM repair.user;


INSERT INTO repair.status (name) VALUE ("Ждет оплаты");
INSERT INTO repair.status (name) VALUE ("Оплачено");
INSERT INTO repair.status (name) VALUE ("Отменено");
INSERT INTO repair.status (name) VALUE ("В работе");
INSERT INTO repair.status (name) VALUE ("Исполнено");

INSERT INTO repair.details (user_id, balance) VALUE (1, 250);



SELECT * FROM  repair.status;
SELECT * FROM repair.user;
SELECT * from repair.details where repair.details.user_id = repair.user.user_id ;
INSERT INTO repair.details (user_id, balance) VALUE (1, 250);

select details.balance from repair.details where repair.details.user_id = 1;
SELECT * from repair.details;
UPDATE  repair.details SET balance=balance+120 WHERE repair.details.user_id=1;

Select * from  repair.details;
INSERT INTO repair.request (user_id, master_id, name, description, date, status_id) values (7, 4, 'Починка вытяжки', 'Починить вытяжку на кухне.', '2020-10-04', 1);
select* from repair.request;