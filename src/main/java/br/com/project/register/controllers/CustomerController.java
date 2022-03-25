package br.com.project.register.controllers;

import br.com.project.register.dto.CustomerDto;
import br.com.project.register.forms.AddressForm;
import br.com.project.register.forms.CustomerForm;
import br.com.project.register.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

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
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid CustomerForm customerForm, UriComponentsBuilder uriBuilder){
        return service.createCustomer(customerForm, uriBuilder);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity remove(@PathVariable Long id) {
        return service.remove(id);
    }

}
