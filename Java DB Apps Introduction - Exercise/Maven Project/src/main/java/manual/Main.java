package manual;

import java.sql.*;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws SQLException {
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "1234");
		
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/minions_db?serverTimezone=UTC", props);

		Engine engine = new Engine(connection);
		engine.run();
	}

}
