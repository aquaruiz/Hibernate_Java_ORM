
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.text.Position.Bias;

import org.hibernate.Session;
import entities.Student;
import utils.HibernateUtils;

public class Main {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
//        Session session = HibernateUtils.getSession();
//        session.beginTransaction();
//
////        Student student = new Student();
////        student.setName("Gosho");
////        student.setRegistrationDate(new Date());
////        
////        Student myStudent = session.get(Student.class, 1);
////        
////        System.out.println(myStudent);
////        session.save(student);
//
////        session.createQuery("FROM Student").list().forEach(System.out::println);;
////        session.createQuery("FROM Student WHERE name='Pesho'").list().forEach(System.out::println);;
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery query = builder.createQuery(Student.class);
////        query.from(Student.class);
//        
//        // WHERE
//        
//        Root<Student> studentRoot = query.from(Student.class);
//        query.select(studentRoot).where(builder.or(
//        		builder.like(studentRoot.get("name"), "Pesho"),
//        			builder.like(studentRoot.get("name"), "P%"))
//        		);
//        
//        
//        session.createQuery(query).getResultList().forEach(System.out::println);
//        
//        session.getTransaction().commit();
//        session.close();
//        
        // JPA
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("school");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Student student = new Student("Teo", new Date());
        em.persist(student);
        Student studentToRemove = em.find(Student.class, 2);
        em.remove(studentToRemove);
        em.getTransaction().commit();
        
    }
}