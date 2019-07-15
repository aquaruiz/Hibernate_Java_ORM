package ex.xml.domain.dtos.query5;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class TotalCustomersRootDto {
    @XmlElement(name = "customer")
    List<TotalCustomerSalesDto> customers;

    public TotalCustomersRootDto() {
    }

    public List<TotalCustomerSalesDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<TotalCustomerSalesDto> customers) {
        this.customers = customers;
    }
}