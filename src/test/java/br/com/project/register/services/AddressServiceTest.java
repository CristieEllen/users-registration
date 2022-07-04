package br.com.project.register.services;

import br.com.project.register.build.AddressDtoBuild;
import br.com.project.register.build.CustomerDtoBuilder;
import br.com.project.register.dto.request.AddressRequestDto;
import br.com.project.register.dto.request.AddressRequestDtoPatch;
import br.com.project.register.dto.request.AddressRequestDtoPut;
import br.com.project.register.entities.Address;
import br.com.project.register.entities.Customer;
import br.com.project.register.exceptions.CompiledException;
import br.com.project.register.repositories.AddressRepository;
import br.com.project.register.services.impl.AddressServiceImpl;
import br.com.project.register.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class AddressServiceTest {

    private static AddressRepository addressRepository;

    private static AddressServiceImpl addressService;

    private static CustomerServiceImpl customerService;

    @BeforeAll
    static void setUp(){
        addressRepository = Mockito.mock(AddressRepository.class);
        customerService = Mockito.mock(CustomerServiceImpl.class);
        addressService = new AddressServiceImpl(addressRepository, customerService);
    }

    @Test
    void  testGivenValidIdThenReturnThisAddress() { //testa o retorno do endereço caso o id esteja correto
        Address addressExpected = AddressDtoBuild.builder().build().toAddress();

        when(addressRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(addressExpected));
        Address address = addressService.findBy(addressExpected.getId());

        Assertions.assertEquals(addressExpected.getId(), 1L);
        Assertions.assertFalse(addressExpected.getName().isEmpty());
        Assertions.assertFalse(address.getCity().isEmpty());
    }

    @Test //testa a busca de id de um endereço invalido e retorna erro caso não encontre
    void testGivenInvalidAddressIdThenThrowException(){
        Long invalidAddressId = 1L;
        when(addressRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(Mockito.any(Address.class)));

        Assertions.assertThrows(CompiledException.class, () -> addressService.findBy(invalidAddressId));
    }

    @Test //testa se está atualizando os dados do usuário
    void testGivenValidAddressIdAndUpdateInfoThenReturnSuccesOnUpdate() {
        Long customerId= 1L;

        Address addressExpected = AddressDtoBuild.builder().build().toAddress();
        addressExpected.setCustomer(CustomerDtoBuilder.builder().build().toCustomer());

        AddressRequestDtoPatch expectedAddressToUpdate = AddressDtoBuild.builder().build().toAddressRequestDtoPatch();
        expectedAddressToUpdate.setName("José Antônio");

        when(addressRepository.findById(addressExpected.getId()))
                .thenReturn(Optional.of(addressExpected));

        addressExpected.setName("José Antônio");

        doReturn(addressExpected)
                .when(addressRepository)
                .save(addressExpected);

        Address addressUpdated = addressService.updateAddress(customerId, addressExpected.getId(), expectedAddressToUpdate);
        Assertions.assertEquals(addressUpdated.getName(), "José Antônio");
    }

    @Test //testa uma exceção caso o id seja invalido na hora de atualizar
    void testGivenInvalidAddressIdAndUpdateInfoThenThrowExceptionOnUpdate(){
        Long invalidAddressId = 2L;
        AddressRequestDtoPatch expectedAddressToUpdate = AddressDtoBuild.builder().build().toAddressRequestDtoPatch();
        expectedAddressToUpdate.setName("José Antônio");

        Customer customer = CustomerDtoBuilder.builder().build().toCustomer();
        when(addressRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(any(Address.class)));

        Assertions.assertThrows(CompiledException.class, () -> addressService.updateAddress(customer.getId(), invalidAddressId, expectedAddressToUpdate));
    }

    @Test //testa a tentativa de atualização de um endereço e o endereço não ser da pessoa mencionada gerando erro
    void testGivenInvalidCustomerIdInAddressThenReturnAnErrorOnUpdate() {
        Long invalidCustomerId = 2L;
        Address expectedAddressNotToDelete = AddressDtoBuild.builder().build().toAddress();
        expectedAddressNotToDelete.setCustomer(CustomerDtoBuilder.builder().build().toCustomer());
        expectedAddressNotToDelete.setPrincipalAddress(false);

        AddressRequestDtoPatch expectedAddressToUpdate = AddressDtoBuild.builder().build().toAddressRequestDtoPatch();
        expectedAddressToUpdate.setName("José Antônio");

        when(addressRepository.findById(expectedAddressNotToDelete.getId()))
                .thenReturn(Optional.of(expectedAddressNotToDelete));

        Assertions.assertThrows(CompiledException.class, () -> addressService.updateAddress(invalidCustomerId, expectedAddressNotToDelete.getId(), expectedAddressToUpdate));
    }

    @Test //testa o sucesso ao deletar
    void testGivenValidAddressIdThenReturnSuccessOnDelete() {
        Long deletedAddressId = 1L;
        Address expectedAddressToDelete = AddressDtoBuild.builder().build().toAddress();
        expectedAddressToDelete.setCustomer(CustomerDtoBuilder.builder().build().toCustomer());
        expectedAddressToDelete.setPrincipalAddress(false);

        when(addressRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(expectedAddressToDelete));
        addressService.removeAddress(expectedAddressToDelete.getCustomer().getId(), deletedAddressId);

        verify(addressRepository, times(1)).deleteById(deletedAddressId);
    }

    @Test //testa se está retornando um erro caso o endereço para deletar não corresponda ao usuário indicado
    void testGivenInvalidCustomerIdInAddressThenReturnAnErrorOnDelete() {
        Long invalidCustomerId = 2L;
        Address expectedAddressNotToDelete = AddressDtoBuild.builder().build().toAddress();
        expectedAddressNotToDelete.setCustomer(CustomerDtoBuilder.builder().build().toCustomer());
        expectedAddressNotToDelete.setPrincipalAddress(false);

        when(addressRepository.findById(expectedAddressNotToDelete.getId()))
                .thenReturn(Optional.of(expectedAddressNotToDelete));


        Assertions.assertThrows(CompiledException.class, () -> addressService.removeAddress(invalidCustomerId, expectedAddressNotToDelete.getId()));
    }

    @Test // testa se ao tentar deletar o endereço principal irá retornar erro
    void testGivenPrincipalAddressTrueThenReturnThrowExceptionOnDelete() {
        Address expectedAddressToDelete = AddressDtoBuild.builder().build().toAddress();
        expectedAddressToDelete.setCustomer(CustomerDtoBuilder.builder().build().toCustomer());

        when(addressRepository.findById(expectedAddressToDelete.getId()))
                .thenReturn(Optional.of(expectedAddressToDelete));

        Assertions.assertThrows(CompiledException.class, () ->addressService.removeAddress(Mockito.anyLong(), expectedAddressToDelete.getId()));
    }

    @Test //testa caso o id do endereço seja invalido e retorna um erro ao tentar deletar
    void testGivenInvalidAddressIdThenReturnAnErrorOnDelete() {
        Long invalidAddressId = 2L;
        Customer customer = CustomerDtoBuilder.builder().build().toCustomer();
        when(addressRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.ofNullable(any(Address.class)));
        Assertions.assertThrows(CompiledException.class, () -> addressService.removeAddress(customer.getId(), invalidAddressId));
    }

    @Test
    void testGivenAddressDTOThenSaved(){
        Customer customer = CustomerDtoBuilder.builder().build().toCustomer();
        Address addressExpected = AddressDtoBuild.builder().build().toAddress();
        addressExpected.setCustomer(customer);

        AddressRequestDto addressToSave = AddressDtoBuild.builder().build().toAddressRequestDto();

        when(customerService.findBy(customer.getId()))
                .thenReturn(customer);

        when(addressRepository.save(Mockito.any()))
                .thenReturn(addressExpected);

        Address address = addressService.createAddress(customer.getId(), addressToSave);

        Assertions.assertEquals(address.getName(), addressToSave.getName());

    }

    @Test //testa se vai atualizar um unico endereço principal para falso
    void testGivenValidAddressIdAndUpdateAddressPrincipalThenReturnThrowException() {
        Long customerId= 1L;

        Address addressExpected = AddressDtoBuild.builder().build().toAddress();
        addressExpected.setCustomer(CustomerDtoBuilder.builder().build().toCustomer());

        AddressRequestDtoPut expectedAddressToUpdate = AddressDtoBuild.builder().build().toAddressRequestDtoPut();
        expectedAddressToUpdate.setPrincipalAddress(false);

        when(addressRepository.findById(addressExpected.getId()))
                .thenReturn(Optional.of(addressExpected));

        doReturn(addressExpected)
                .when(addressRepository)
                .save(addressExpected);


        Assertions.assertThrows(CompiledException.class, () -> addressService.updatePrincipalAddress(customerId, addressExpected.getId(), expectedAddressToUpdate));
    }

    @Test //testa se está colocando um endereço como principal
    void testGivenValidAddressIdAndUpdateAddressPrincipalThenReturnSuccesOnUpdate() {

        Address addressExpected = AddressDtoBuild.builder().build().toAddress();

        Customer customerExpected = CustomerDtoBuilder.builder().build().toCustomerTestMainAddress();

        addressExpected.setCustomer(customerExpected);

        AddressRequestDtoPut expectedAddressToUpdate = AddressDtoBuild.builder().build().toAddressRequestDtoPut();
        expectedAddressToUpdate.setPrincipalAddress(true);

        when(addressRepository.findById(customerExpected.getAddresses().get(0).getId()))
                .thenReturn(Optional.of(addressExpected));

        doReturn(addressExpected)
                .when(addressRepository)
                .save(addressExpected);

        Address address = addressService.updatePrincipalAddress(customerExpected.getId(), addressExpected.getId(), expectedAddressToUpdate);

        Assertions.assertEquals(address.getPrincipalAddress(), true);

    }
}
