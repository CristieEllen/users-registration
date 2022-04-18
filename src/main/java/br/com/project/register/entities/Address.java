package br.com.project.register.entities;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 5,max = 80, message = "Mín 5, Max: 80 caracteres.")
    private String name;

    @NotNull @Range(min = 1, max = 2000) @NotEmpty
    private String number;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 5,max = 50, message = "Mín 5, Max: 50 caracteres.")
    private String district;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 5,max = 50, message = "Mín 5, Max: 50 caracteres.")
    private String city;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 8,max = 8, message = "Digite o cep sem a pontuação.")
    private String cep;

    @NotNull @NotEmpty(message = "Preenchimento obrigatório!") @Length(min = 5,max = 50, message = "Digite o nome da cidade por extenso.")
    private String state;

    @NotNull(message = "Preenchimento obrigatório!")
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

    public void setPrincipalAddress(Boolean principalAddress) {
        this.principalAddress = principalAddress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setAllPrincipalAddress(){
        customer.setFalseAllAddresss();

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
                '}';
    }
}
