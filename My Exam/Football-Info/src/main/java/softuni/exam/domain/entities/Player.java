package softuni.exam.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity(name = "players")
@Table(name = "players")
public class Player extends BaseEntity {
    @Column(name = "first_name", nullable = false)
    @NotNull
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 15)
    @NotNull
    @Size(min = 3, max = 15)
    private String lastName;

    /** Bankin said it's not mandatory to use name="" inside @Column
     * if there is no name change
     * I believe he has right
     */
    @Column(nullable = false)
    @NotNull
    @Max(99)
    @Min(1)
    @Positive
    private Integer number;

    @Column(nullable = false)
    @NotNull
    @Min(0)
    @PositiveOrZero
    private BigDecimal salary;

    @Column(nullable = false)
    @NotNull
    private Position position;

    @ManyToOne(targetEntity = Picture.class)
    @JoinColumn(name = "picture_id", nullable = false)
    @NotNull
    private Picture picture;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "team_id", nullable = false)
    @NotNull
    private Team team;

    public Player() {
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }


}