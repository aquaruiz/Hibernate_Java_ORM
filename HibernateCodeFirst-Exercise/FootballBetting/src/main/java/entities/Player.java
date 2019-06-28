package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "players")
public class Player extends BaseName implements Serializable {
	@Column(name = "squad_number")
	private String squadNumber;
	
	@ManyToOne(targetEntity = Team.class, cascade = CascadeType.ALL)
	private Team team;
	
	@ManyToOne(targetEntity = Position.class, cascade = CascadeType.ALL)
	private Position position;
	
	@Column(name = "is_currently_injured")
	private boolean isCurrentlyInjured;
	
	public Player() {
	}

	public String getSquadNumber() {
		return squadNumber;
	}

	public void setSquadNumber(String squadNumber) {
		this.squadNumber = squadNumber;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean isCurrentlyInjured() {
		return isCurrentlyInjured;
	}

	public void setCurrentlyInjured(boolean isCurrentlyInjured) {
		this.isCurrentlyInjured = isCurrentlyInjured;
	}
}