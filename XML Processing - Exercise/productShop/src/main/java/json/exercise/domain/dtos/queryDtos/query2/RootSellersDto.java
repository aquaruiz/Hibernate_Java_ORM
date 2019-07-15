package json.exercise.domain.dtos.queryDtos.query2;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootSellersDto {
    @XmlElement(name = "user")
    private List<SuccessfullSellerDto> sellers;

    public RootSellersDto() {
        this.sellers = new ArrayList<>();
    }

    public RootSellersDto(List<SuccessfullSellerDto> sellers) {
        this.sellers = sellers;
    }

    public List<SuccessfullSellerDto> getSellers() {
        return sellers;
    }

    public void setSellers(List<SuccessfullSellerDto> sellers) {
        this.sellers = sellers;
    }
}