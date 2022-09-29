package com.ironhack.finalProject.models.accounts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CreditCardTest {

    private CreditCard creditCard;

    @BeforeEach
    void setup(){
        creditCard = new CreditCard();
    }

    @Test
    void setCreditLimit_SetsMaxAndMinValuesWhenInputBeyondLimits() {
        BigDecimal aboveMax = new BigDecimal("100500");
        BigDecimal max = new BigDecimal("100000");
        BigDecimal min = new BigDecimal("100");
        BigDecimal belowMin = new BigDecimal("80");

        creditCard.setCreditLimit(max);
        assertEquals(max, creditCard.getCreditLimit());
        creditCard.setCreditLimit(aboveMax);
        assertEquals(max, creditCard.getCreditLimit());
        creditCard.setCreditLimit(min);
        assertEquals(min, creditCard.getCreditLimit());
        creditCard.setCreditLimit(belowMin);
        assertEquals(min, creditCard.getCreditLimit());
    }

    @Test
    void setInterestRate() {
        BigDecimal aboveMax = new BigDecimal("0.7");
        BigDecimal max = new BigDecimal("0.2");
        BigDecimal min = new BigDecimal("0.1");
        BigDecimal belowMin = new BigDecimal("0.001");

        creditCard.setInterestRate(max);
        assertEquals(max, creditCard.getInterestRate());
        creditCard.setInterestRate(aboveMax);
        assertEquals(max, creditCard.getInterestRate());
        creditCard.setInterestRate(min);
        assertEquals(min, creditCard.getInterestRate());
        creditCard.setInterestRate(belowMin);
        assertEquals(min, creditCard.getInterestRate());
    }

    @Test
    void addMonthlyInterest() {
    }

    @Test
    void isTimeToAddInterest() {
    }

    @Test
    void checkBalance() {
    }
}