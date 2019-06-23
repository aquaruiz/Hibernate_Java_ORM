

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

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
}