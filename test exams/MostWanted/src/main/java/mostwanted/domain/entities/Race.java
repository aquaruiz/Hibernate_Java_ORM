package mostwanted.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "races")
@Table(name = "races")
public class Race extends BaseEntity {
    @Column(nullable = false)
    private int laps = 0;

    @ManyToOne(targetEntity = District.class)
    @JoinColumn(name = "district_id")
    private District district;

    @ManyToMany(targetEntity = RaceEntry.class, fetch = FetchType.EAGER)
    @JoinTable(name = "races_raceEntries")
    private List<RaceEntry> entries;

    public Race() {
        this.entries = new ArrayList<>();
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<RaceEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<RaceEntry> entries) {
        this.entries = entries;
    }
}