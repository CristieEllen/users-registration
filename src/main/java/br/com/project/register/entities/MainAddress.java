package br.com.project.register.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_mainaddress")
public class MainAddress {

    @EmbeddedId
    private MainAddressPK id = new MainAddressPK();
    private Integer mainAddress;

    public MainAddress(){
    }

    public void setCostumer(Costumer costumer){
        id.setCostumer(costumer);
    }

    public void setAddress(Address address){
        id.setAddress(address);
    }

    public MainAddressPK getId() {
        return id;
    }

    public void setId(MainAddressPK id) {
        this.id = id;
    }

    public Integer getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(Integer mainAddress) {
        this.mainAddress = mainAddress;
    }
}
