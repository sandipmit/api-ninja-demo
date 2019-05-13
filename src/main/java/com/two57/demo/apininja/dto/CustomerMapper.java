package com.two57.demo.apininja.dto;

import com.two57.demo.apininja.model.Customer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
@Component
public interface CustomerMapper {
    CustomerDTO toCustomerDTO(Customer customer);

    List<CustomerDTO> toCustomerDTOs(List<Customer> customers);

    Customer toCustomer(CustomerDTO customerDTO);

//
//    AddressDTO toAddressDTO(Address address);
//
//    Address toAddress(AddressDTO addressDTO);

}
