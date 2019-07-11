package json.exercise.domain.dtos.queryDtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class SuccessfullSellerDto {
    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private List<SoldProductDto> sells;

    public SuccessfullSellerDto() {
    }

    public SuccessfullSellerDto(String firstName, String lastName, List<SoldProductDto> sells) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.sells = sells;
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

    public List<SoldProductDto> getSells() {
        return sells;
    }

    public void setSells(List<SoldProductDto> sells) {
        this.sells = sells;
    }

    public void addSoldProduct(SoldProductDto soldProduct) {
        if (this.sells.contains(soldProduct)){
            return;
        }

        this.sells.add(soldProduct);
    }

    public void removeSoldProduct(SoldProductDto soldProduct) {
        if (!this.sells.contains(soldProduct)){
            return;
        }

        this.sells.remove(soldProduct);
    }
}