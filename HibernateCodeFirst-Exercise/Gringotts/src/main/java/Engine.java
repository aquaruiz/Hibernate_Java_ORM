import javax.persistence.EntityManager;

public class Engine implements Runnable{
	private final EntityManager entityManager;
	
	public Engine(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@Override
	public void run() {
		this.entityManager.getTransaction().begin();

		this.entityManager.getTransaction().commit();
		this.entityManager.clear();
	}
}
