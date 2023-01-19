-- ----------------------------------------------------------
-- READ ME BEFORE RUNNING
-- -----------------------------------------------------
-- * MAKE SURE YOU ALREADY HAVE A DATABASE NAMED BANK
-- * AND THAT YOU'VE RUN THE SPRING BOOT APPLICATION ONCE 
-- -- SO IT CAN CREATE THE NECESSARY TABLES FOR YOU
-- -----------------------------------------------------

-- ------- UNCOMMENT LINES BELOW IF NECESSARY ---------------
-- --- RUN THIS LINE IF YOU ALREADY HAVE A DATABASE NAMED BANK AND WANT TO REMOVE IT --
-- drop database bank;

-- --- CREATE A NEW DATABASE FOR THIS BANK PROJECT --
-- create DATABASE bank;
-- -----------------------------------------------------------


-- ----- HERE BEGINS THE SCRIPT -------
use bank;

-- ---- WE ADD AN ADMIN USER: jun90 PASS: 9090 ----------
insert into credentials (secret_pass, username)
values ('$2a$10$oSjx6ha9qJ2m4YUMzpITb.2XrJdWIa5fSsG3saURpi6EENJjCHY6i', 'jun90');
insert into roles (name, credentials_id)
values ('USER', 1),
	('ADMIN', 1),
    ('THIRD', 1);
insert into user(full_name, credentials_id, user_type)
values ('Jun Elli', 1, 1);
    
-- ---- WE ADD A THIRD PARTY USER: JanetPaintings1 PASS: 1919 HASHED KEY: 123ABC ----------
insert into credentials (secret_pass, username)
values ('$2a$10$oSjx6ha9qJ2m4YUMzpITb.2XrJdWIa5fSsG3saURpi6EENJjCHY6i', 'JanetPaintings1');
insert into roles (name, credentials_id)
values ('THIRD', 2);
insert into user(full_name, hashed_key, credentials_id, user_type)
values ('Janet Paintings SA', '$2a$10$Oj2zdI5PIt4/6yk5MCEASufjfF9mEGDIzTrZvAG0OV.adHzWTnX5G', 2, 3);

-- ---- WE ADD ACCOUNTHOLDER 1 USER: Marie9 PASS: 9876 ACCOUNT: CHECKING SECRET KEY: 9999 --------------
-- set credentials and roles --
insert into credentials(secret_pass, username)
values ('$2a$10$MWMfDsXwa7omDXnqhcVQB.b0wbk26oM19ae0lGoSWo0dra2u8Z6cO', 'Marie9');
insert into roles(name, credentials_id)
values('USER', 3);
-- set address --
insert into address(street, additional_address, city, country, postal_code) 
VALUes ('carrer del Croissant', 'entresol 4', 'Barcelona', 'Spain', '08001');
-- set user --
insert into user(full_name, date_of_birth, primary_address_id, credentials_id, user_type)
values ('Marie Boulangerie', '1990-9-9', 1, 3, 2);
-- set account --
insert into checking_accounts(penalty_fee, balance, currency, creation_date, secret_key, status, primary_owner_id, minimum_balance, monthly_maintenance_fee)
values (40, 200, 'USD', '2022-1-1', '$2a$10$bQGNHAEbZl.mf/HKO.0fJu7GdeBM6tR.70KyXn7H4n4jmdjWSXjyS', 'ACTIVE', 3, 250, 12);
insert into credit_cards(penalty_fee, balance, currency, credit_limit, interest_rate, primary_owner_id)
values (40, 505, 'USD', 1000, 0.1, 3);

-- ---- WE ADD ACCOUNTHOLDER 2 USER: Ahmed-s PASS: 8787 ACCOUNT: SAVINGS SECRET KEY: 7788 --------------
-- set credentials and roles --
insert into credentials(secret_pass, username)
values ('$2a$10$bmrjiARBVZpziADHpz2fIeBMzLWzd0AvBQdHS4yVY7TYlcStmX.fG', 'Ahmed-s');
insert into roles(name, credentials_id)
values('USER', 4);
-- set address --
insert into address(street, additional_address, city, country, postal_code) 
VALUes ('carrer de la Sopa, 35', '2n 2a', 'Girona', 'Spain', '45098');
-- set user --
insert into user(full_name, date_of_birth, primary_address_id, credentials_id, user_type)
values ('Sarah Ahmed', '2002-2-2', 2, 4, 2);
-- set student checking account  --
insert into saving_accounts(penalty_fee, balance, currency, creation_date, interest_rate, minimum_balance, secret_key, status, primary_owner_id, secondary_owner_id)
values (40, 2000, 'USD', '2020-1-1', 0.10, 1000, '$2a$10$FuVHmBuI6IjKdFvFXQryN.nEC1tYlO2ClOEq4O1vpJiRmJElndGFm', 'ACTIVE', 4, 3);


-- select * from address;
 select * from user;
-- select * from credentials;
-- select * from roles;
-- SELECT * from checking_accounts;
-- SELECT * from student_checking_accounts;
-- SELECT * from saving_accounts;
-- SELECT * from credit_cards;

-- drop TABLE checking_accounts;

-- delete from address where id = 2;
-- delete from user where full_name = 'Bob el Manitas';
-- delete from credentials where username = 'jun90';
-- delete from roles where credentials_id = 2;
-- delete from checking_accounts where id = 2;
