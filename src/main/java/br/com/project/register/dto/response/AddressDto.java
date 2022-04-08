package br.com.project.register.dto.response;

import br.com.project.register.entities.Address;

public class AddressDto {

    private Long id;
    private String name;
    private String number;
    private String district;
    private String city;
    private String cep;
    private String state;
    private Boolean principalAddress;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.name = address.getName();
        this.number = address.getNumber();
        this.district = address.getDistrict();
        this.city = address.getCity();
        this.cep = address.getCep();
        this.state = address.getState();
        this.principalAddress = address.getPrincipalAddress();
    }

    public Long getId() {
        return id;
    }

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

    public Boolean getPrincipalAddress() {
        return principalAddress;
    }
}
