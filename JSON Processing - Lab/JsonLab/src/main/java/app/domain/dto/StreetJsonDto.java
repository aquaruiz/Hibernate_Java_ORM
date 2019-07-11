package app.domain.dto;

import com.google.gson.annotations.Expose;

public class StreetJsonDto {
    @Expose
    private long id;

    @Expose
    private String name;

    @Expose
    private  int number;

    public StreetJsonDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}