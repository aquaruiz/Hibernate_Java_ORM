package entities.labels;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import entities.shampoos.BasicShampoo;

@SuppressWarnings("serial")
@Entity
@Table(name = "labels")
public class BasicLabel implements Label{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Basic
	private String title;
	@Basic
	private String subtitle;

	@OneToOne(
			mappedBy = "label", 
			targetEntity = BasicShampoo.class, 
			fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL
		)
	private BasicShampoo basicShampoo;	
	
	protected BasicLabel() {

	}
	
	public BasicLabel(String title, String subtitle) {
		setTitle(title);
		setSubtitle(subtitle);
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSubtitle() {
		return subtitle;
	}
	
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public BasicShampoo getBasicShampoo() {
		return basicShampoo;
	}

	public void setBasicShampoo(BasicShampoo basicShampoo) {
		this.basicShampoo = basicShampoo;
	}
}