package ex.xml.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerRootDto {
    @XmlElement(name = "customer")
    CustomerDto[] customers;
    public CustomerRootDto() {
    }

    public CustomerDto[] getCustomers() {
        return customers;
    }

    public void setCustomers(CustomerDto[] customers) {
        this.customers = customers;
    }
}