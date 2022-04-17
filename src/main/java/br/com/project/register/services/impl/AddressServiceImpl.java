package br.com.project.register.services.impl;

import br.com.project.register.dto.request.AddressRequestDtoPatch;
import br.com.project.register.dto.request.AddressRequestDtoPut;
import br.com.project.register.entities.Address;
import br.com.project.register.exceptions.CompiledException;
import br.com.project.register.repositories.AddressRepository;
import br.com.project.register.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
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
    public void removeAddress(final Long idCustomer, Long idAddress){
        validationAddressInCustomer(idCustomer, idAddress);
        notDeletePrincipalAddress(idAddress);
        findBy(idAddress);
        addressRepository.deleteById(idAddress);

    }

    @Transactional
    @Override
    public Address updateAddress(final Long idCustomer, final Long idAddress, final AddressRequestDtoPatch addressRequest){
        validationAddressInCustomer(idCustomer, idAddress);
        final Address newAddress = findBy(idAddress);
        updateAddress(newAddress, addressRequest);
        return addressRepository.save(newAddress);
    }

    @Override
    public Address updatePrincipalAddress(final Long idCustomer, final Long idAddress, final AddressRequestDtoPut addressRequest){
        validationAddressInCustomer(idCustomer, idAddress);
        final Address newAddress = findBy(idAddress);
        newAddress.setAllPrincipalAddress();
        updatePrincipalAddress(newAddress, addressRequest);
        return addressRepository.save(newAddress);
    }

    private void updateAddress(final Address newAddress, final AddressRequestDtoPatch addressRequest) {
        newAddress.setName((addressRequest.getName()==null) ? newAddress.getName() : addressRequest.getName());
        newAddress.setNumber((addressRequest.getNumber()==null) ? newAddress.getNumber() : addressRequest.getNumber());
        newAddress.setCep((addressRequest.getCep()==null) ? newAddress.getCep() : addressRequest.getCep());
        newAddress.setDistrict((addressRequest.getDistrict()==null) ? newAddress.getDistrict() : addressRequest.getDistrict());
    }

    private void updatePrincipalAddress(final Address newAddress, final AddressRequestDtoPut addressRequest) {
        newAddress.setPrincipalAddress((addressRequest.getPrincipalAddress() == null) ? newAddress.getPrincipalAddress() : addressRequest.getPrincipalAddress());
    }


    private void validationAddressInCustomer(final Long idCustomer, final Long idAddress){
        Address address = findBy(idAddress);
        if(!(Objects.equals(idCustomer, address.getCustomer().getId()))){
            throw new CompiledException("This address does not belong to id " + idCustomer + ".") ;
        }

    }

    private void notDeletePrincipalAddress(final Long idAddress){
        final Address newAddress = findBy(idAddress);
        if(newAddress.getPrincipalAddress()){
            throw new CompiledException("Can not delete main address");
        }
    }
}
