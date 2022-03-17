package br.com.project.register.services;

import br.com.project.register.forms.CustomerForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.project.register.dto.CustomerDto;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public interface CustomerService {

    public Page<CustomerDto> findAllCustomer(Pageable pageable);

    public CustomerDto findByIdCustomer(Long id);

    public ResponseEntity<CustomerDto> createCustomer(CustomerForm customerForm);
}
