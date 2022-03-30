package br.com.project.register.services;

import br.com.project.register.dto.request.CustomerRequestDto;
import br.com.project.register.dto.request.UpdateCustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

public interface CustomerService {

    Page<br.com.project.register.dto.response.CustomerDto> findAllCustomer(Pageable pageable);

    br.com.project.register.dto.response.CustomerDto findByIdCustomer(Long id);

    ResponseEntity<br.com.project.register.dto.response.CustomerDto> createCustomer(CustomerRequestDto customerForm, UriComponentsBuilder uriBuilder);

    ResponseEntity removeCustomer(Long id);

    ResponseEntity<br.com.project.register.dto.response.CustomerDto> updateCustomer(Long id, UpdateCustomerDto updateCustomerForm);
}
