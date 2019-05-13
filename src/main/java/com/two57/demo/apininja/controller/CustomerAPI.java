package com.two57.demo.apininja.controller;

import com.two57.demo.apininja.dto.CustomerDTO;
import com.two57.demo.apininja.dto.CustomerMapper;
import com.two57.demo.apininja.model.Customer;
import com.two57.demo.apininja.service.CustomerService;
import com.two57.demo.apininja.utils.FieldsUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerAPI {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> findAll(@RequestParam(value = "filter", required = false) String filter, @RequestParam(value = "fields", required = false) final String fields) {
        final List<Customer> customers = filter != null ? customerService.search(filter) : customerService.findAll();
        customers.stream().forEach(c -> selectFields(c, fields));
        return ResponseEntity.ok(customerMapper.toCustomerDTOs(customers));
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDTO) {
        Customer cust = customerMapper.toCustomer(customerDTO);
        customerService.save(cust);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id, @RequestParam(value = "fields", required = false) String fields) {
        Optional<Customer> customerO = customerService.findById(id);

        customerO.ifPresent(c -> selectFields(c, fields));
        return ResponseEntity.ok(customerMapper.toCustomerDTO(customerO.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerMapper.toCustomer(customerDTO);
        customer.setId(id);

        customerService.save(customer);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        customerService.deleteById(id);

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    private <T> void selectFields(T source, final String fields) {
        FieldsUtil.setFields(source, getFields(fields));
    }
    private List<String> getFields(final String fields) {
        return StringUtils.isEmpty(fields) ? Collections.emptyList() : Arrays.asList(fields.split("\\,"));
    }
}