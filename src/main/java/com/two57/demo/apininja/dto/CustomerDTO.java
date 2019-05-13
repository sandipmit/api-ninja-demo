package com.two57.demo.apininja.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {
    private long id;
    private String companyName;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String primaryEmail;
    private String secondaryEmail;
    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private String country;
    private String postalCode;
}
