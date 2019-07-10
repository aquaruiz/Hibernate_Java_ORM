package json.exercise.domain.dtos;

import com.google.gson.annotations.Expose;

public class UserDto {
    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private int age;

    public UserDto() {
    }

    public UserDto(String firstName, String lastName, int age) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAge(age);
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}