package entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "colors")
public class Color extends BaseName {
	public Color() {
	}
}
