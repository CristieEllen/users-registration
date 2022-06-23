package br.com.project.register.dto.response;

import br.com.project.register.entities.Address;

public class AddressResponseDto {

    private Long id;
    private String name;
    private String number;
    private String district;
    private String city;
    private String cep;
    private String state;
    private Boolean principalAddress;

    public AddressResponseDto(Address address) {
        this.id = address.getId();
        this.name = address.getName();
        this.number = address.getNumber();
        this.district = address.getDistrict();
        this.city = address.getCity();
        this.cep = address.getCep();
        this.state = address.getState();
        this.principalAddress = address.getPrincipalAddress();
    }

    public AddressResponseDto(Long id, String name, String number, String district, String city, String cep, String state, Boolean principalAddress) {
        this.id = id;
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
