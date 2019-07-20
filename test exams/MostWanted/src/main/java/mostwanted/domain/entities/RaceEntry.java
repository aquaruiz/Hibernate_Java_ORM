package mostwanted.domain.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "raceEntries")
@Table(name = "race_entries")
public class RaceEntry extends BaseEntity {
    @Column(name = "has_finished")
    private boolean hasFinished;

    @Column(name = "finish_time")
    private double finishTime;

    @ManyToOne(targetEntity = Car.class)
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne(targetEntity = Racer.class)
    @JoinColumn(name = "racer_id")
    private Racer racer;

    @ManyToMany(mappedBy = "entries")
    private List<Race> races;

    public RaceEntry() {
        this.races = new ArrayList<>();
    }

    public boolean isHasFinished() {
        return hasFinished;
    }

    public void setHasFinished(boolean hasFinished) {
        this.hasFinished = hasFinished;
    }

    public double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(double finishTime) {
        this.finishTime = finishTime;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Racer getRacer() {
        return racer;
    }

    public void setRacer(Racer racer) {
        this.racer = racer;
    }

    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }
}