package app.domain.dto;

import com.google.gson.annotations.Expose;

public class AddressJsonDto {
    @Expose
    private String country;

    @Expose
    private String city;

    @Expose
    private String street;

    public AddressJsonDto() {
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

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
