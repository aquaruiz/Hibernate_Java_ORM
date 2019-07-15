package ex.xml.domain.dtos.query4;

import com.google.gson.annotations.Expose;
import ex.xml.domain.dtos.CarDto;

import java.util.List;

public class CarPartDto {
    @Expose
    private CarDto car;

    @Expose
    private List<SimplePartDto> parts;

    public CarPartDto() {
    }

    public CarPartDto(CarDto car, List<SimplePartDto> parts) {
        this.car = car;
        this.parts = parts;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public List<SimplePartDto> getParts() {
        return parts;
    }

    public void setParts(List<SimplePartDto> parts) {
        this.parts = parts;
    }
}