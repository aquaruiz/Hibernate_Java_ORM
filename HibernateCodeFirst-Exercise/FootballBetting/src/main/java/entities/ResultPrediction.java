package entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "result_predictions")
public class ResultPrediction extends BaseId {
	@Enumerated(EnumType.STRING)
	private Prediction prediction;
	
	public ResultPrediction() {
	}

	public Prediction getPrediction() {
		return prediction;
	}
	
	public void setPrediction(Prediction prediction) {
		this.prediction = prediction;
	}
}
