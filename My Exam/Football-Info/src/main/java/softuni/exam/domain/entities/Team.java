package softuni.exam.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity(name = "teams")
@Table(name = "teams")
public class Team extends BaseEntity {
    @Column(nullable = false, length = 20)
    @Size(min = 3, max = 20)
    @NotNull
    private String name;

    @ManyToOne(targetEntity = Picture.class)
    @JoinColumn(name = "picture_id", nullable = false)
    @NotNull
    private Picture picture;

    @OneToMany(mappedBy = "team")
    Set<Player> players;

    public Team() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}