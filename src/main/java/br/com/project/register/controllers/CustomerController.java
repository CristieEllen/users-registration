package br.com.project.register.controllers;

import br.com.project.register.dto.request.CustomerRequestDtoPatch;
import br.com.project.register.dto.request.CustomerRequestDto;
import br.com.project.register.dto.response.CustomerResponseDto;
import br.com.project.register.entities.Customer;
import br.com.project.register.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping
    public Page<CustomerResponseDto> findAllCustomer(Pageable pageable){
        Page<Customer> resultCustomer = service.findAllCustomer(pageable);
        return resultCustomer.map(CustomerResponseDto::new);
    }

    @GetMapping(value = "/{idCustomer}")
    public CustomerResponseDto findByIdCustomer(@PathVariable Long idCustomer){
        return new CustomerResponseDto(service.findBy(idCustomer));
    }

    @PostMapping
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody @Valid CustomerRequestDto customerRequest, UriComponentsBuilder uriBuilder){
        Customer customer = service.createCustomer(customerRequest);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).body(new CustomerResponseDto(customer));
    }

    @DeleteMapping("/{idCustomer}")
    public void removeCustomer(@PathVariable Long idCustomer) {
        service.removeCustomer(idCustomer);
    }

   /* @DeleteMapping("/{idCustomer}/{idAddress}")
    public void removeAddress(@PathVariable(name = "idCustomer") Long idCustomer, @PathVariable(name = "idAddress") Long idAddress) {
        service.removeAddress(idCustomer, idAddress);
    }*/

    @PatchMapping("/{idCustomer}")
    ResponseEntity<CustomerResponseDto> updateCustomer(@PathVariable Long idCustomer, @RequestBody @Valid CustomerRequestDtoPatch customerRequest){
        Customer customer = service.updateCustomer(idCustomer, customerRequest);
        return ResponseEntity.ok().body(new CustomerResponseDto(customer));
    }
}
