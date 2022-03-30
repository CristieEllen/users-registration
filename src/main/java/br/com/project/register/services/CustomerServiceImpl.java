package br.com.project.register.services;

import br.com.project.register.dto.request.AddressRequestDto;
import br.com.project.register.dto.response.CustomerDto;
import br.com.project.register.exceptions.ObjectNotFoundException;
import br.com.project.register.dto.request.CustomerRequestDto;
import br.com.project.register.dto.request.UpdateCustomerDto;
import br.com.project.register.dto.response.AddressDto;

import br.com.project.register.exceptions.chooseMoreThanAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.project.register.entities.Customer;
import br.com.project.register.repositories.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    public Page<CustomerDto> findAllCustomer(Pageable pageable) {
        Page<Customer> resultCustomer = customerRepository.findAll(pageable);
        return resultCustomer.map(br.com.project.register.dto.response.CustomerDto::new);
    }

    public CustomerDto findByIdCustomer(Long id) {
        Optional<Customer> resultCustomer = customerRepository.findById(id);
        return new CustomerDto(resultCustomer.orElseThrow(() -> new ObjectNotFoundException("Object not found. Non-existent customer id. Id: " + id)));
    }

    public ResponseEntity<CustomerDto> createCustomer(CustomerRequestDto customerForm, UriComponentsBuilder uriBuilder) {

        if(!validationPrincipalAddress(customerForm)){
            Customer customer = customerRepository.save(customerForm.converter());

            URI uri = uriBuilder.path("/{id}").buildAndExpand(customer.getId()).toUri();

            return ResponseEntity.created(uri).body(new CustomerDto(customer));
        }
        throw new chooseMoreThanAllowed("Select only one address as primary. ");

    }

    public ResponseEntity removeCustomer(Long id){
        try{
            customerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch(EmptyResultDataAccessException e){
            throw new ObjectNotFoundException("Object not deleted because not found. Id: " + id);
        }
    }

    public ResponseEntity<CustomerDto> updateCustomer(Long id, UpdateCustomerDto updateCustomerForm){
        try{
            Customer customer = updateCustomerForm.update(id, customerRepository);
            return ResponseEntity.ok(new br.com.project.register.dto.response.CustomerDto(customer));
        } catch(EntityNotFoundException e){
            throw new ObjectNotFoundException("Object not updated. Non-existent customer id. Id: " + id);
        }

    }

    public Boolean validationPrincipalAddress(CustomerRequestDto customerRequestDto){
        return customerRequestDto.validationAddress();
    }

}