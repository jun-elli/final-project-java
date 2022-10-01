package com.ironhack.finalProject.config.security;

import java.util.List;

public interface CredentialsService {
    List<Credentials> listAllCredentials();
    Credentials addNewCredentials(Credentials credentials);
}
