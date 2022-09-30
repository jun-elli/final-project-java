create DATABASE final_project;
use final_project;

insert into address(street, additional_address, city, country, postal_code) 
VALUes ('carrer del Croissant', 'entresol 4', 'Barcelona', 'Spain', '08001');

insert into account_holders(full_name, secret_pass, username, date_of_birth, primary_address_id)
values ('Marie Boulangerie', '987', 'marie9', '1990-9-9', 1);

insert into checking_accounts(penalty_fee, balance, currency, creation_date, secret_key, status, primary_owner_id)
values (20, 200, 'USD', '2022-1-1', '666', 'ACTIVE', 1);

drop database final_project;

select * from account_holders;