package br.com.project.register.build;

import br.com.project.register.dto.request.AddressRequestDto;
import br.com.project.register.dto.request.AddressRequestDtoPatch;
import br.com.project.register.dto.request.AddressRequestDtoPut;
import br.com.project.register.entities.Address;
import lombok.Builder;

@Builder
public class AddressDtoBuild {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Rua joão antônio";

    @Builder.Default
    private String number = "45";

    @Builder.Default
    private String district = "federal";

    @Builder.Default
    private String city = "João Penha";

    @Builder.Default
    private String cep = "12345678";

    @Builder.Default
    private String state = "goias";

    @Builder.Default
    private Boolean principalAddress = true;

    public AddressRequestDto toAddressRequestDto(){
        return new AddressRequestDto(id,
                name,
                number,
                district,
                city,
                cep,
                state,
                principalAddress);
    }

    public AddressRequestDtoPatch toAddressRequestDtoPatch(){
        return new AddressRequestDtoPatch(
                name,
                number,
                district,
                cep);
    }

    public Address toAddress(){
        return new Address(id,
                name,
                number,
                district,
                city,
                cep,
                state,
                principalAddress);
    }

    public AddressRequestDtoPut toAddressRequestDtoPut(){
        return new AddressRequestDtoPut(principalAddress);
    }

}
