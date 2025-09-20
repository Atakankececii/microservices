package com.microservices.microservices.accounts.service;

import com.microservices.microservices.accounts.dto.CustomerDto;

public interface IAccountsService {


    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccountDetails(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
