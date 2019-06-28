package entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "games")
public class Game extends BaseId{
	@OneToOne(targetEntity = Team.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "home_team")
	private Team homeTeam;
	
	@OneToOne(targetEntity = Team.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "away_team")
	private Team awayTeam;
	
	@Column(name = "home_goals")
	private int homeGoals;
	
	@Column(name = "away_goals")
	private int awayGoals;
	
	@Column(name = "date_and_time_of_game")
	private Date dateTimeGame;
	
	@Column(name = "home_team_win_bet_rate")
	private double homeTeamWinBetRate;
	
	@Column(name = "away_team_win_bet_rate")
	private double  awayTeamWinBetRate;
	
	@Column(name = "draw_game_bet_rate")
	private double drawGameBetRate;
	
	@ManyToOne(targetEntity = Round.class, cascade = CascadeType.ALL)
	private Round round;
	
	@ManyToOne(targetEntity = Competition.class, cascade = CascadeType.ALL)
	private Competition competition;
	
	public Game() {
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(int homeGoals) {
		this.homeGoals = homeGoals;
	}

	public int getAwayGoals() {
		return awayGoals;
	}

	public void setAwayGoals(int awayGoals) {
		this.awayGoals = awayGoals;
	}

	public Date getDateTimeGame() {
		return dateTimeGame;
	}

	public void setDateTimeGame(Date dateTimeGame) {
		this.dateTimeGame = dateTimeGame;
	}

	public double getHomeTeamWinBetRate() {
		return homeTeamWinBetRate;
	}

	public void setHomeTeamWinBetRate(double homeTeamWinBetRate) {
		this.homeTeamWinBetRate = homeTeamWinBetRate;
	}

	public double getAwayTeamWinBetRate() {
		return awayTeamWinBetRate;
	}

	public void setAwayTeamWinBetRate(double awayTeamWinBetRate) {
		this.awayTeamWinBetRate = awayTeamWinBetRate;
	}

	public double getDrawGameBetRate() {
		return drawGameBetRate;
	}

	public void setDrawGameBetRate(double drawGameBetRate) {
		this.drawGameBetRate = drawGameBetRate;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
		this.round = round;
	}

	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}
}