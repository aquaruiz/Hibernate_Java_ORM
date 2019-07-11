package json.ex.domain.dtos;

import com.google.gson.annotations.Expose;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomerDto {
    @Expose
    private String name;

    @Expose
    private String birthDate;

    @Expose
    private boolean isYoungDriver;

    public CustomerDto() {
    }

    public CustomerDto(String name, String birthDate, boolean isYoungDriver) {
        this.name = name;
        this.birthDate = birthDate;
        this.isYoungDriver = isYoungDriver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
//        this.birthDate = LocalDateTime.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
    }

//    public void setBirthDate(LocalDateTime birthDate) {
//        this.birthDate = birthDate;
////        this.birthDate = LocalDateTime.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
//    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}