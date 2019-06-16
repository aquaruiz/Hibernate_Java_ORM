package entities;

import java.util.Date;

import orm.annotations.Column;
import orm.annotations.Entity;
import orm.annotations.Id;

@Entity
public class User {
	@Id
	private int id;
	
	@Column
	private String username;

	@Column
	private String password;
	
	@Column
	private int age;
	
	@Column
	private Date registration;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String username, String password, int age, Date registration) {
		this.username = username;
		this.password = password;
		this.age = age;
		this.registration = registration;
	}
	
	public String getUsername() {
		return username;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	public int getAge() {
		return age;
	}
	
	public Date getRegistration() {
		return registration;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public void setRegistration(Date registration) {
		this.registration = registration;
	}
}