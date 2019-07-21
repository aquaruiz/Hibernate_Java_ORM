package alararestaurant.domain.dtos;

import alararestaurant.domain.enums.OrderType;
import alararestaurant.util.LocalDateTimeAdapter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderImportDto {
    @XmlElement
    private String customer;

    @XmlElement
    private String employee;

    @XmlElement(name = "date-time")
    @XmlJavaTypeAdapter(type = LocalDateTime.class, value = LocalDateTimeAdapter.class)
    private LocalDateTime dateTime;

    @XmlElement
    @Enumerated(value = EnumType.STRING)
    private OrderType type;

    @XmlElement(name = "items")
    private ItemRootDto itemRootDto;

    public OrderImportDto() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public ItemRootDto getItemRootDto() {
        return itemRootDto;
    }

    public void setItemRootDto(ItemRootDto itemRootDto) {
        this.itemRootDto = itemRootDto;
    }
}