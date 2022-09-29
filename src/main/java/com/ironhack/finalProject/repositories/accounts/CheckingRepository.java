package com.ironhack.finalProject.repositories.accounts;

import com.ironhack.finalProject.models.accounts.Checking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckingRepository extends JpaRepository<Checking, Integer> {
}
