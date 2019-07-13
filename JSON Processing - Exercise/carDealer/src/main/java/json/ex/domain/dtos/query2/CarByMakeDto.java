package json.ex.domain.dtos.query2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CarByMakeDto {
    @Expose
    @SerializedName(value = "Id")
    private Integer id;

    @Expose
    @SerializedName(value = "Make")
    private String make;

    @Expose
    @SerializedName(value = "Model")
    private String model;

    @Expose
    @SerializedName(value = "TravelledDistance")
    private Long travelledDistance; // in km

    public CarByMakeDto() {
    }

    public CarByMakeDto(Integer id, String make, String model, Long travelledDistance) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}