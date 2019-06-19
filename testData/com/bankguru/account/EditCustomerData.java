package com.bankguru.account;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EditCustomerData {
    public static EditCustomerData get(String fileName) throws JsonParseException, JsonMappingException, IOException {
	ObjectMapper mapper = new ObjectMapper();
	return mapper.readValue(new File(fileName), EditCustomerData.class);

    }

    @JsonProperty("address")
    private String address;

    @JsonProperty("city")
    private String city;

    @JsonProperty("state")
    private String state;

    @JsonProperty("pin")
    private String pin;

    @JsonProperty("phone")
    private String phone;

    public String getAddress() {
	return address;
    }

    public String getCity() {
	return city;
    }

    public String getState() {
	return state;
    }

    public String getPin() {
	return pin;
    }

    public String getNumber() {
	return phone;
    }

}
