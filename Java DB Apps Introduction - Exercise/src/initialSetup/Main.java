package initialSetup;

import java.sql.*;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws SQLException {
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "1234");
		
		Connection connection = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/minions_db?serverTimezone=UTC", props);
		
		PreparedStatement stmt1 =  connection.prepareStatement("INSERT INTO towns (name, country)\n" + 
				"VALUES ('Kazanlak', 'Bulgaria'),\n" + 
				"	('Sydney', 'Australia'),\n" + 
				"	('Skopje', 'Macedonia'),\n" + 
				"	('Kiew', 'Ukraine'),\n" + 
				"	('unknown', 'Universe');\n"); 
		PreparedStatement stmt2 =  connection.prepareStatement("INSERT INTO villains (name, evilness_factor)\n" + 
				"VALUES	('Bunny Bee', 'super evil'),\n" + 
				"	('Total Annihilation', 'evil'),\n" + 
				"	('Pink Panter', 'good'),\n" + 
				"	('Granny Smith', 'evil'),\n" + 
				"	('God', 'good');\n");
		PreparedStatement stmt3 =  connection.prepareStatement("INSERT INTO minions (name, age, town_id)\n" + 
				"VALUES\n" + 
				"	('Pancake', 0, 5),\n" + 
				"	('Strawberry', 18, 1),\n" + 
				"	('Strauss', 22, 12),\n" + 
				"	('Pikatchu', 5, 5),\n" + 
				"	('Meself', 42, 11);\n"); 
		PreparedStatement stmt4 =  connection.prepareStatement("INSERT INTO minions_villains\n" + 
				"VALUES\n" + 
				"	(1, 1),\n" + 
				"	(2, 3),\n" + 
				"	(4, 4),\n" + 
				"	(5, 5);\n");
				
		stmt1.execute();
		stmt2.execute();
		stmt3.execute();
		stmt4.execute();
	}
}