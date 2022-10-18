package com.ironhack.finalProject.services.accounts;

import com.ironhack.finalProject.DTO.AccountDTO;
import com.ironhack.finalProject.config.security.CredentialsRepository;
import com.ironhack.finalProject.models.Money;
import com.ironhack.finalProject.models.accounts.*;
import com.ironhack.finalProject.models.users.AccountHolder;
import com.ironhack.finalProject.repositories.accounts.CheckingRepository;
import com.ironhack.finalProject.repositories.accounts.CreditCardRepository;
import com.ironhack.finalProject.repositories.accounts.SavingsRepository;
import com.ironhack.finalProject.repositories.accounts.StudentCheckingRepository;
import com.ironhack.finalProject.repositories.users.AccountHolderRepository;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Currency;
@Service
public class AccountServiceImp implements AccountService{
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private StudentCheckingRepository studentCheckingRepository;
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Account createNewAccount(AccountDTO dto) {
        if (dto.getAccountType() == null){
            throw new IllegalArgumentException("Account type not specified");
        }
        Account newAccount;
        switch (dto.getAccountType()){
            case CHECKING -> newAccount = createChecking(dto);
            case STUDENT_CHECKING -> newAccount = createStudent(dto);
            case CREDIT_CARD -> newAccount = createCard(dto);
            case SAVINGS -> newAccount = createSavings(dto);
            default -> throw new IllegalArgumentException("Account type not correct");
        }
        return newAccount;
    }

    private Savings createSavings(AccountDTO dto) {
        Savings newSavings = new Savings();
        Money money = new Money(dto.getBalance(), Currency.getInstance(dto.getCurrency()));
        newSavings.setMoney(money);
        // owners
        AccountHolder a1 = accountHolderRepository.findById(dto.getPrimaryOwnerId()).orElseThrow();
        newSavings.setPrimaryOwner(a1);
        if (dto.getSecondaryOwnerId() > 0){
            AccountHolder a2 = accountHolderRepository.findById(dto.getSecondaryOwnerId()).orElseThrow();
            newSavings.setSecondaryOwner(a2);
        }
        //min bal interest
        newSavings.setMinimumBalance(dto.getMinimumBalance());
        newSavings.setInterestRate(dto.getInterestRate());

        // others
        newSavings.setSecretKey(passwordEncoder.encode(dto.getRawSecretKey()));
        newSavings.setCreationDate(LocalDate.parse(dto.getCreationDate()));
        newSavings.setStatus(dto.getAccountStatus());
        return savingsRepository.save(newSavings);
    }

    private CreditCard createCard(AccountDTO dto) {
        CreditCard newCard = new CreditCard();
        Money money = new Money(dto.getBalance(), Currency.getInstance(dto.getCurrency()));
        newCard.setMoney(money);
        // owners
        AccountHolder a1 = accountHolderRepository.findById(dto.getPrimaryOwnerId()).orElseThrow();
        newCard.setPrimaryOwner(a1);
        if (dto.getSecondaryOwnerId() > 0){
            AccountHolder a2 = accountHolderRepository.findById(dto.getSecondaryOwnerId()).orElseThrow();
            newCard.setSecondaryOwner(a2);
        }
        newCard.setCreditLimit(dto.getCreditLimit());
        newCard.setInterestRate(dto.getInterestRate());
        return creditCardRepository.save(newCard);
    }

    private StudentChecking createStudent(AccountDTO dto) {
        StudentChecking newStudent = new StudentChecking();
        Money money = new Money(dto.getBalance(), Currency.getInstance(dto.getCurrency()));
        newStudent.setMoney(money);
        // owners
        AccountHolder a1 = accountHolderRepository.findById(dto.getPrimaryOwnerId()).orElseThrow();
        newStudent.setPrimaryOwner(a1);
        if (dto.getSecondaryOwnerId() > 0){
            AccountHolder a2 = accountHolderRepository.findById(dto.getSecondaryOwnerId()).orElseThrow();
            newStudent.setSecondaryOwner(a2);
        }
        // others
        newStudent.setSecretKey(passwordEncoder.encode(dto.getRawSecretKey()));
        newStudent.setCreationDate(LocalDate.parse(dto.getCreationDate()));
        newStudent.setStatus(dto.getAccountStatus());
        return studentCheckingRepository.save(newStudent);
    }

    private Checking createChecking(AccountDTO dto) {
        Checking newChecking = new Checking();
        Money money = new Money(dto.getBalance(), Currency.getInstance(dto.getCurrency()));
        newChecking.setMoney(money);
        // owners
        AccountHolder a1 = accountHolderRepository.findById(dto.getPrimaryOwnerId()).orElseThrow();
        newChecking.setPrimaryOwner(a1);
        if (dto.getSecondaryOwnerId() > 0){
            AccountHolder a2 = accountHolderRepository.findById(dto.getSecondaryOwnerId()).orElseThrow();
            newChecking.setSecondaryOwner(a2);
        }
        // others
        newChecking.setSecretKey(passwordEncoder.encode(dto.getRawSecretKey()));
        newChecking.setCreationDate(LocalDate.parse(dto.getCreationDate()));
        newChecking.setStatus(dto.getAccountStatus());
        return checkingRepository.save(newChecking);
    }
}
