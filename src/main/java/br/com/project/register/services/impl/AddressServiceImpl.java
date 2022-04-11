package br.com.project.register.services.impl;

import br.com.project.register.dto.request.AddressRequestDtoPatch;
import br.com.project.register.entities.Address;
import br.com.project.register.exceptions.CompiledException;
import br.com.project.register.repositories.AddressRepository;
import br.com.project.register.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public void removeAddress(Long idAddress){
        findBy(idAddress);
        addressRepository.deleteById(idAddress);

    }

    @Transactional
    @Override
    public Address updateAddress(final Long idAddress, final AddressRequestDtoPatch addressRequest){

        final Address address = findBy(idAddress);
        updateAddress(address, addressRequest);
        return addressRepository.save(address);
    }

    private void updateAddress(final Address address, final AddressRequestDtoPatch addressRequest) {
        address.setName((addressRequest.getName()==null) ? address.getName() : addressRequest.getName());
        address.setNumber((addressRequest.getNumber()==null) ? address.getNumber() : addressRequest.getNumber());
        address.setCep((addressRequest.getCep()==null) ? address.getCep() : addressRequest.getCep());
        address.setDistrict((addressRequest.getDistrict()==null) ? address.getDistrict() : addressRequest.getDistrict());
    }

}
