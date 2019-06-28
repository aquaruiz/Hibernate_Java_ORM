package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "player_statistics")
public class PlayerStatistic implements Serializable {
	@Id
	@ManyToOne(targetEntity = Game.class)
	@JoinColumn(name = "game_id")
	private Game game;
	
	@Id
	@ManyToOne(targetEntity =  Player.class)
//	@JoinColumn(name = "player_id")
	private Player player;
	
	@Column(name = "scored_goals")
	private int scoredGoals;
	
	@Column(name = "player_assist")
	private Player  playerAssists;
	
	@Column(name = "played_minutes_during_game")
	private int playedMinutesDuringGame;
}