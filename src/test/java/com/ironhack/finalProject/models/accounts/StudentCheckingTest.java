package com.ironhack.finalProject.models.accounts;

import com.ironhack.finalProject.models.users.AccountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class StudentCheckingTest {

    private StudentChecking studentChecking;

    @BeforeEach
    void setup(){
        studentChecking = new StudentChecking();
    }

    @Test
    void setPrimaryOwner_WorksFine() {
        AccountHolder owner = new AccountHolder();
        owner.setFullName("Antonio Mas Jr.");
        owner.setDateOfBirth(LocalDate.of(2000, 5, 1));
        studentChecking.setPrimaryOwner(owner);
        assertEquals("Antonio Mas Jr.", studentChecking.getPrimaryOwner().getFullName());
    }

    @Test
    void setPrimaryOwner_ThrowsExceptionIfOwnerIsMoreThan24(){
        AccountHolder owner = new AccountHolder();
        owner.setFullName("Antonio Mas");
        owner.setDateOfBirth(LocalDate.of(1980, 5, 1));
        assertThrows(IllegalArgumentException.class, () -> {
            studentChecking.setPrimaryOwner(owner);
        });
    }
}