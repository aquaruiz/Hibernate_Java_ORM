package ex.xml.domain.dtos.query1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderedCustomerRootDto {
    @XmlElement(name = "customer")
    List<OrderedCustomerDto> customers;

    public OrderedCustomerRootDto() {
    }

    public List<OrderedCustomerDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<OrderedCustomerDto> customers) {
        this.customers = customers;
    }
}