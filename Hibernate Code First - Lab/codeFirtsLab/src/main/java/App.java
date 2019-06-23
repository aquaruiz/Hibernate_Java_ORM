

import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import entities.Mother;
import entities.Person;
import entities.Student;
import entities.Teacher;

public class App 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
    	EntityManager entityManager = factory.createEntityManager();
    	
    	Person[] people = {
    			new Student("Pesho", 3),
    			new Teacher("Gosho", "Java")
    	};
    	
    	inTransaction(entityManager, 
    			() -> Arrays.stream(people)
        			.forEach(entityManager::persist)); 

    	Mother mother = new Mother("Gia");
    	mother.setChild(people[0]);
    	
    	inTransaction(entityManager, () -> entityManager.persist(mother)); 
    	
    	Mother mother1 = entityManager.find(Mother.class, 3);
    	Person person = mother1.getChild();
    	Mother mother2 = person.getMother();
    	
    	System.out.println(mother1);
    	
    	CriteriaQuery<Teacher> query = entityManager.getCriteriaBuilder()
    			.createQuery(Teacher.class);

//    	Root<Teacher> root = query.from(Teacher.class);
//    	query.select(root);
    	
//    	List<Teacher> teachers = entityManager.createQuery(query).getResultList();
//    	entityManager.getTransaction().commit();
    	
//    	teachers.forEach(te -> System.out.println(te.getName()));

    	query.from(Teacher.class);
    	entityManager.createQuery(query)
    		.getResultList()
    		.forEach(te -> System.out.println(te.getName()));
    	
    	entityManager.close();
    }
    
    private static void inTransaction(EntityManager entityManager, Runnable runnable) {
    	entityManager.getTransaction().begin();
    	runnable.run();
    	entityManager.getTransaction().commit();
	}
}