package com.ironhack.finalProject.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.finalProject.models.Address;
import com.ironhack.finalProject.repositories.AddressRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AddressControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    AddressRepository addressRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private int numberOfAddresses;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        // check how many addresses are there
        numberOfAddresses = addressRepository.findAll().size();
    }

    @Test
    void getAllAddress() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        List<Address> addresses = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<List<Address>>() {});

        assertEquals(numberOfAddresses, addresses.size());

    }
}