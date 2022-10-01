package com.ironhack.finalProject.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsServiceImp implements CredentialsService{
    @Autowired
    private CredentialsRepository credentialsRepository;

    @Override
    public List<Credentials> listAllCredentials() {
        return credentialsRepository.findAll();
    }

    @Override
    public Credentials addNewCredentials(Credentials credentials) {
        return null;
    }
}
