package br.com.project.register.controllers;

import br.com.project.register.dto.request.UpdateAddressDto;
import br.com.project.register.dto.response.AddressDto;
import br.com.project.register.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {

    @Autowired
    private AddressService service;

    @DeleteMapping("/{id}")
    public ResponseEntity removeAddress(@PathVariable Long id){
        return service.removeAddress(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long id,@RequestBody @Valid UpdateAddressDto updateAddressForm){
        return service.updateAddress(id, updateAddressForm);
    }

}
