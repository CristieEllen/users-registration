package br.com.project.register.factorys;

import br.com.project.register.dto.request.CustomerRequestDtoPatch;
import br.com.project.register.entities.Address;
import br.com.project.register.entities.Customer;
import br.com.project.register.enums.CustomerTypes;

import java.util.ArrayList;
import java.util.List;

public class CustomerFactory {

    public static Customer defaultCustomer(){
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address("José Antônio","223", "Vila Nova", "Bananeira", "12345678","São Paulo" , true));
        return new Customer("João Cesaro", "586.425.720-60", "joao@email.com", "12912342345", CustomerTypes.valueOf("PF"), addresses);
    }

    public static Customer defaultCustomerWithoutAddress(){
        List<Address> addresses = new ArrayList<>();
        return new Customer("João Cesaro", "586.425.720-60", "joao@email.com", "12912342345", CustomerTypes.valueOf("PF"), addresses);
    }

    public static CustomerRequestDtoPatch defaultCustomerPatch(){
        return new CustomerRequestDtoPatch("José Maria Santos", null, null);
    }
}
