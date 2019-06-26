import javax.persistence.EntityManager;

import entities.ingredients.AmmoniumChloride;
import entities.ingredients.Lavender;
import entities.ingredients.Mint;
import entities.ingredients.Nettle;
import entities.labels.BasicLabel;
import entities.shampoos.FiftyShades;
import entities.shampoos.FreshNuke;
import entities.shampoos.PinkPanter;

public class Engine implements Runnable{
	private final EntityManager entityManager;
	
	public Engine(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void run() {
		this.entityManager.getTransaction().begin();

		AmmoniumChloride amCh = new AmmoniumChloride();
		Nettle nettle = new Nettle();
		Mint mint = new Mint();
		Lavender lavender = new Lavender();

		BasicLabel label = new BasicLabel("badass", "beee");
		BasicLabel label2 = new BasicLabel("Cool", "begam");
		BasicLabel label3 = new BasicLabel("bad", "gone");

		FiftyShades fs = new FiftyShades(label);
		fs.addIngredient(mint);
		fs.addIngredient(amCh);

		FreshNuke nuke = new FreshNuke(label2);
		nuke.addIngredient(amCh);
		nuke.addIngredient(lavender);

		PinkPanter pp = new PinkPanter(label3);
		pp.addIngredient(lavender);
		pp.addIngredient(amCh);
		pp.addIngredient(nettle);

		entityManager.persist(fs);
		entityManager.persist(nuke);
		entityManager.persist(pp);

		this.entityManager.getTransaction().commit();
		this.entityManager.clear();
	}
}