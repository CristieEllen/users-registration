package br.com.project.register.services.impl;

import br.com.project.register.dto.request.AddressRequestDtoPost;
import br.com.project.register.dto.request.CustomerRequestDtoPatch;
import br.com.project.register.dto.request.CustomerRequestDtoPost;
import br.com.project.register.entities.Address;
import br.com.project.register.entities.Customer;
import br.com.project.register.exceptions.ChooseMoreThanAllowedException;
import br.com.project.register.exceptions.CompiledException;
import br.com.project.register.repositories.AddressRepository;
import br.com.project.register.repositories.CustomerRepository;
import br.com.project.register.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Page<Customer> findAllCustomer(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer findBy(Long idCustomer){
        Optional<Customer> resultCustomer = customerRepository.findById(idCustomer);
        return resultCustomer.orElseThrow(() -> new CompiledException("Element of id " + idCustomer + " does not exist"));
    }

    @Transactional
    @Override
    public Customer createCustomer(CustomerRequestDtoPost customerRequest) {
        validationPrincipalAddress(customerRequest);
        return customerRepository.save(customerRequest.converterCustomer());
    }

    @Transactional
    public void removeCustomer(Long idCustomer){
        findBy(idCustomer);
        customerRepository.deleteById(idCustomer);
    }

    @Transactional
    @Override
    public Customer updateCustomer(Long id, CustomerRequestDtoPatch customerRequest){
        Customer customer = findBy(id);
        updateCustomer(customer, customerRequest);
        return customerRepository.save(customer);
    }

    @Transactional
    @Override
    public void removeAddress(Long idCustomer, Long idAddress){
        validationAddress(idCustomer, idAddress);
        addressRepository.deleteById(idAddress);
    }


    private void validationPrincipalAddress(CustomerRequestDtoPost customerRequest){
        int sum = 0;
        for (AddressRequestDtoPost add : customerRequest.getAddresses()){
            if(add.getPrincipalAddress()){
                sum++;
            }
        }
        if(sum != 1){
            throw new ChooseMoreThanAllowedException("Select only one address as primary. ");
        }
    }

    private void validationAddress(Long idCustomer, Long idAddress){
        Customer customer = findBy(idCustomer);
        for(Address add : customer.getAddresses()){
            if(Objects.equals(add.getId(), idAddress)){
                return;
            }
        }
        throw new ChooseMoreThanAllowedException("There is no such address in this customer.");
    }

    /*public void removeAddress(Long id, Long idAddress) {
        Customer cus = findBy(id);
        System.out.print(cus.getAddresses());

        for (Address add: cus.getAddresses()) {
            if(Objects.equals(add.getId(), idAddress)){
                addressRepository.deleteById(idAddress);
            }
        }
    }*/

    private void updateCustomer(Customer customer, CustomerRequestDtoPatch customerRequest) {
        customer.setName((customerRequest.getName()==null) ? customer.getName() : customerRequest.getName());
        customer.setEmail((customerRequest.getEmail()==null) ? customer.getEmail() : customerRequest.getEmail());
        customer.setCellphone((customerRequest.getCellphone()==null) ? customer.getCellphone() : customerRequest.getCellphone());
    }


}