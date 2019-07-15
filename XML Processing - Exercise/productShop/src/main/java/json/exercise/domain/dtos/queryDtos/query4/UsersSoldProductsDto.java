package json.exercise.domain.dtos.queryDtos.query4;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersSoldProductsDto {
    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlAttribute(name = "age")
    private int age;

    @XmlElement(name = "sold-products")
    private SoldProductsDto sells;

    public UsersSoldProductsDto() {
        this.sells = new SoldProductsDto();
    }

    public UsersSoldProductsDto(String firstName, String lastName, int age, SoldProductsDto sells) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sells = sells;
    }

    public SoldProductsDto getSells() {
        return sells;
    }

    public void setSells(SoldProductsDto sells) {
        this.sells = sells;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}