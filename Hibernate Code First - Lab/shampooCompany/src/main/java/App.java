import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.ingredients.AmmoniumChloride;
import entities.ingredients.BasicIngredient;
import entities.ingredients.Mint;
import entities.ingredients.Nettle;
import entities.labels.BasicLabel;
import entities.shampoos.BasicShampoo;
import entities.shampoos.FiftyShades;
import entities.shampoos.FreshNuke;

public class App 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("shampoo");
    	EntityManager entityManager = factory.createEntityManager();
    	
    	entityManager.getTransaction().begin();
    	
    	BasicIngredient am = new AmmoniumChloride();
    	BasicIngredient mint = new Mint();
    	BasicIngredient nettle = new Nettle();
    	
    	BasicLabel label = new  BasicLabel("Title", "subs");
    	BasicShampoo nuke = new FreshNuke(label);
    	BasicShampoo shades = new FiftyShades(label);
    	
    	nuke.getIngredients().add(am);
    	nuke.getIngredients().add(mint);
    	
    	shades.getIngredients().add(mint);
    	shades.getIngredients().add(nettle);
    	
    	entityManager.persist(nuke);
    	entityManager.persist(shades);
    	
    	entityManager.getTransaction().commit();
    	entityManager.close();
    }
}
