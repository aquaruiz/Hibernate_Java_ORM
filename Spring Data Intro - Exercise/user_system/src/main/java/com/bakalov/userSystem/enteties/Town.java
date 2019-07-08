package com.bakalov.userSystem.enteties;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town extends IdEntity {

    private String name;
    private Country country;

    public Town() {
    }

    @Column(nullable = false, length = 90)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name = "country_id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
