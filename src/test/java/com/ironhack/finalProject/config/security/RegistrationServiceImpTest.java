package com.ironhack.finalProject.config.security;

import com.ironhack.finalProject.DTO.RegistrationUserDTO;
import com.ironhack.finalProject.models.Address;
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

import java.time.LocalDate;
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
    @Test
    void addNewUser_Holder() {
        goodDTO.setUserType(2);
        goodDTO.setFullName("Brigitte Vasallo");
        goodDTO.setUsername("LaVasallo");
        goodDTO.setSecretPass("biva3");
        ArrayList<String> roles = new ArrayList<>();
        roles.add("USER");
        goodDTO.setRoles(roles);
        goodDTO.setDateOfBirth(LocalDate.of(1984, 1, 20));
        Address a = new Address("plaça de Roma","30, 2a", "Barcelona", "Espanya","08046");
        /*Address a2 = addressRepository.save(a);*/
        goodDTO.setPrimaryAddress(a);
        newUser = registrationServiceImp.addNewUser(goodDTO);
        //
        assertEquals("Brigitte Vasallo", newUser.getFullName());
        assertEquals("LaVasallo", newUser.getCredentials().getUsername());
        AccountHolder ah = (AccountHolder) newUser;
        assertEquals(1, ah.getDateOfBirth().getMonthValue());
        assertEquals("plaça de Roma", ah.getPrimaryAddress().getStreet());
    }

    @Test
    void addNewUser_Third(){
        goodDTO.setUserType(3);
        goodDTO.setFullName("Chizuko Ueno");
        goodDTO.setUsername("dra-ueno9");
        goodDTO.setSecretPass("123098");
        ArrayList<String> roles = new ArrayList<>();
        roles.add("THIRD");
        goodDTO.setRoles(roles);
        goodDTO.setHashedKey("123-456-789");
        newUser = registrationServiceImp.addNewUser(goodDTO);
        assertEquals("Chizuko Ueno", newUser.getFullName());
        assertEquals("dra-ueno9", newUser.getCredentials().getUsername());
    }
    // throw errors if information is missing
    @Test
    void addNewUser_ThrowExceptionsIfInformationMissing(){
        // No credentials info
        IllegalArgumentException exception;
        exception = assertThrows(IllegalArgumentException.class, () -> {
            newUser = registrationServiceImp.addNewUser(wrongDTO);
        });
        assertEquals("Credentials not found", exception.getMessage());

        // No roles info
        wrongDTO.setUsername("BobManos");
        wrongDTO.setSecretPass("manitas33");
        exception = assertThrows(IllegalArgumentException.class, () -> {
            newUser = registrationServiceImp.addNewUser(wrongDTO);
        });
        assertEquals("Roles not found", exception.getMessage());

        // No user type
        ArrayList<String> roles = new ArrayList<>();
        roles.add("THIRD");
        wrongDTO.setRoles(roles);
        exception = assertThrows(IllegalArgumentException.class, () -> {
            newUser = registrationServiceImp.addNewUser(wrongDTO);
        });
        assertEquals("User type not found.", exception.getMessage());

        //No Admin info
        wrongDTO.setUserType(1);
        exception = assertThrows(IllegalArgumentException.class, () -> {
            newUser = registrationServiceImp.addNewUser(wrongDTO);
        });
        assertEquals("Admin information not found.", exception.getMessage());

        // No Third info
        wrongDTO.setUserType(3);
        exception = assertThrows(IllegalArgumentException.class, () -> {
            newUser = registrationServiceImp.addNewUser(wrongDTO);
        });
        assertEquals("Third party information not found.", exception.getMessage());

        // No Account holder info
        wrongDTO.setUserType(2);
        exception = assertThrows(IllegalArgumentException.class, () -> {
            newUser = registrationServiceImp.addNewUser(wrongDTO);
        });
        assertEquals("Account holder information not found.", exception.getMessage());
    }
}