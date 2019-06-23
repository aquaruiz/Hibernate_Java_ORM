

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
    	
    	entityManager.getTransaction().begin();
    	
    	Arrays.stream(people)
    		.forEach(entityManager::persist);

    	entityManager.getTransaction().commit();
    }
}