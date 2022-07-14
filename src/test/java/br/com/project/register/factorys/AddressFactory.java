package br.com.project.register.factorys;

import br.com.project.register.dto.request.AddressRequestDtoPatch;
import br.com.project.register.dto.request.AddressRequestDtoPut;
import br.com.project.register.entities.Address;

public class AddressFactory {

    public static Address defaultAddress(){
        return new Address("Maria da Silva","23", "Vila Nova", "Bananeira", "12345690","São Paulo", false);
    }

    public static AddressRequestDtoPatch defaultAddressPatch(){
        return new AddressRequestDtoPatch("Maria José Antônio", null, null, null);
    }

    public static AddressRequestDtoPut defaultAddressPutTrue(){
        return new AddressRequestDtoPut(true);
    }

    public static AddressRequestDtoPut defaultAddressPutFalse(){
        return new AddressRequestDtoPut(false);
    }
}
