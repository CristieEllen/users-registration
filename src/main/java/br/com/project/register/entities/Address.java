package br.com.project.register.entities;

import javax.persistence.*;

@Entity
@Table(name = "tb_addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer number;
    private String district;
    private String city;
    private String cep;
    private String state;

    @ManyToOne
    private Customer customer;

    public Address(){
    }

    public Address(Long id, String name, Integer number, String district, String city, String cep, String state) {
        this.id = id;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer costumer) {
        this.customer = costumer;
    }
}
