package spring_intro.user_system.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "towns")
public class Town extends BaseId {
    private String name;
    private String country;
    private Set<User> bornUsers;
    private Set<User> currentlyLivingUsers;

    public Town() {
        this.bornUsers = new HashSet<>();
        this.currentlyLivingUsers = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @OneToMany(mappedBy = "bornTown")
    public Set<User> getBornUsers() {
        return bornUsers;
    }

    public void setBornUsers(Set<User> bornUsers) {
        this.bornUsers = bornUsers;
    }

    @OneToMany(mappedBy = "currentlyLivingTown")
    public Set<User> getCurrentlyLivingUsers() {
        return currentlyLivingUsers;
    }

    public void setCurrentlyLivingUsers(Set<User> currentlyLivingUsers) {
        this.currentlyLivingUsers = currentlyLivingUsers;
    }
}