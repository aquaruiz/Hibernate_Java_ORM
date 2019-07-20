package mostwanted.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "racer")
//@Entity(name = "racers")
@Table(name = "racer")
//@Table(name = "racers")
public class Racer extends BaseEntity{
    @Column(nullable = false, unique = true)
    private  String name;

    private int age;

    @Column(columnDefinition = "DECIMAL(19,2)")
    private double bounty;

    @ManyToOne(targetEntity = Town.class)
    @JoinColumn(name = "town_id")
    private Town homeTown;

    @OneToMany(mappedBy = "racer")
    private Set<Car> cars;

    @OneToMany(mappedBy = "racer")
    private List<RaceEntry> entries;

    public Racer() {
        this.cars = new HashSet<>();
        this.entries = new ArrayList<>();
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

    public Town getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(Town homeTown) {
        this.homeTown = homeTown;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public List<RaceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<RaceEntry> entries) {
        this.entries = entries;
    }
}