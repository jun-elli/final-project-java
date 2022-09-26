package com.ironhack.finalProject.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    //autowire user repo

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        /*Optional<User> user =  userRepository.findByUsername(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        CustomUserDetails userDetails = new CustomUserDetails();
        userDetails.setUser(user.get());

        return userDetails;*/
        return null;

    }
}
