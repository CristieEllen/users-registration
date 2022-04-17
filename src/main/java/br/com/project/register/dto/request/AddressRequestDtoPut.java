package br.com.project.register.dto.request;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class AddressRequestDtoPut {

    private Boolean principalAddress;

    public Boolean getPrincipalAddress() {
        return principalAddress;
    }

    @Override
    public String toString() {
        return "AddressRequestDtoPut{" +
                "principalAddress=" + principalAddress +
                '}';
    }
}
