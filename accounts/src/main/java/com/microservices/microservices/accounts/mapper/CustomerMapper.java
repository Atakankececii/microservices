package com.microservices.microservices.accounts.mapper;


import com.microservices.microservices.accounts.dto.CustomerDto;
import com.microservices.microservices.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto){
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setMobileNumber(String.valueOf(customer.getMobileNumber()));
        return customerDto;
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer){
        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setMobileNumber(String.valueOf(customerDto.getMobileNumber()));
        return customer;
    }
}
