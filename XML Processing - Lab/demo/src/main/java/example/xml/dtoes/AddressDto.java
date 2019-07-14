package example.xml.dtoes;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "address")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AddressDto {
//    @XmlElement(name = "country")
    @XmlAttribute(name = "country")
    private String country;
    @XmlElement(name = "city")
//    @XmlAttribute(name = "city")
    private String city;

    private AddressDto address;

    public AddressDto() {
    }

    public AddressDto(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}