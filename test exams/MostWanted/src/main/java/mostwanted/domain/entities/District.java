package mostwanted.domain.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "districts")
@Table(name = "districts")
public class District  extends  BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(targetEntity = Town.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "town_id")
    private Town town;

    @OneToMany(mappedBy = "district")
    Set<Race> races;

    public District() {
        this.races = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    public Set<Race> getRaces() {
        return races;
    }

    public void setRaces(Set<Race> races) {
        this.races = races;
    }
}