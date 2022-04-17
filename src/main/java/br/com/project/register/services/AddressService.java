package br.com.project.register.services;

import br.com.project.register.dto.request.AddressRequestDtoPatch;
import br.com.project.register.dto.request.AddressRequestDtoPut;
import br.com.project.register.entities.Address;

public interface AddressService {

    Address findBy(final Long idAddress);

    void removeAddress(final Long idCustomer, final Long idAddress);

    Address updateAddress(final Long idCustomer, final Long idAddress, final AddressRequestDtoPatch addressRequest);

    Address updatePrincipalAddress(final Long idCustomer, final Long idAddress, final AddressRequestDtoPut addressRequest);
}

