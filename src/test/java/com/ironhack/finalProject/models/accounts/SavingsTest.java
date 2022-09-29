package com.ironhack.finalProject.models.accounts;

import com.ironhack.finalProject.models.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SavingsTest {

    private Savings savings;

    @BeforeEach
    void setup(){
        savings = new Savings();
        savings.setMoney(new Money(new BigDecimal("1000"), Currency.getInstance("USD")));
        BigDecimal myInterest = new BigDecimal("0.1");
        savings.setInterestRate(myInterest);
        BigDecimal minBalance = new BigDecimal("200");
        savings.setMinimumBalance(minBalance);
    }

    @Test
    void setMinimumBalance_SetsMaxAndMinValuesWhenInputBeyondLimits() {
        BigDecimal aboveMax = new BigDecimal("2000");
        BigDecimal max = new BigDecimal("1000");
        BigDecimal min = new BigDecimal("100");
        BigDecimal belowMin = new BigDecimal("80");

        savings.setMinimumBalance(max);
        assertEquals(max, savings.getMinimumBalance());
        savings.setMinimumBalance(aboveMax);
        assertEquals(max, savings.getMinimumBalance());
        savings.setMinimumBalance(min);
        assertEquals(min, savings.getMinimumBalance());
        savings.setMinimumBalance(belowMin);
        assertEquals(min, savings.getMinimumBalance());
    }

    @Test
    void setInterestRate() {
        BigDecimal aboveMax = new BigDecimal("0.80");
        BigDecimal max = new BigDecimal("0.5");
        BigDecimal min = new BigDecimal("0");
        BigDecimal belowMin = new BigDecimal("-0.2");

        savings.setInterestRate(max);
        assertEquals(max, savings.getInterestRate());
        savings.setInterestRate(aboveMax);
        assertEquals(max, savings.getInterestRate());
        savings.setInterestRate(min);
        assertEquals(min, savings.getInterestRate());
        savings.setInterestRate(belowMin);
        assertEquals(min, savings.getInterestRate());

    }

    @Test
    void addYearlyInterest() {
        savings.addYearlyInterest();
        assertEquals(new BigDecimal("1100.00"), savings.getBalance());
        assertEquals(LocalDate.now().getDayOfMonth(), savings.getWhenLastInterestWasAdded().getDayOfMonth());
    }

    @Test
    void isTimeToAddInterest() {
        //False if less than a year
        savings.setWhenLastInterestWasAdded(LocalDate.of(2022, 9, 13));
        assertFalse(savings.isTimeToAddInterest());
        //true if more than a year
        savings.setWhenLastInterestWasAdded(LocalDate.of(2021, 8, 13));
        assertTrue(savings.isTimeToAddInterest());
    }

    @Test
    void checkBalance() {
        // if less than a year, balance should be 1000.00
        savings.setWhenLastInterestWasAdded(LocalDate.of(2022, 9, 13));
        assertEquals(new BigDecimal("1000.00"), savings.checkBalance());

        //if time is more than a year, add new interest and return balance should be 1010.00
        savings.setWhenLastInterestWasAdded(LocalDate.of(2021, 8, 13));
        assertEquals(new BigDecimal("1100.00"), savings.checkBalance());
    }
}