package br.com.project.register.services;

import br.com.project.register.dto.request.CustomerRequestDto;
import br.com.project.register.dto.response.CustomerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public interface CustomerService {

    Page<CustomerDto> findAllCustomer(Pageable pageable);

   CustomerDto findByIdCustomer(Long id);

    ResponseEntity<CustomerDto> createCustomer(CustomerRequestDto customerForm, UriComponentsBuilder uriBuilder);

    ResponseEntity removeCustomer(Long id);


    ResponseEntity<CustomerDto> savePartial(Long id, Map<String, Object> fields);
}
