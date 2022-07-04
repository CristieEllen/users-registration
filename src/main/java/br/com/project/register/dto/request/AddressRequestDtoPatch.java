package br.com.project.register.dto.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class AddressRequestDtoPatch {


    @Length(min = 5,max = 80, message = "Mín 5, Max: 80 caracteres.")
    private String name;

    @Range(min = 1, max = 2000)
    private String number;

    @Length(min = 5,max = 50, message = "Mín 5, Max: 50 caracteres.")
    private String district;

    @Length(min = 8,max = 8, message = "Digite o cep sem a pontuação.")
    private String cep;

    public AddressRequestDtoPatch(String name, String number, String district, String cep) {
        this.name = name;
        this.number = number;
        this.district = district;
        this.cep = cep;
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

    public String getCep() {
        return cep;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "AddressRequestDtoPost{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", district='" + district + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }

}
