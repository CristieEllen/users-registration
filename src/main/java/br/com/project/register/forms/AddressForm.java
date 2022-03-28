package br.com.project.register.forms;

import br.com.project.register.entities.Address;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AddressForm {

    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 5,max = 80, message = "Mín 5, Max: 80 caracteres.")
    private String name;

    @NotNull @Range(min = 1, max = 2000)
    private String number;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 5,max = 50, message = "Mín 5, Max: 50 caracteres.")
    private String district;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 5,max = 50, message = "Mín 5, Max: 50 caracteres.")
    private String city;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 8,max = 8, message = "Digite o cep sem a pontuação.")
    private String cep;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 5,max = 50, message = "Digite o nome da cidade por extenso.")
    private String state;


    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getCep() {
        return cep;
    }

    public String getState() {
        return state;
    }

    public Address converterAddress() {
        return new Address(name, number, district, city, cep, state);
    }

    @Override
    public String toString() {
        return "AddressForm{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", cep='" + cep + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
