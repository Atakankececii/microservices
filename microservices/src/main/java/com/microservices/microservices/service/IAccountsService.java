package com.microservices.microservices.service;

import com.microservices.microservices.dto.CustomerDto;

public interface IAccountsService {


    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccountDetails(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
