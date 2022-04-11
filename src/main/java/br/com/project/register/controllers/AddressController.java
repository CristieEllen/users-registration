package br.com.project.register.controllers;

import br.com.project.register.dto.response.AddressResponseDto;
import br.com.project.register.entities.Address;
import br.com.project.register.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {

    @Autowired
    private AddressService service;

    public AddressResponseDto findByAddress(final Long idAddress){
        return new AddressResponseDto(service.findBy(idAddress));
    }

    @DeleteMapping("/{id}")
    public void removeAddress(@PathVariable Long idAddress){
        service.removeAddress(idAddress);
    }



}
