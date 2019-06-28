package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "rounds")
public class Round extends BaseName implements Serializable{
	public Round() {
	}
}
