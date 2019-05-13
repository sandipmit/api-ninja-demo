package com.two57.demo.apininja.service;

import com.two57.demo.apininja.model.Customer;
import com.two57.demo.apininja.repository.CustomerRepository;
import com.two57.demo.apininja.repository.rsql.CustomRsqlVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class CustomerService {
    private final CustomerRepository customerRepo;

    @Transactional(readOnly = true)
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Customer> findById(Long id) {
        return customerRepo.findById(id);
    }

    public Customer save(Customer stock) {
        return customerRepo.save(stock);
    }

    public void deleteById(Long id) {
        customerRepo.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Customer> search(String filter) {
        final Node rootNode = new RSQLParser().parse(filter);
        Specification<Customer> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return customerRepo.findAll(spec);
    }
}
