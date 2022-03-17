package br.com.project.register.forms;

import br.com.project.register.entities.Customer;
import br.com.project.register.enums.CustomerTypes;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerForm {

    @NotNull @NotEmpty @Size(min = 10, max = 100)
    private String name;

    @NotNull @NotEmpty @Size(min= 11, max = 14)
    private String cpf;

    @NotNull @NotEmpty
    private String email;

    @NotNull @NotEmpty @Size(min = 11)
    private String cellphone;

    @NotNull
    private CustomerTypes customerType;

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

    public Customer converter() {
        return new Customer(name, cpf, email, cellphone, customerType);
    }
}
