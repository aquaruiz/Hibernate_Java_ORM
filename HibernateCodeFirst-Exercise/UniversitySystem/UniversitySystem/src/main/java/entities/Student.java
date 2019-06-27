package entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student extends BasePerson {
	@Column(name = "average_grade")
	private double averageGrade;
	
	private int attendance;
	
	@ManyToMany
	@JoinTable(name = "students_courses", 
			joinColumns = @JoinColumn(name = "course_id",
										referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "student_id", 
										referencedColumnName = "id")
	)
	private Set<Course> courses;

	public double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
}