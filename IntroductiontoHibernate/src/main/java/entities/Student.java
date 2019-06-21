package entities;
import java.util.Date;

import javax.persistence.*;

// JPA
@Entity 
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name", length = 50)
    private String name;
    @Column(name = "registration_date")
    private Date registrationDate;
    
    public Student(String string, Date date) {
    	setName(string);
    	setRegistrationDate(date);
    }

	public Student() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
    
    public void setId(int id) {
		this.id = id;
	}
    
    public String getName() {
		return name;
	}
    
    public void setName(String name) {
		this.name = name;
	}

    public Date getRegistrationDate() {
		return registrationDate;
	}
    
    public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
    
    @Override
    	public String toString() {
    		return "Student{id=" + id + ", name=" + name + ", registrationDate=" + registrationDate + "}";
    	}
}