package br.com.project.register.entities;

import br.com.project.register.dto.request.CustomerRequestDtoPost;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    private Boolean principalAddress;

    @ManyToOne
    @JoinColumn(name = "costumer_id") @NotNull
    private Customer customer;

    public Address(){
    }

    public Address(String name, String number, String district, String city, String cep, String state, Boolean principalAddress) {

        this.name = name;
        this.number = number;
        this.district = district;
        this.city = city;
        this.cep = cep;
        this.state = state;
        this.principalAddress = principalAddress;

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

    public Boolean getPrincipalAddress() {
        return principalAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @PreRemove
    public void removeCustomer(){
        customer.removeAddress(id);
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", district='" + district + '\'' +
                ", city='" + city + '\'' +
                ", cep='" + cep + '\'' +
                ", state='" + state + '\'' +
                ", principalAddress=" + principalAddress +
                ", customer=" + customer +
                '}';
    }
}
