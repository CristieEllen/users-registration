package br.com.project.register.entities;


import br.com.project.register.forms.AddressForm;

import javax.persistence.*;

@Entity
@Table(name = "tb_addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String number;
    private String district;
    private String city;
    private String cep;
    private String state;

    public Address(){
    }

    public Address(String name, String number, String district, String city, String cep, String state) {
        this.name = name;
        this.number= number;
        this.district = district;
        this.city = city;
        this.cep = cep;
        this.state = state;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number= number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "Address{" +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", cep='" + cep + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
