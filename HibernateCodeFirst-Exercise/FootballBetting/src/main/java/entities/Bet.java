package entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "bets")
public class Bet extends BaseId {
	@Column(name = "bet_money")
	private double betMoney;
	
	@Column(name = "date_and_time_of_bet")
	private Date dateAndTimeOfBet;
	
	@ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
	private User user;
	
	public Bet() {
	}

	public double getBetMoney() {
		return betMoney;
	}

	public void setBetMoney(double betMoney) {
		this.betMoney = betMoney;
	}

	public Date getDateAndTimeOfBet() {
		return dateAndTimeOfBet;
	}

	public void setDateAndTimeOfBet(Date dateAndTimeOfBet) {
		this.dateAndTimeOfBet = dateAndTimeOfBet;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
