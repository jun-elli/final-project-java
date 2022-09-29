package com.ironhack.finalProject.config.security;

import com.ironhack.finalProject.config.CustomUserDetails;
import com.ironhack.finalProject.models.users.User;
import com.ironhack.finalProject.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user =  userRepository.findByUsername(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUser(user.get());

        return userDetails;
        //return null;

    }
}
