package br.com.project.register.services;

import br.com.project.register.dto.request.AddressRequestDto;
import br.com.project.register.dto.request.CustomerRequestDto;
import br.com.project.register.dto.response.CustomerDto;
import br.com.project.register.entities.Address;
import br.com.project.register.entities.Customer;
import br.com.project.register.exceptions.ChooseMoreThanAllowedException;
import br.com.project.register.exceptions.CompiledException;
import br.com.project.register.repositories.AddressRepository;
import br.com.project.register.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Page<CustomerDto> findAllCustomer(Pageable pageable) {
        Page<Customer> resultCustomer = customerRepository.findAll(pageable);
        return resultCustomer.map(br.com.project.register.dto.response.CustomerDto::new);
    }

    @Override
    public CustomerDto findByIdCustomer(Long id) {
        return new CustomerDto(findBy(id));
    }

    @Override
    public ResponseEntity<CustomerDto> createCustomer(CustomerRequestDto customerForm, UriComponentsBuilder uriBuilder) {

        if(validationPrincipalAddress(customerForm)){
            Customer customer = customerRepository.save(customerForm.converter());
            URI uri = uriBuilder.path("/{id}").buildAndExpand(customer.getId()).toUri();

            return ResponseEntity.created(uri).body(new CustomerDto(customer));
        }
        throw new ChooseMoreThanAllowedException("Select only one address as primary. ");
    }

    @Override
    public ResponseEntity removeCustomer(Long id){
        try{
            customerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch(EmptyResultDataAccessException e){
            throw new CompiledException("Object not deleted because not found. Id: " + id);
        }
    }

    @Override
    public ResponseEntity<CustomerDto> savePartial(Long id, Map<String, Object> fields){
        Customer resultCustomer = findBy(id);

        fields.forEach((name, value) -> {
            Field field = ReflectionUtils.findField(Customer.class, (String) name);
            field.setAccessible(true);
            ReflectionUtils.setField(field, resultCustomer, value);
        });
        return ResponseEntity.ok(new CustomerDto(customerRepository.save(resultCustomer)));
    }


    public Boolean validationPrincipalAddress(CustomerRequestDto customerRequestDto){
        int sum = 0;
        for (AddressRequestDto add : customerRequestDto.getAddresses()){
            if(add.getPrincipalAddress()){
                sum++;
            }
        }
        return sum == 1;
    }

    public Customer findBy(Long id){
        Optional<Customer> resultCustomer = customerRepository.findById(id);
        return resultCustomer.orElseThrow(() -> new CompiledException("Object not found. Non-existent customer id. Id: " + id));
    }
}