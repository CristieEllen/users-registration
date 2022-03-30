package br.com.project.register.dto.request;

import br.com.project.register.entities.Address;
import br.com.project.register.entities.Customer;
import br.com.project.register.enums.CustomerTypes;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class CustomerRequestDto {

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 10, max = 100, message = "Min: 10, Max: 100")
    private String name;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Length(min= 11, max = 14, message = "Digite com a pontuação")
    private String documentNumber;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Email(message = "Email inválido!")
    private String email;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 11, max= 11, message = "Digite apenas o número do DDD e do telefone sem pontuação.")
    private String cellphone;

    @NotNull(message = "Preenchimento obrigatório, o campo não pode ser nulo!")
    private CustomerTypes customerType;

    @Valid @NotNull @Size(min=1, max=5, message = "Adicione pelo menos 1 e no máximo 5 endereços.")
    private final List<AddressRequestDto> addresses = new ArrayList<>();


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

    public List<AddressRequestDto> getAddresses() {
        return addresses;
    }

    public Customer converter() {
        List<Address> address = new ArrayList<>();

        for(AddressRequestDto addForm: addresses) {
            address.add(addForm.converterAddress());
        }
        return new Customer(name, documentNumber, email, cellphone, customerType, address);
    }

    public Boolean validationAddress(){
        int sum = 0;
        for (AddressRequestDto add : addresses){
            if(add.getPrincipalAddress()){
                 sum++;
            }
        }
        return sum >= 2;
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
