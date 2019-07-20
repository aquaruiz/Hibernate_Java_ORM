package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;

public class RacerImportDto {
    @Expose
    private String name;

    @Expose
    private int age;

    @Expose
    private double bounty;

    @Expose
    private String homeTown;

    public RacerImportDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getBounty() {
        return bounty;
    }

    public void setBounty(double bounty) {
        this.bounty = bounty;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown;
    }
}