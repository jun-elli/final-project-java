package com.ironhack.finalProject.controllers;

import com.ironhack.finalProject.models.Address;
import com.ironhack.finalProject.services.AddressService;
import com.ironhack.finalProject.services.AddressServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @GetMapping("/address")
    public List<Address> getAllAddress(){
        return addressService.listAllAddress();
    }
}
