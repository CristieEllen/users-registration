package br.com.project.register.controllers;

import br.com.project.register.dto.request.AddressRequestDto;
import br.com.project.register.dto.request.AddressRequestDtoPatch;
import br.com.project.register.dto.request.AddressRequestDtoPut;
import br.com.project.register.dto.response.AddressResponseDto;
import br.com.project.register.entities.Address;
import br.com.project.register.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/addresses")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping("/{idAddress}")
    public AddressResponseDto findByAddress(@PathVariable final Long idAddress){
        return new AddressResponseDto(service.findBy(idAddress));
    }

    @PostMapping("/{idCustomer}")
    public ResponseEntity<AddressResponseDto> createAddress(@PathVariable final Long idCustomer, @RequestBody @Valid final AddressRequestDto addressRequest, final UriComponentsBuilder uriBuilder){
        Address address = service.createAddress(idCustomer, addressRequest);
        URI uri = uriBuilder.path("/{id}").buildAndExpand(address.getId()).toUri();
        return ResponseEntity.created(uri).body(new AddressResponseDto(address));
    }


    @DeleteMapping("/{idCustomer}/{idAddress}")
    public void removeAddress(@PathVariable final Long idCustomer, @PathVariable final Long idAddress){
        service.removeAddress(idCustomer, idAddress);
    }

    @PatchMapping("/{idCustomer}/{idAddress}")
    public ResponseEntity<AddressResponseDto> updateAddress(@PathVariable final Long idCustomer, @PathVariable final Long idAddress, @RequestBody @Valid final AddressRequestDtoPatch addressRequest){
        Address address = service.updateAddress(idCustomer, idAddress, addressRequest);
        return ResponseEntity.ok().body(new AddressResponseDto(address));
    }

    @PutMapping("/{idCustomer}/{idAddress}")
    public ResponseEntity<AddressResponseDto> updatePrincipalAddress(@PathVariable final Long idCustomer, @PathVariable final Long idAddress, @RequestBody @Valid final AddressRequestDtoPut addressRequest){
        Address address = service.updatePrincipalAddress(idCustomer, idAddress, addressRequest);
        return ResponseEntity.ok().body(new AddressResponseDto(address));
    }
}
