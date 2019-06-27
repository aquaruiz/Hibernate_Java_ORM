import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EntityManager;

import entities.Customer;
import entities.Product;
import entities.Sale;
import entities.StoreLocation;

public class Engine implements Runnable{
	private final EntityManager entityManager;
	
	public Engine(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void run() {
		this.entityManager.getTransaction().begin();

		Customer customer = new Customer();
	    customer.setEmail("pesho@mail.bg");
	    customer.setName("Pesho Peshev");
	    customer.setCreditCardNumber("0000000000999999");
	
	    StoreLocation storeLocation = new StoreLocation();
	    storeLocation.setLocationName("NoWhere");
	
	    Product product = new Product();
	    product.setName("beer");
	    product.setPrice(BigDecimal.valueOf(0.69));
	    product.setQuantity(10.5);
	
	    Sale sale = new Sale();
	    sale.setCustomer(customer);
	    sale.setProduct(product);
	    sale.setStoreLocation(storeLocation);
	    sale.setDate(new Date());
	    
	    entityManager.persist(customer);
	    entityManager.persist(product);
	    entityManager.persist(storeLocation);
	    entityManager.persist(sale);

		this.entityManager.getTransaction().commit();
		this.entityManager.clear();
	}
}