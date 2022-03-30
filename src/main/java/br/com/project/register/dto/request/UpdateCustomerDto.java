package br.com.project.register.dto.request;

import br.com.project.register.entities.Customer;
import br.com.project.register.repositories.CustomerRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class UpdateCustomerDto {

    @Length(min = 10, max = 100, message = "Min: 10, Max: 100")
    private String name;

    @Email(message = "Email inválido!")
    private String email;

    @Length(min = 11, max= 11, message = "Digite apenas o número do DDD e do telefone sem pontuação.")
    private String cellphone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }


    public Customer update(Long id, CustomerRepository customerRepository) {
        Customer customer = customerRepository.getById(id);

        customer.setName(this.name);
        customer.setEmail(this.email);
        customer.setCellphone(this.cellphone);

        return customer;
    }
}
