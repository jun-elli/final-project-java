create DATABASE final_project;
use final_project;

insert into address(street, additional_address, city, country, postal_code) 
VALUes ('carrer del Croissant', 'entresol 4', 'Barcelona', 'Spain', '08001');

insert into credentials(secret_pass, username)
values ('9876', 'Marie9');
UPDATE credentials SET secret_pass = '$2a$10$MWMfDsXwa7omDXnqhcVQB.b0wbk26oM19ae0lGoSWo0dra2u8Z6cO' where username = 'Marie9';

insert into roles(name, credentials_id)
values('USER', 1);

insert into user(full_name, date_of_birth, primary_address_id, credentials_id, user_type)
values ('Marie Boulangerie', '1990-9-9', 1, 1, 2);

insert into checking_accounts(penalty_fee, balance, currency, creation_date, secret_key, status, primary_owner_id)
values (20, 200, 'USD', '2022-1-1', '666', 'ACTIVE', 1);

-- Jun super user (9090)
insert into credentials (secret_pass, username)
values ('$2a$10$oSjx6ha9qJ2m4YUMzpITb.2XrJdWIa5fSsG3saURpi6EENJjCHY6i', 'jun90');
insert into roles (name, credentials_id)
values ('USER', 2),
	('ADMIN', 2),
    ('THIRD', 2),
    ('SUPER', 2);


drop database final_project;

select * from account_holders;
select * from user;
select * from credentials;
select * from roles;
