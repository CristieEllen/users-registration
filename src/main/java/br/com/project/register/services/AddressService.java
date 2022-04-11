package br.com.project.register.services;

import br.com.project.register.dto.request.AddressRequestDtoPatch;
import br.com.project.register.entities.Address;

public interface AddressService {

    Address findBy(final Long idAddress);

    void removeAddress(Long idAddress);

    public Address updateAddress(final Long idAddress, final AddressRequestDtoPatch addressRequest);
}

