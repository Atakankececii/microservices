package com.microservices.microservices.loans.service;

import com.microservices.microservices.loans.dto.LoansDto;

public interface ILoansService {


    void createLoan(String mobileNumber);


    LoansDto fetchLoan(String mobileNumber);


    boolean updateLoan(LoansDto loansDto);


    boolean deleteLoan(String mobileNumber);
}
