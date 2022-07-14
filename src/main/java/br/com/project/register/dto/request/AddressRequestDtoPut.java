package br.com.project.register.dto.request;

public class AddressRequestDtoPut {

    private Boolean principalAddress;

    public AddressRequestDtoPut() {
    }
    public AddressRequestDtoPut(Boolean principalAddress) {
        this.principalAddress = principalAddress;
    }

    public Boolean getPrincipalAddress() {
        return principalAddress;
    }

    public void setPrincipalAddress(Boolean principalAddress) {
        this.principalAddress = principalAddress;
    }

    @Override
    public String toString() {
        return "AddressRequestDtoPut{" +
                "principalAddress=" + principalAddress +
                '}';
    }
}
