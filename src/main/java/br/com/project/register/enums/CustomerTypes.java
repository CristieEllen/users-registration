package br.com.project.register.enums;

public enum CustomerTypes {
    PF("Pessoa Fisica"),
    PJ("Pessoa Juridica");

    private final String customerType;

    private CustomerTypes(String customerType ){
        this.customerType = customerType;
    }

    private String getCustomerType(){
        return this.customerType;
    }


}
