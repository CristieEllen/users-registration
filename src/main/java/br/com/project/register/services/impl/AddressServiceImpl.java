package br.com.project.register.services.impl;

import br.com.project.register.entities.Address;
import br.com.project.register.exceptions.CompiledException;
import br.com.project.register.repositories.AddressRepository;
import br.com.project.register.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address findBy(final Long idAddress){
        final Optional<Address> resultAddress = addressRepository.findById(idAddress);
        return resultAddress.orElseThrow(() -> new CompiledException("Element of id " + idAddress + " does not exist"));
    }

    @Override
    public void removeAddress(Long idAddress){
        findBy(idAddress);
        addressRepository.deleteById(idAddress);

    }


}
