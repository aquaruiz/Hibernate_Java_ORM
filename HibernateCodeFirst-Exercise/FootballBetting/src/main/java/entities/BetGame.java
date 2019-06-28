package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "bets_games")
public class BetGame implements Serializable {
	@Id
	@ManyToOne(targetEntity = Game.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "game_id")
	private Game game;
	
	@Id
	@ManyToOne(targetEntity = Bet.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "bet_id")
	private Bet bet;
	
	@OneToOne(targetEntity = ResultPrediction.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "result_prediction")
	private ResultPrediction resultPrediction;
	
	public BetGame() {
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}

	public ResultPrediction getResultPrediction() {
		return resultPrediction;
	}

	public void setResultPrediction(ResultPrediction resultPrediction) {
		this.resultPrediction = resultPrediction;
	}
}