# Banking System

## About

This is a simulated banking system built as the final project for the _Java for Backend Bootcamp_ run by Ironhack. 

Spring Boot application run with Java 18, Hibernate and MySQL.

## Table of contents

> * [About](#about)
> * [Installation](#installation)
> * [Structure](#structure)
>   * [UML diagram](#UML)
>   * [Class diagram](#Class-diagram)
>   * [Endpoints](#endpoints)
> * [Usage](#usage)
> * [Features](#features) > [List of actions to try](#functionality)
> * [Project requirements](#requirements)


## Installation

To run locally you need:

* Create a MySql Database called `bank`
* Remember to change your username and password for the DB in the file `src / main / resources / application.properties`
* Run the spring boot application once, so it can generate the necessary tables in the DB for you (runs in localhost:8080 by default)
* Stop the application
* Run the `bankData.sql` script in MySQL Workbench to populate your DB with temporary data
* Run the application again
* Use the attached POSTMAN collection to test endpoints

## Structure

### UML class diagram

![UML class diagram](Bank_UML_class_diagram.png)

### Endpoints

Check `bank_final_project.postman_collection.json` postman collection to try different endpoints

## Usage

Once you have run the sql script to populate your DB, you will be able to try most functionalities with the following credentials:

> `username: jun90` 
> `password: 9090`

**Other users:**

In `bankData.sql` script, you'll found the credentials for different kind of users.

### Features

This application allows you to:

* Authentication with Json Web Tokens
* Protected Routes
* Allows creations of accounts
* Automatic calculation of interests when adding money
* Automatic subtraction of penalty fee if transaction sets money below minimum threshold

#### Functionality

Here is a list of actions I had to demonstrate to my teachers:

>- [ ] System will be preloaded with the following data:
>  - [ ] An admin user
>  - [ ] An account holder 1
>    - older than 24 years old
>    - has a credit card
>    - has a checking account
>  - [ ] An account holder 2
>    - younger than 24 years old
>    - has a savings account
>  - [ ] A third party user
>- [ ] Using admin credentials, create a new checking account for account holder 2 to see how an error is prompted if you don't choose to create a student checking account. Clients younger than 24 years old can only open student checking accounts instead of normal checking.
>- [ ] As an admin, you can increase the account holder 1's checking account balance by 100 USD
>- [ ] Each account holder should be able to see their account information using their credentials
>  - [ ] Likewise, they shouldn't be able to access another clients account information
>- [ ] Add money to a new checking account with a creation date set a few months ago to see how monthly maintenance fees apply
>- [ ] Remove money from a checking account to go below the minimum balance threshold and see how penalty fees apply
>- [ ] ~~Un third party debe retirar 10 de una cuenta~~ not implemented
>- [ ] ~~You can update a credit card creation's date in order to see how interest ratings get applied and increase the balance~~ not implemented

- Apart from that you can: 
- POST
  - Create all 3 kinds of users.
  - Create all 4 kinds of accounts. You will have to use an admin's credentials.
- You can GET a list of all:
  - users
  - credentials
  - checking accounts
  - addresses
- GET You can access your own checking account information
- PUT You can add or subtract money from a checking account and see how interests or penalty fees apply

# Requirements

The requirements to pass this final project assignment were the following:

> ### 1. The system must have 4 types of accounts: StudentChecking, Checking, Savings, and CreditCard.

**Checking**

Checking Accounts should have:

* A balance
* A secretKey
* A PrimaryOwner
* An optional SecondaryOwner
* A minimumBalance
* A penaltyFee
* A monthlyMaintenanceFee
* A creationDate
* A status (FROZEN, ACTIVE)

**StudentChecking**

Student Checking Accounts are identical to Checking Accounts except that they do NOT have:

* A monthlyMaintenanceFee
* A minimumBalance

**Savings**

Savings are identical to Checking accounts except that they

* Do NOT have a monthlyMaintenanceFee
* Do have an interestRate

**CreditCard**

CreditCard Accounts have:

* A balance
* A PrimaryOwner
* An optional SecondaryOwner
* A creditLimit
* An interestRate
* A penaltyFee

> ### 2. The system must have 3 types of Users: Admins, AccountHolders and Third Parties.

**AccountHolders**

The AccountHolders should be able to access their own accounts and only their accounts when passing the correct credentials using Basic Auth. AccountHolders have:

* A name
* Date of birth
* A primaryAddress (which should be a separate address class)
* An optional mailingAddress

**Admins**

* Admins only have a name

**ThirdParty**

* The ThirdParty Accounts have a hashed key and a name.

> ### 3. Admins can create new accounts. When creating a new account they can create Checking, Savings, or CreditCard Accounts.
**Savings**

* Savings accounts have a default interest rate of 0.0025
* Savings accounts may be instantiated with an interest rate other than the default, with a maximum interest rate of 0.5
* Savings accounts should have a default minimumBalance of 1000
* Savings accounts may be instantiated with a minimum balance of less than 1000 but no lower than 100

**CreditCards**

* CreditCard accounts have a default creditLimit of 100
* CreditCards may be instantiated with a creditLimit higher than 100 but not higher than 100000
* CreditCards have a default interestRate of 0.2
* CreditCards may be instantiated with an interestRate less than 0.2 but not lower than 0.1

**CheckingAccounts**


* When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account should be created otherwise a regular Checking Account should be created.
* Checking accounts should have a minimumBalance of 250 and a monthlyMaintenanceFee of 12

> ### 4. Interest and Fees should be applied appropriately

**PenaltyFee**

* The penaltyFee for all accounts should be 40.
* If any account drops below the minimumBalance, the penaltyFee should be deducted from the balance automatically

**InterestRate**

* Interest on savings accounts is added to the account annually at the rate of specified interestRate per year. That means that if I have 1000000 in a savings account with a 0.01 interest rate, 1% of 1 Million is added to my account after 1 year. When a savings account balance is accessed, you must determine if it has been 1 year or more since either the account was created or since interest was added to the account, and add the appropriate interest to the balance if necessary.
* Interest on credit cards is added to the balance monthly. If you have a 12% interest rate (0.12) then 1% interest will be added to the account monthly. When the balance of a credit card is accessed, check to determine if it has been 1 month or more since the account was created or since interested was added, and if so, add the appropriate interest to the balance.

> ### 5. Account Access

**Admins**

* Admins should be able to access the balance for any account and to modify it.

**AccountHolders**

* AccountHolders should be able to access their own account balance
* Account holders should be able to transfer money from any of their accounts to any other account (regardless of owner). The transfer should only be processed if the account has sufficient funds. The user must provide the Primary or Secondary owner name and the id of the account that should receive the transfer.

**Third-Party Users**

* There must be a way for third-party users to receive and send money to other accounts.
* Third-party users must be added to the database by an admin.
* In order to receive and send money, Third-Party Users must provide their hashed key in the header of the HTTP request. They also must provide the amount, the Account id and the account secret key.