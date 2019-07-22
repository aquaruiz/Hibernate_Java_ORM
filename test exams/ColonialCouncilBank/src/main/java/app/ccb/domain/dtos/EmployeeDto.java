package app.ccb.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class EmployeeDto {
    @Expose
    @SerializedName("full_name")
    private String fullName;

    private String firstName;

    private String lastName;

    @Expose
    private String salary;

    @Expose
    @SerializedName("started_on")
    private LocalDate startedOn;

    @Expose
    @SerializedName("branch_name")
    private String branchName;

    public EmployeeDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public LocalDate getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(LocalDate startedOn) {
        this.startedOn = startedOn;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getFirstName() {
        return fullName.split("\\s+")[0];
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return fullName.split("\\s+")[1];
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}