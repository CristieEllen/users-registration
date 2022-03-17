package br.com.project.register.enums;

public enum CustomerTypes {
    PF("PF"),
    PJ("PJ");

    private final String customerType;

    private CustomerTypes(String customerType ){
        this.customerType = customerType;
    }

    private String getCustomerType(){
        return this.customerType;
    }


}
