package br.com.project.register.forms;

import br.com.project.register.entities.Address;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class AddressForm {

    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório!")
    private String name;

    @NotNull
    private Integer number;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!")
    private String district;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!")
    private String city;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!")
    private String cep;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!")
    private String state;


    public String getName() {
        return name;
    }

    public Integer getNumber() {
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
