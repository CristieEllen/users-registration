package br.com.project.register.services.impl;

import br.com.project.register.dto.request.AddressRequestDto;
import br.com.project.register.dto.request.CustomerRequestDto;
import br.com.project.register.dto.request.CustomerRequestDtoPatch;
import br.com.project.register.entities.Customer;
import br.com.project.register.exceptions.ChooseMoreThanAllowedException;
import br.com.project.register.exceptions.CompiledException;
import br.com.project.register.repositories.CustomerRepository;
import br.com.project.register.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Page<Customer> findAllCustomer(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer findBy(final Long idCustomer){
        final Optional<Customer> resultCustomer = customerRepository.findById(idCustomer);
        return resultCustomer.orElseThrow(() -> new CompiledException("Element of id " + idCustomer + " does not exist"));
    }

    @Transactional
    @Override
    public Customer createCustomer(final CustomerRequestDto customerRequest) {
        validationPrincipalAddress(customerRequest);
        return customerRepository.save(customerRequest.converterCustomer());
    }

    @Transactional
    public void removeCustomer(final Long idCustomer){
        findBy(idCustomer);
        customerRepository.deleteById(idCustomer);
    }

    @Transactional
    @Override
    public Customer updateCustomer(final Long idCustomer, final CustomerRequestDtoPatch customerRequest){
        final Customer customer = findBy(idCustomer);
        updateCustomer(customer, customerRequest);
        return customerRepository.save(customer);
    }

    private void validationPrincipalAddress(final CustomerRequestDto customerRequest){
        int sum = 0;
        for (AddressRequestDto principalAddress : customerRequest.getAddresses()){
            if(principalAddress.getPrincipalAddress()){
                sum++;
            }
        }
        if(sum != 1){
            throw new ChooseMoreThanAllowedException("Select only one address as primary. ");
        }
    }


    private void updateCustomer(final Customer customer, final CustomerRequestDtoPatch customerRequest) {
        customer.setName((customerRequest.getName()==null) ? customer.getName() : customerRequest.getName());
        customer.setEmail((customerRequest.getEmail()==null) ? customer.getEmail() : customerRequest.getEmail());
        customer.setCellphone((customerRequest.getCellphone()==null) ? customer.getCellphone() : customerRequest.getCellphone());
    }


}