package br.com.project.register.services.impl;

import br.com.project.register.dto.request.UpdateAddressDto;
import br.com.project.register.dto.response.AddressDto;
import br.com.project.register.entities.Address;
import br.com.project.register.exceptions.CompiledException;
import br.com.project.register.repositories.AddressRepository;
import br.com.project.register.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public ResponseEntity removeAddress(Long id){
        try{
            addressRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch(EmptyResultDataAccessException e){
            throw new CompiledException("Object not deleted because not found. Id: " + id);
        }
    }

    public ResponseEntity<AddressDto> updateAddress(Long id, UpdateAddressDto updateAddressForm){
        try{
            Address address = updateAddressForm.update(id, addressRepository);
            return ResponseEntity.ok(new AddressDto(address));
        } catch(EntityNotFoundException e){
            throw new CompiledException("Object not updated. Non-existent customer id. Id: " + id);
        }

    }
}
