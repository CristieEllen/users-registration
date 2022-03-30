package br.com.project.register.entities;

import br.com.project.register.enums.CustomerTypes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String documentNumber;
    private String email;
    private String cellphone;

    @Enumerated(EnumType.STRING)

    private CustomerTypes customerType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public Customer() {
    }

    public Customer(String name, String documentNumber, String email, String cellphone, CustomerTypes customerType, List<Address> addresses) {
        this.name = name;
        this.documentNumber = documentNumber;
        this.email = email;
        this.cellphone = cellphone;
        this.customerType = customerType;
        this.addresses = addresses;

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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
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

    public CustomerTypes getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerTypes customerType) {
        this.customerType = customerType;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", email='" + email + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", customerType=" + customerType +
                ", addresses=" + addresses +
                '}';
    }

}