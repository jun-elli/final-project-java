package com.ironhack.finalProject;

import com.ironhack.finalProject.models.Money;
import com.ironhack.finalProject.models.accounts.Checking;
import com.ironhack.finalProject.models.enums.AccountStatus;
import com.ironhack.finalProject.models.users.AccountHolder;
import com.ironhack.finalProject.repositories.accounts.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@SpringBootApplication
public class FinalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalProjectApplication.class, args);
	}

	/*public static void populateDB(){
		//Money
		Money money = new Money(new BigDecimal("200"), Currency.getInstance("USD"));
		//Primary Owner
		AccountHolder owner = new AccountHolder();
			//Address?
		//secondary null
		//secretkey
		//creationdate
		LocalDate creationDate = LocalDate.of(2021, 1, 1);
		//status

		Checking checking = new Checking(money,owner, null, "123", creationDate, AccountStatus.DISABLED);

		checkingRepository.save(checking);
	}*/

}
