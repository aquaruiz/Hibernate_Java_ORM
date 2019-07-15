package ex.xml.domain.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "parts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartRootDto {
    @XmlElement(name = "part")
    PartDto[] parts;

    public PartRootDto() {
    }

    public PartDto[] getParts() {
        return parts;
    }

    public void setParts(PartDto[] parts) {
        this.parts = parts;
    }
}