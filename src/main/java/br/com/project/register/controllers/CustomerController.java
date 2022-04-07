package br.com.project.register.controllers;

import br.com.project.register.dto.request.CustomerRequestDtoPatch;
import br.com.project.register.dto.request.CustomerRequestDtoPost;
import br.com.project.register.dto.response.CustomerDto;
import br.com.project.register.entities.Address;
import br.com.project.register.entities.Customer;
import br.com.project.register.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;


    @GetMapping
    public Page<CustomerDto> findAllCustomer(Pageable pageable){
        Page<Customer> resultCustomer = service.findAllCustomer(pageable);
        return resultCustomer.map(CustomerDto::new);
    }

    @GetMapping(value = "/{id}")
    public CustomerDto findByIdCustomer(@PathVariable Long id){
        return new CustomerDto(service.findBy(id));
    }


    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid CustomerRequestDtoPost customerForm, UriComponentsBuilder uriBuilder){
        Customer customer = service.createCustomer(customerForm);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(new CustomerDto(customer));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void removeCustomer(@PathVariable Long id) {
        service.removeCustomer(id);
    }

    @Transactional
    @DeleteMapping("/{idCustomer}/{idAddress}")
    public void removeAddress(@PathVariable(name = "idCustomer") Long idCustomer, @PathVariable(name = "idAddress") Long idAddress) {
        service.removeAddress(idCustomer, idAddress);
    }

    @PatchMapping("/{id}")
    ResponseEntity<CustomerDto> saveCustomer(@PathVariable Long id, @RequestBody @Valid CustomerRequestDtoPatch customerRequestDtoPatch){
        Customer customer = service.updateCustomer(id, customerRequestDtoPatch);
        return ResponseEntity.ok().body(new CustomerDto(customer));
    }
}
