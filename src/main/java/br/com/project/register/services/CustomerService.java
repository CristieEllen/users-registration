package br.com.project.register.services;

import br.com.project.register.dto.request.CustomerRequestDtoPatch;
import br.com.project.register.dto.request.CustomerRequestDtoPost;
import br.com.project.register.entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    Page<Customer> findAllCustomer(Pageable pageable);

    Customer findBy(Long idCustomer);

    Customer createCustomer(CustomerRequestDtoPost customerForm);

    void removeCustomer(Long id);

    void removeAddress(Long idCustomer, Long idAddress);

    Customer updateCustomer(Long id, CustomerRequestDtoPatch customerRequestDtoPatch);
}
