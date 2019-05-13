package com.two57.demo.apininja.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "contacts")

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 80)
    private String companyName;

    @Column(length = 80)
    @NotNull
    @Valid
    @Size(min = 1, max = 80)
    private String firstName;

    @Column(length = 80)
    private String lastName;

    @Column(length = 20)
    private String phoneNumber;

    @Column(nullable = false, length = 80)
    private String primaryEmail;

    @Column(length = 80)
    private String secondaryEmail;

    @Column(nullable = false)
    private String address1;

    @Column
    private String address2;

    @Column
    private String address3;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String postalCode;
}
