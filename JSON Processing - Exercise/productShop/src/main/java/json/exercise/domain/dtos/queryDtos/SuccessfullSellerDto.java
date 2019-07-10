package json.exercise.domain.dtos.queryDtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class SuccessfullSellerDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private List<SoldProductDto> soldProducts;

    public SuccessfullSellerDto() {
    }

    public SuccessfullSellerDto(String firstName, String lastName, List<SoldProductDto> soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.soldProducts = soldProducts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SoldProductDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<SoldProductDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}