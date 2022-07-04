package br.com.project.register.services;

import br.com.project.register.build.CustomerDtoBuilder;
import br.com.project.register.dto.request.CustomerRequestDto;
import br.com.project.register.dto.request.CustomerRequestDtoPatch;
import br.com.project.register.entities.Customer;
import br.com.project.register.exceptions.ChooseMoreThanAllowedException;
import br.com.project.register.exceptions.CompiledException;
import br.com.project.register.repositories.CustomerRepository;
import br.com.project.register.services.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    private Pageable page = Pageable.ofSize(5);
    @Mock
    private static CustomerRepository customerRepository;
    @InjectMocks
    private static CustomerServiceImpl customerService;

    @BeforeAll
    static void setUp(){
        customerRepository = Mockito.mock(CustomerRepository.class);
        customerService = new CustomerServiceImpl(customerRepository);

    }

    @Test
    void testThatCallsTheUserList() { // testa se está chamando todos os usuários da lista
        List<Customer> customersList = Collections.singletonList(CustomerDtoBuilder.builder().build().toCustomer());
        Mockito.when(customerRepository.findAll((Pageable) Mockito.any()))
                .thenReturn((new PageImpl<Customer>(customersList)));

        Page<Customer> expectedCustomers = customerService.findAllCustomer(page);
        Assertions.assertFalse(expectedCustomers.isEmpty());

    }

    @Test //testa se ao procurar pelo id é retornado uma pessoa
    void  testGivenValidCustomerIdThenReturnThisPerson() {
        Customer expectedCustomer = CustomerDtoBuilder.builder().build().toCustomer();

        when(customerRepository.findById(expectedCustomer.getId()))
                .thenReturn(Optional.of(expectedCustomer));
        Customer customer = customerService.findBy(expectedCustomer.getId());

        Assertions.assertEquals(expectedCustomer.getId(), 1L);
        Assertions.assertFalse(customer.getName().isEmpty());
        Assertions.assertFalse(customer.getDocumentNumber().isEmpty());
    }

    @Test //testa se vai retornar um erro caso não encontre o id no método find by id
    void testGivenInvalidCustomerIdThenThrowException(){
        Long invalidCustomerId = 1L;
        when(customerRepository.findById(invalidCustomerId))
                .thenReturn(Optional.ofNullable(Mockito.any(Customer.class)));

        Assertions.assertThrows(CompiledException.class, () -> customerService.findBy(invalidCustomerId));
    }

    @Test //testa se está chamando o método de delete
    void testGivenValidPersonIdThenReturnSuccessOnDelete() {
        Long deletedCustomerId = 1L;
        Customer expectedCustomerToDelete = CustomerDtoBuilder.builder().build().toCustomer();

        when(customerRepository.findById(deletedCustomerId))
                .thenReturn(Optional.of(expectedCustomerToDelete));
        customerService.removeCustomer(deletedCustomerId);

        verify(customerRepository, times(1)).deleteById(deletedCustomerId);
    }

    @Test //testa se está retornando um erro ao não encontrar um id no método de deletar
    void testGivenInvalidPersonIdThenReturnAnError() {
        Long invalidCustomerId = 1L;
        when(customerRepository.findById(invalidCustomerId))
                .thenReturn(Optional.ofNullable(any(Customer.class)));
        Assertions.assertThrows(CompiledException.class, () -> customerService.removeCustomer(invalidCustomerId));
    }

    @Test //testa se está atualizando os dados do usuário
    void testGivenValidCustomerIdAndUpdateInfoThenReturnSuccesOnUpdate() {
        Long updatedPersonId= 1L;

        Customer customerExpected = CustomerDtoBuilder.builder().build().toCustomer();
        customerExpected.setCellphone("12999999999");
        customerExpected.setName("Nome Teste");

        CustomerRequestDtoPatch expectedCustomerToUpdate = CustomerDtoBuilder.builder().build().toCustomerRequestDtoPatch();
        expectedCustomerToUpdate.setCellphone("12999999999");
        expectedCustomerToUpdate.setName("Nome Teste");

        when(customerRepository.findById(updatedPersonId))
                .thenReturn(Optional.of(CustomerDtoBuilder.builder().build().toCustomer()));
        doReturn(customerExpected)
                .when(customerRepository).save(customerExpected);

        Customer customerUpdated = customerService.updateCustomer(updatedPersonId, expectedCustomerToUpdate);
        Assertions.assertEquals(customerUpdated.getName(), "Nome Teste");
    }

    @Test
    void testGivenInvalidPCustomerIdAndUpdateInfoThenThrowExceptionOnUpdate(){
        Long invalidCustomerId = 1L;

        CustomerRequestDtoPatch updateCustomerDto = CustomerDtoBuilder.builder().build().toCustomerRequestDtoPatch();
        updateCustomerDto.setName("Nome teste");

        when(customerRepository.findById(invalidCustomerId))
                .thenReturn(Optional.ofNullable(any(Customer.class)));

        Assertions.assertThrows(CompiledException.class, () -> customerService.updateCustomer(invalidCustomerId, updateCustomerDto));
    }

    @Test
    void testGivenCustomerDTOThenSaved(){

        Customer customerExpected = CustomerDtoBuilder.builder().build().toCustomer();
        CustomerRequestDto customerToSave = CustomerDtoBuilder.builder().build().toCustomerRequestDto();

        when(customerRepository.save(customerExpected))
                .thenReturn(customerExpected);

        Customer customer = customerService.createCustomer(customerToSave);
        Assertions.assertEquals(customer.getName(), customerExpected.getName());

    }

    @Test
    void testGivenValidCustomerIdAndUpdateInfoThenThrowExceptionOnUpdate(){
        Customer customerExpected = CustomerDtoBuilder.builder().build().toCustomer();
        when(customerRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(customerExpected));

        CustomerRequestDtoPatch customerToUpdate = CustomerDtoBuilder.builder().build().toCustomerRequestDtoPatch();
        customerToUpdate.setName("aa");

        when(customerRepository.save(Mockito.any())).thenReturn(customerExpected);
        Customer customerNotExpected = customerService.updateCustomer(Mockito.anyLong(), customerToUpdate);

        Assertions.assertEquals(customerExpected.getName(),customerNotExpected.getName());
    }

    @Test
    void testValidationOfAMainAddressThenReturnThrowException() {
        CustomerRequestDto customerRequest = CustomerDtoBuilder.builder().build().toCustomerRequestDtoTestMainAddress();
        Assertions.assertThrows(ChooseMoreThanAllowedException.class, () ->  customerService.createCustomer(customerRequest));
    }

    @Test
    void testValidationOfNumberOfAddress() {
        Customer customerRequest = CustomerDtoBuilder.builder().build().toCustomerTestNumberOfAddress();
        Assertions.assertThrows(CompiledException.class, () ->  customerService.countAddress(customerRequest));
    }


}



