package ex.xml.domain.dtos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierRootDto {
    @XmlElement(name = "supplier")
    private SupplierDto[] suppliers;

    public SupplierRootDto() {
    }

    public SupplierRootDto(SupplierDto[] suppliers) {
        this.suppliers = suppliers;
    }

    public SupplierDto[] getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(SupplierDto[] suppliers) {
        this.suppliers = suppliers;
    }
}