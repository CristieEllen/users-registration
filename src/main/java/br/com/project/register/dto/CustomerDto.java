package br.com.project.register.dto;

import br.com.project.register.entities.Customer;
import br.com.project.register.enums.CustomerTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerDto {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String cellphone;
    private CustomerTypes type;

    private List<AddressDto> addresses;

    public CustomerDto(Customer costumer) {

        this.id = costumer.getId();
        this.name = costumer.getName();
        this.cpf = costumer.getCpf();
        this.email = costumer.getEmail();
        this.cellphone = costumer.getCellphone();
        this.type = costumer.getCustomerType();
        this.addresses = new ArrayList<>(5);
        this.addresses.addAll(costumer.getAddresses().stream().map(AddressDto::new).collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public CustomerTypes getType() {
        return type;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }
}
