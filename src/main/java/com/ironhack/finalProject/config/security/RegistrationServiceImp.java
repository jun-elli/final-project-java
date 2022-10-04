package com.ironhack.finalProject.config.security;

import com.ironhack.finalProject.DTO.RegistrationUserDTO;
import com.ironhack.finalProject.config.CustomUserDetails;
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
import com.ironhack.finalProject.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RegistrationServiceImp implements RegistrationService{
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

    @Override
    public User addNewUser(RegistrationUserDTO userDTO) {
        // registro les credencials
        Credentials credentials = new Credentials();
        credentials.setUsername(userDTO.getUsername());
        credentials.setSecretPass(passwordEncoder.encode(userDTO.getSecretPass()));
        Credentials savedCredentials = credentialsRepository.save(credentials);
        // registro els rols
        for (String role : userDTO.getRoles()) {
            Role r = new Role();
            r.setName(role);
            r.setCredentials(savedCredentials);
            roleRepository.save(r);
        }
        // agafo DTO i miro quin tipus de user estic rebent
        User newUser;
        switch (userDTO.getUserType()) {
            case 1 -> {
                Admin a = createAdmin(userDTO, savedCredentials);
                newUser = adminRepository.save(a);
            }
            case 2 -> {
                AccountHolder h = createHolder(userDTO, savedCredentials);
                newUser = accountHolderRepository.save(h);
            }
            case 3 -> {
                ThirdParty t = createThirdParty(userDTO, savedCredentials);
                newUser = thirdPartyRepository.save(t);
            }
            default -> throw new IllegalArgumentException("User type not found.");
        };
        return newUser;
    }

    private ThirdParty createThirdParty(RegistrationUserDTO userDTO, Credentials savedCredentials) {
        return new ThirdParty(userDTO.getFullName(), savedCredentials, passwordEncoder.encode(userDTO.getHashedKey()));
    }

    private AccountHolder createHolder(RegistrationUserDTO userDTO, Credentials savedCredentials) {
        AccountHolder newHolder = new AccountHolder();
        newHolder.setFullName(userDTO.getFullName());
        newHolder.setCredentials(savedCredentials);
        newHolder.setDateOfBirth(userDTO.getDateOfBirth());
        // save address
        Address a1 = addNewSavedAddress(userDTO.getPrimaryAddress());
        newHolder.setPrimaryAddress(a1);
        // check if there's a secondary address
        if (userDTO.getOptionalAddress() != null){
            Address a2 = addNewSavedAddress(userDTO.getOptionalAddress());
            newHolder.setOptionalAddress(a2);
        }
        return newHolder;
    }

    private Address addNewSavedAddress(Address address) {
        Address newAddress = new Address(address.getStreet(), address.getAdditionalAddress(), address.getCity(), address.getCountry(), address.getPostalCode());
        return addressRepository.save(newAddress);
    }

    private Admin createAdmin(RegistrationUserDTO userDTO, Credentials savedCredentials) {
        Admin newAdmin = new Admin();
        newAdmin.setFullName(userDTO.getFullName());
        newAdmin.setCredentials(savedCredentials);
        return newAdmin;
    }
}
