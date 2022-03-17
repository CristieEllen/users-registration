package br.com.project.register.controllers;

import br.com.project.register.dto.CustomerDto;
import br.com.project.register.forms.CustomerForm;
import br.com.project.register.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl service;

    @GetMapping
    public Page<CustomerDto> findAllCustomer(Pageable pageable){
        return service.findAllCustomer(pageable);
    }

    @GetMapping(value = "/{id}")
    public CustomerDto findByIdCustomer(@PathVariable Long id){
        return service.findByIdCustomer(id);
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerForm customerForm, UriComponentsBuilder uriBuilder){
        return service.createCustomer(customerForm, uriBuilder);
    }

}
