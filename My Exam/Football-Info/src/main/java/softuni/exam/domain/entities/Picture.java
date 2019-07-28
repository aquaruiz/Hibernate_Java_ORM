package softuni.exam.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity(name = "pictures")
@Table(name = "pictures")
public class Picture extends BaseEntity {
    @Column(nullable = false)
    /** Bankin said it's not mandatory to use name="" inside @Column
     * if there is no name change
     * I believe he has right
     */
    @NotNull
    private String url;

    @OneToMany(mappedBy = "picture")
    private List<Team> teams;

    @OneToMany(mappedBy = "picture")
    private List<Player> players;

    public Picture() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}