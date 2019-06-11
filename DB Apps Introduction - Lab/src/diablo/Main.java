package diablo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "1234");
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo?serverTimezone=UTC", props);
		PreparedStatement stmt = connection
				.prepareStatement("SELECT us.user_name, us.first_name, us.last_name, COUNT(us_g.game_id) AS 'games' FROM users us\n" + 
						"INNER JOIN users_games AS us_g\n" + 
						"ON us.id = us_g.user_id\n" + 
						"WHERE user_name LIKE ?\n" + 
						"GROUP BY us.id;");
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter player name: ");
		String user = scanner.nextLine().trim();
		scanner.close();
		stmt.setString(1, user);
		
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			System.out.printf("User: %s%n%s %s has played %d games", 
					rs.getString("user_name"),
					rs.getString("first_name"),
					rs.getString("last_name"),
					rs.getInt("games"));
		} else {
			System.out.println("No such user exists");
		}
	}

}
