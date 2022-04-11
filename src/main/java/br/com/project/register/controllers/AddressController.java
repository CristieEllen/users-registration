package br.com.project.register.controllers;

import br.com.project.register.dto.request.AddressRequestDtoPatch;
import br.com.project.register.dto.response.AddressResponseDto;
import br.com.project.register.entities.Address;
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

    @GetMapping("/{idAddress}")
    public AddressResponseDto findByAddress(@PathVariable final Long idAddress){
        return new AddressResponseDto(service.findBy(idAddress));
    }

    @DeleteMapping("/{idAddress}")
    public void removeAddress(@PathVariable final Long idAddress){
        service.removeAddress(idAddress);
    }

    @PatchMapping("/{idAddress}")
    public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable final Long idAddress, @RequestBody @Valid final AddressRequestDtoPatch addressRequest){
        Address Address = service.updateAddress(idAddress, addressRequest);
        return ResponseEntity.ok().body(new AddressResponseDto(Address));
    }

}
