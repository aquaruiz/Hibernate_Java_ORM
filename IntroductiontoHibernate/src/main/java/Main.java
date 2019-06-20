
import java.util.Date;

import org.hibernate.Session;

import entities.Student;
import utils.HibernateUtils;

public class Main {
	public static void main(String[] args) {
        Session session = HibernateUtils.getSession();
        session.beginTransaction();

        Student student = new Student();
        student.setName("Pesho");
        student.setRegistrationDate(new Date());
        
        Student myStudent = session.get(Student.class, 1);
//        session.save(student);
        session.getTransaction().commit();
        session.close();
    }
}