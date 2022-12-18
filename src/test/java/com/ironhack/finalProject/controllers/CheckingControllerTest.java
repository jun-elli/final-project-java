package com.ironhack.finalProject.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.finalProject.DTO.RegistrationUserDTO;
import com.ironhack.finalProject.config.security.RegistrationServiceImp;
import com.ironhack.finalProject.models.Address;
import com.ironhack.finalProject.models.Money;
import com.ironhack.finalProject.models.accounts.Checking;
import com.ironhack.finalProject.models.enums.AccountStatus;
import com.ironhack.finalProject.models.users.AccountHolder;
import com.ironhack.finalProject.repositories.accounts.CheckingRepository;
import com.ironhack.finalProject.repositories.users.AccountHolderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//
//@SpringBootTest
//class CheckingControllerTest {
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Autowired
//    private CheckingRepository checkingRepository;
//    @Autowired
//    private AccountHolderRepository accountHolderRepository;
//    @Autowired
//    private RegistrationServiceImp registrationServiceImp;
//
//    private MockMvc mockMvc;
//    private final ObjectMapper objectMapper = new ObjectMapper();
//    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    // Create accounts and owners
//    Checking c1 = new Checking();
//    AccountHolder owner1;
//    RegistrationUserDTO owner1DTO = new RegistrationUserDTO();
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//
//        // Owner 1
//        owner1DTO.setUserType(2);
//        owner1DTO.setFullName("Brigitte Vasallo");
//        owner1DTO.setUsername("LaVasallo");
//        owner1DTO.setSecretPass("biva3");
//        ArrayList<String> roles = new ArrayList<>();
//        roles.add("USER");
//        owner1DTO.setRoles(roles);
//        owner1DTO.setDateOfBirth(LocalDate.of(1984, 1, 20));
//        Address a = new Address("plaça de Roma","30, 2a", "Barcelona", "Espanya","08046");
//        owner1DTO.setPrimaryAddress(a);
//        owner1 = (AccountHolder) registrationServiceImp.addNewUser(owner1DTO);
//
//
//        // Checking account 1
//        // Owner
//        c1.setPrimaryOwner(owner1);
//        // Money
//        c1.setMoney(new Money(new BigDecimal("1000"), Currency.getInstance("USD")));
//        // Secret Key String
//        c1.setSecretKey(passwordEncoder.encode("987123"));
//        // CreationDate LocalDate
//        LocalDate creationDate = LocalDate.of(2019,4,22);
//        c1.setCreationDate(creationDate);
//        // AccountStatus status
//        c1.setStatus(AccountStatus.ACTIVE);
//        checkingRepository.save(c1);
//
//    }
//
//    @AfterEach
//    void tearDown() {
//        // delete from both repos
//        //accountHolderRepository.delete(owner1);
//        checkingRepository.delete(c1);
//    }
//
//    @Test
//    void getAllCheckings() throws Exception{
//        MvcResult mvcResult = mockMvc.perform(get("/checkings"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andReturn();
//        List<Checking> checkings = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Checking>>() {});
//
//        assertEquals(2, checkings.size());
//    }
//
//    @Test
//    void getMyCheckingAccount() {
//    }
//
//    @Test
//    void changeBalanceChecking() {
//    }
//}