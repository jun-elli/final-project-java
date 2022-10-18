package com.ironhack.finalProject.DTO;

import com.ironhack.finalProject.models.enums.BalanceOperation;

import java.math.BigDecimal;

public class BalanceDTO {
    private BalanceOperation operation;
    private BigDecimal amount;
    private String currency;

    public BalanceOperation getOperation() {
        return operation;
    }

    public void setOperation(BalanceOperation operation) {
        this.operation = operation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
