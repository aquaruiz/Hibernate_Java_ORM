package app.domain.dto;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class AddressJsonDto {
    @Expose
    private String country;

    @Expose
    private String city;

    @Expose
    private List<StreetJsonDto> streets;

    public AddressJsonDto() {
        this.streets = new ArrayList<>();
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public List<StreetJsonDto> getStreets() {
        return streets;
    }

    public void setStreets(List<StreetJsonDto> streets) {
        this.streets = streets;
    }

    public void addStreet(StreetJsonDto street) {
        this.streets.add(street);
    }
}
