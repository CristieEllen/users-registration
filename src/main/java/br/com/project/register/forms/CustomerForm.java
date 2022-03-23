package br.com.project.register.forms;

import br.com.project.register.dto.CustomerDto;
import br.com.project.register.entities.Address;
import br.com.project.register.entities.Customer;
import br.com.project.register.enums.CustomerTypes;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerForm {

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Size(min = 10, max = 100)
    private String name;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Size(min= 11, max = 14)
    private String cpf;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Email(message = "Email inválido!")
    private String email;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Size(min = 11)
    private String cellphone;

    @NotNull
    private CustomerTypes customerType;

    private List<AddressForm> addressFormList = new ArrayList<>();


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public CustomerTypes getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerTypes customerType) {
        this.customerType = customerType;
    }

    public List<AddressForm> getAddressFormList() {
        return addressFormList;
    }

    public void setAddressFormList(List<AddressForm> addressFormList) {
        this.addressFormList = addressFormList;
    }


    public Customer converter() {
        List<Address> addresses = new ArrayList<>();

        for( AddressForm addForm: addressFormList) {
            addresses.add(addForm.converterAddress());
        }
        return new Customer(name, cpf, email, cellphone, customerType, addresses);
    }

    @Override
    public String toString() {
        return "CustomerForm{" +
                "name='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", customerType=" + customerType +
                ", addressFormList=" + addressFormList +
                '}';
    }
}
