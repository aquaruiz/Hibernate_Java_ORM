package entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "students")
public class Student extends Person {
	@Column(nullable = false)	
	private int grade;
	
	public Student() {
		super();
	}
	
	public Student(String name, int grade) {
		super(name);
		this.setGrade(grade);
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
}
