package alararestaurant.domain.entities;

import alararestaurant.domain.enums.OrderType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
@Table(name = "orders")
public class Order extends BaseEntity {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String customer;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "type", nullable = false)
    private OrderType type = OrderType.ForHere;

    @ManyToOne(targetEntity = Employee.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public Order() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}