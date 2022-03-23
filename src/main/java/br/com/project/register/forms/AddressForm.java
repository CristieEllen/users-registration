package br.com.project.register.forms;

import br.com.project.register.entities.Address;



public class AddressForm {

    private String name;


    private Integer number;


    private String district;

    private String city;


    private String cep;


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
