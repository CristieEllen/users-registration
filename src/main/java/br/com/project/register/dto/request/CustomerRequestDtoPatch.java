package br.com.project.register.dto.request;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class CustomerRequestDtoPatch {

    @Length(min = 10, max = 100, message = "Min: 10, Max: 100")
    private String name;

    @Email(message = "Email inválido!")
    private String email;

    @Length(min = 11, max= 11, message = "Digite apenas o número do DDD e do telefone sem pontuação.")
    private String cellphone;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCellphone() {
        return cellphone;
    }

    @Override
    public String toString() {
        return "CustomerRequestDtoPatch{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cellphone='" + cellphone + '\'' +
                '}';
    }
}
