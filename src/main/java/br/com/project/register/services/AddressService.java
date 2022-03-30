package br.com.project.register.services;

import br.com.project.register.dto.request.UpdateAddressDto;
import br.com.project.register.dto.response.AddressDto;
import org.springframework.http.ResponseEntity;

public interface AddressService {

    ResponseEntity removeAddress(Long id);

    ResponseEntity<AddressDto> updateAddress(Long id, UpdateAddressDto updateAddressForm);

}
