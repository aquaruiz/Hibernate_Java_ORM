package ex.xml.domain.dtos;

import ex.xml.util.LocalDateTimeAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerDto {
    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(type = LocalDateTime.class, value = LocalDateTimeAdapter.class)
    private LocalDateTime birthDate;

    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;

    public CustomerDto() {
    }

    public CustomerDto(String name, LocalDateTime birthDate, boolean isYoungDriver) {
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
//        this.birthDate = LocalDateTime.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

//    public void setBirthDate(LocalDateTime birthDate) {
//        this.birthDate = birthDate;
////        this.birthDate = LocalDateTime.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
//    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}