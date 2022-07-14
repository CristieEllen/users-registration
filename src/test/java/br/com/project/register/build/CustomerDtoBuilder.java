package br.com.project.register.build;

import br.com.project.register.dto.request.AddressRequestDto;
import br.com.project.register.dto.request.CustomerRequestDto;
import br.com.project.register.dto.request.CustomerRequestDtoPatch;
import br.com.project.register.entities.Address;
import br.com.project.register.entities.Customer;
import br.com.project.register.enums.CustomerTypes;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class CustomerDtoBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String name = "Cistie Ellen Ferreira";

    @Builder.Default
    private String documentNumber = "45237128831";

    @Builder.Default
    private String email = "cristie@email.com";

    @Builder.Default
    private String cellphone = "1299999999";

    @Builder.Default
    private CustomerTypes customerTypes = CustomerTypes.valueOf("PF");

    @Builder.Default
    private List<AddressRequestDto> addressRequest = new ArrayList<>();

    @Builder.Default
    private List<Address> address = new ArrayList<>();


    public CustomerRequestDto toCustomerRequestDto(){
        addressRequest.add(AddressDtoBuild.builder().build().toAddressRequestDto());
        return new CustomerRequestDto(
                name,
                documentNumber,
                email,
                cellphone,
                customerTypes,
                addressRequest);
    }

    public CustomerRequestDto toCustomerRequestDtoTestMainAddress(){
        addressRequest.add(AddressDtoBuild.builder().build().toAddressRequestDto());
        addressRequest.add(AddressDtoBuild.builder().build().toAddressRequestDto());
        return new CustomerRequestDto(
                name,
                documentNumber,
                email,
                cellphone,
                customerTypes,
                addressRequest);
    }

    public Customer toCustomerTestNumberOfAddress(){
        address.add(AddressDtoBuild.builder().build().toAddress());
        address.add(AddressDtoBuild.builder().build().toAddress());
        address.add(AddressDtoBuild.builder().build().toAddress());
        address.add(AddressDtoBuild.builder().build().toAddress());
        address.add(AddressDtoBuild.builder().build().toAddress());

        return new Customer(id,
                name,
                documentNumber,
                email,
                cellphone,
                customerTypes,
                address);
    }
    public CustomerRequestDtoPatch toCustomerRequestDtoPatch(){
        return new CustomerRequestDtoPatch(name,
                email,
                cellphone);
    }
    public Customer toCustomer(){
        address.add(AddressDtoBuild.builder().build().toAddress());
        return new Customer(id,
                name,
                documentNumber,
                email,
                cellphone,
                customerTypes,
                address);
    }

    public Customer toCustomerTestMainAddress(){
        address.add(AddressDtoBuild.builder().build().toAddress());

        Address addressToUpdate = AddressDtoBuild.builder().build().toAddress();
        addressToUpdate.setId(2L);
        addressToUpdate.setPrincipalAddress(false);

        address.add(addressToUpdate);

        return new Customer(id,
                name,
                documentNumber,
                email,
                cellphone,
                customerTypes,
                address);
    }
}
