package com.ironhack.finalProject.services;

import com.ironhack.finalProject.models.Address;
import com.ironhack.finalProject.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImp implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> listAllAddress() {
        return addressRepository.findAll();
    }
}
