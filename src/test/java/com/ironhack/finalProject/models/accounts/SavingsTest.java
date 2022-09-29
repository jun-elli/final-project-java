package com.ironhack.finalProject.models.accounts;

import com.ironhack.finalProject.models.Money;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
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
    }

    @Test
    void addYearlyInterest() {
    }

    @Test
    void isTimeToAddInterest() {
    }

    @Test
    void checkBalance() {
    }
}