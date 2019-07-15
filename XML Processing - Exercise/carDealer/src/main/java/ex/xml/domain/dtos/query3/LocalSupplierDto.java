package ex.xml.domain.dtos.query3;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocalSupplierDto {
    @XmlAttribute(name = "id")
    private Integer id;

    @XmlAttribute(name = "name")
    private String name;

    @XmlAttribute(name = "parts-count")
    private long partsCount;

    public LocalSupplierDto() {
    }

    public LocalSupplierDto(Integer id, String name, long partsCount) {
        this.id = id;
        this.name = name;
        this.partsCount = partsCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(long partsCount) {
        this.partsCount = partsCount;
    }
}