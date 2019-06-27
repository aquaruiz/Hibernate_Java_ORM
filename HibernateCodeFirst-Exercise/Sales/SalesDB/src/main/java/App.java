import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("sales");
    	EntityManager entityManager = factory.createEntityManager();
    	    	
    	Runnable runnable = new Engine(entityManager);
    	runnable.run();	
    }
}
