package com.ironhack.finalProject.models.accounts;

import com.ironhack.finalProject.models.Address;
import com.ironhack.finalProject.models.Money;
import com.ironhack.finalProject.models.users.AccountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.datetime.joda.LocalDateTimeParser;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AccountTest {

    private CreditCard creditCard;


    @BeforeEach
    void setUp() {
        Money money = new Money(new BigDecimal("150"));
        LocalDate dateOfBirth = LocalDate.of(1980, 9, 22);
        Address address = new Address("Carrer del Croissant", "23, 2n 3a", "Barcelona", "Spain", "08001");
        AccountHolder primaryOwner = new AccountHolder("Marie Bront", "marie1", "1234", dateOfBirth, address, null);
        creditCard = new CreditCard(money, primaryOwner,null, new BigDecimal("200"), new BigDecimal("0.02"));
    }

    @Test
    void setMoney_Works() {
        Money newMoney = new Money(new BigDecimal("200"));
        creditCard.setMoney(newMoney);
        assertEquals(newMoney, creditCard.getMoney());
        assertEquals(newMoney.getAmount(), creditCard.getBalance());
    }

    @Test
    void setMoney_ThrowsExceptionWhenDifferentCurrency() {
        assertThrows(IllegalArgumentException.class, () -> {
            creditCard.setMoney(new Money(new BigDecimal("200"), Currency.getInstance("EUR")));
        });
    }
}