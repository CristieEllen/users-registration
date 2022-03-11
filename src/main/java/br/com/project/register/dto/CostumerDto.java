package br.com.project.register.dto;

import br.com.project.register.entities.Costumer;
import br.com.project.register.enums.Types;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class CostumerDto {
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String cellphone;
    private Types type;

    public CostumerDto(Costumer costumer) {

        this.id = costumer.getId();
        this.name = costumer.getName();
        this.cpf = costumer.getCpf();
        this.email = costumer.getEmail();
        this.cellphone = costumer.getCellphone();
        this.type = costumer.getType();
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

    public Types getType() {
        return type;
    }

}
