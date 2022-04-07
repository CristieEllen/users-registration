package br.com.project.register.dto.request;

import br.com.project.register.annotations.Document;
import br.com.project.register.entities.Address;
import br.com.project.register.entities.Customer;
import br.com.project.register.enums.CustomerTypes;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRequestDtoPost {

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 10, max = 100, message = "Min: 10, Max: 100")
    private String name;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!")
    @Pattern(regexp = "^(((\\d{3}).(\\d{3}).(\\d{3})-(\\d{2}))?((\\d{2}).(\\d{3}).(\\d{3})/(\\d{4})-(\\d{2}))?)*$", message = "CPF: 000.000.000-00 / CNPJ: 00.000.000/0000-00")
    @Document
    private String documentNumber;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Email(message = "Email inválido!")
    private String email;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 11, max= 11, message = "Digite apenas o número do DDD e do telefone sem pontuação.")
    private String cellphone;

    @NotNull(message = "Preenchimento obrigatório, o campo não pode ser nulo!")
    private CustomerTypes customerType;

    @Valid @NotNull @Size(min=1, max=5, message = "Adicione pelo menos 1 e no máximo 5 endereços.")
    private final List<AddressRequestDtoPost> addresses = new ArrayList<>();


    public String getName() {
        return name;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public CustomerTypes getCustomerType() {
        return customerType;
    }

    public List<AddressRequestDtoPost> getAddresses() {
        return addresses;
    }

    public Customer converter() {
        List<Address> address = new ArrayList<>();

        for(AddressRequestDtoPost addForm: addresses) {
            address.add(addForm.converterAddress());
        }
        return new Customer(name, documentNumber, email, cellphone, customerType, address);
    }

    @Override
    public String toString() {
        return "CustomerForm{" +
                "name='" + name + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", email='" + email + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", customerType=" + customerType +
                ", addressFormList=" + addresses +
                '}';
    }
}
