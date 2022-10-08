package com.ironhack.finalProject.config.security;

import com.ironhack.finalProject.DTO.RegistrationUserDTO;
import com.ironhack.finalProject.models.users.AccountHolder;
import com.ironhack.finalProject.models.users.Admin;
import com.ironhack.finalProject.models.users.ThirdParty;
import com.ironhack.finalProject.models.users.User;
import com.ironhack.finalProject.repositories.AddressRepository;
import com.ironhack.finalProject.repositories.users.AccountHolderRepository;
import com.ironhack.finalProject.repositories.users.AdminRepository;
import com.ironhack.finalProject.repositories.users.ThirdPartyRepository;
import com.ironhack.finalProject.repositories.users.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RegistrationServiceImpTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CredentialsRepository credentialsRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;
    @Autowired
    private AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private RegistrationServiceImp registrationServiceImp;

    private RegistrationUserDTO goodDTO;
    private RegistrationUserDTO wrongDTO;
    private User newUser;

    @BeforeEach
    void setUp() {
        goodDTO = new RegistrationUserDTO();
        wrongDTO = new RegistrationUserDTO();
    }

    @AfterEach
    void tearDown() {
        if (newUser instanceof Admin){
            adminRepository.delete((Admin) newUser);
        }
        if (newUser instanceof AccountHolder){
            accountHolderRepository.delete((AccountHolder) newUser);
        }
        if (newUser instanceof ThirdParty){
            thirdPartyRepository.delete((ThirdParty) newUser);
        }
    }

    @Test
    void addNewUser_Admin() {
        goodDTO.setUserType(1);
        goodDTO.setFullName("Virginia Despentes");
        goodDTO.setUsername("VirKong1");
        goodDTO.setSecretPass("kingkong");
        ArrayList<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        roles.add("USER");
        roles.add("THIRD");
        goodDTO.setRoles(roles);
        newUser = registrationServiceImp.addNewUser(goodDTO);
        assertEquals("Virginia Despentes", newUser.getFullName());
        assertEquals("VirKong1", newUser.getCredentials().getUsername());
    }
    // check one user of a kind is created x3
    // throw errors if information is missing
}