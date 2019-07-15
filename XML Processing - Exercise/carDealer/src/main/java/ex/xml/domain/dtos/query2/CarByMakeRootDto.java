package ex.xml.domain.dtos.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarByMakeRootDto {
    @XmlElement(name = "car")
    List<CarByMakeDto> cars;

    public CarByMakeRootDto() {
    }

    public List<CarByMakeDto> getCars() {
        return cars;
    }

    public void setCars(List<CarByMakeDto> cars) {
        this.cars = cars;
    }
}
