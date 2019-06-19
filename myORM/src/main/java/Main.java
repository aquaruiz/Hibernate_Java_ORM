import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import entities.User;
import orm.Connector;
import orm.EntityManager;

public class Main {
	public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Connector.createConnect("root", "1234", "users");
		
		Connection connection = Connector.getConnection();
		
		@SuppressWarnings("unused")
		User user = new User("username", "12345", 12, Date.valueOf("2019-05-06"));
		
		EntityManager<User> userManager = new EntityManager<User>(connection);
		userManager.persist(user); // insert??
		user = userManager.findFirst(User.class);
		user.setPassword("23456");
		user.setUsername("username2");
		
		userManager.persist(user); // update??
		User found = userManager.findFirst(User.class, "username='username2'");
		System.out.println(found.getPassword());
		
		userManager.find(User.class)
		.forEach(u -> System.out.println(u.getRegistrationDate()));
		
		userManager.delete("id = 2");
	}
}