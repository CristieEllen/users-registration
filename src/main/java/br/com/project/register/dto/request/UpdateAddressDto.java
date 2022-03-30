package br.com.project.register.dto.request;

import br.com.project.register.entities.Address;
import br.com.project.register.repositories.AddressRepository;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class UpdateAddressDto {

    @Length(min = 5,max = 80, message = "Mín 5, Max: 80 caracteres.")
    private String name;

    @Range(min = 1, max = 2000)
    private String number;

    @Length(min = 5,max = 50, message = "Mín 5, Max: 50 caracteres.")
    private String district;

    @Length(min = 5,max = 50, message = "Mín 5, Max: 50 caracteres.")
    private String city;

    @Length(min = 8,max = 8, message = "Digite o cep sem a pontuação.")
    private String cep;

    @Length(min = 5,max = 50, message = "Digite o nome da cidade por extenso.")
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

    public Address update(Long id, AddressRepository addressRepository) {
        Address address = addressRepository.getById(id);

        address.setName(this.name);
        address.setNumber(this.number);
        address.setCity(this.city);
        address.setCep(this.cep);
        address.setDistrict(this.district);
        address.setState(this.state);

        return address;
    }
}