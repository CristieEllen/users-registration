package br.com.project.register.controllers;

import br.com.project.register.dto.request.CustomerRequestDto;
import br.com.project.register.dto.request.UpdateCustomerDto;
import br.com.project.register.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public Page<br.com.project.register.dto.response.CustomerDto> findAllCustomer(Pageable pageable){
        return service.findAllCustomer(pageable);
    }

    @GetMapping(value = "/{id}")
    public br.com.project.register.dto.response.CustomerDto findByIdCustomer(@PathVariable Long id){
        return service.findByIdCustomer(id);
    }

    @PostMapping
    public ResponseEntity<br.com.project.register.dto.response.CustomerDto> createCustomer(@RequestBody @Valid CustomerRequestDto customerForm, UriComponentsBuilder uriBuilder){
        return service.createCustomer(customerForm, uriBuilder);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity removeCustomer(@PathVariable Long id) {
        return service.removeCustomer(id);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<br.com.project.register.dto.response.CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody @Valid UpdateCustomerDto updateCustomerForm){
        return service.updateCustomer(id, updateCustomerForm);
    }


}
