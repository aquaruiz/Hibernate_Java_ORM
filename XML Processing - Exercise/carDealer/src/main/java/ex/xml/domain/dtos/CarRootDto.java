package ex.xml.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarRootDto {
    @XmlElement(name = "car")
    CarDto[] cars;

    public CarRootDto() {
    }

    public CarDto[] getCars() {
        return cars;
    }

    public void setCars(CarDto[] cars) {
        this.cars = cars;
    }
}