import java.util.Date;
import java.util.HashSet;

import javax.persistence.EntityManager;

import entities.Course;
import entities.Student;
import entities.Teacher;

public class Engine implements Runnable{
	private final EntityManager entityManager;
	
	public Engine(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void run() {
		this.entityManager.getTransaction().begin();

	    Teacher teacher = new Teacher();
        teacher.setFirstName("Trawis");
        teacher.setLastName("Williams");
        teacher.setPhoneNumber("123456789");
        teacher.setEmail("williams@gmail.com");
        teacher.setCourses(new HashSet<>());

        Student student = new Student();
        student.setFirstName("Pesho");
        student.setLastName("Peshev");
        student.setPhoneNumber("123456789");
        student.setCourses(new HashSet<>());
        student.setAttendance(12);

        Course course = new Course();
        course.setName("Java");
        course.setDescription("too hard to believe in");
        course.setStartDate(new Date());
        course.setTeacher(teacher);
        course.setStudents(new HashSet<>());

        Course course2 = new Course();
        course2.setName("JS");
        course2.setDescription("too easy");
        course2.setStartDate(new Date());
        course2.setTeacher(teacher);
        course2.setStudents(new HashSet<>());

        teacher.getCourses().add(course);
        teacher.getCourses().add(course2);
        course.getStudents().add(student);
        course2.getStudents().add(student);
        student.getCourses().add(course);
        student.getCourses().add(course2);
		
        entityManager.persist(teacher);
        entityManager.persist(student);
        entityManager.persist(course);
        entityManager.persist(course2);

		this.entityManager.getTransaction().commit();
		this.entityManager.close();
	}
}