package ex.xml.domain.dtos.query4;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarPartRootDto {
    @XmlElement(name = "part")
    List<SimplePartDto> parts;

    public CarPartRootDto() {
    }

    public List<SimplePartDto> getParts() {
        return parts;
    }

    public void setParts(List<SimplePartDto> parts) {
        this.parts = parts;
    }
}