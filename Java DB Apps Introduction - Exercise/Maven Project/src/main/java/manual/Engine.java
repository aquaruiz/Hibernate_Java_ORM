package manual;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Engine implements Runnable{
	Connection connection;
	Scanner scanner;
	
	public Engine(Connection connection) {
		this.connection = connection;
		this.scanner = new Scanner(System.in);
	}

	@Override
	public void run() {
		try {
			// Task 1
//			this.createInititalTables();

			// Task 2
//			this.getVillainsNames();

			// Task 3
//			this.getMinionName();
			
			// Task 4
//			this.addMinion();
			
			// Task 5
//			this.changeTownNamesCasing();

			// Task 6
//				this.removeVillain();

			// Task 7
//			this.printAllMinionNames();
			
			// Task 8
			this.increaseMinionsAge();
			
			// Task 9
//			this.increaseAgeByStoredProcedure();
			
//			this.scanner.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void getVillainsNames() throws SQLException {
		PreparedStatement stmt = this.connection
				.prepareStatement("SELECT vil.name, COUNT(mini.id) AS 'minions_count' FROM villains vil\n" + 
						"INNER JOIN minions_villains min_vil\n" + 
						"ON vil.id = min_vil.villain_id\n" + 
						"INNER JOIN minions mini\n" + 
						"ON min_vil.minion_id = mini.id\n" + 
						"GROUP BY vil.id\n" + 
						"HAVING minions_count > 15\n" +
						"ORDER BY minions_count DESC");
		ResultSet resultSet = stmt.executeQuery();
		this.printOutResult(resultSet, "name", "minions_count");
	}

	private void printOutResult(ResultSet resultSet, String... args) throws SQLException {
		while (resultSet.next()) {
			for (String string : args) {
				System.out.print(resultSet.getString(string) + " ");
			}
			
			System.out.println();
		}
	}

	@SuppressWarnings("unused")
	private void getMinionName() throws SQLException {
		PreparedStatement stmt1 = connection
				.prepareStatement("SELECT CONCAT('Villain: ', name) AS 'name' FROM villains\n" + 
						"WHERE id = ?;");
		
		PreparedStatement stmt2 = connection
				.prepareStatement("SELECT CONCAT(FORMAT((@rownum := @rownum + 1), 0), '.') AS 'row_num', mini.id, mini.name, mini.age FROM villains vil\n" + 
						"INNER JOIN minions_villains min_vil\n" + 
						"ON vil.id = min_vil.villain_id\n" + 
						"INNER JOIN minions mini\n" + 
						"ON min_vil.minion_id = mini.id\n" + 
						"CROSS JOIN (SELECT @rownum := 0) r\n" + 
						"WHERE vil.id = ?");
		
		String minionId = scanner.nextLine();
		
		stmt1.setString(1, minionId);
		ResultSet resultSet1 = stmt1.executeQuery();

		stmt2.setString(1, minionId);
		ResultSet resultSet2 = stmt2.executeQuery();
		
		if (resultSet1.isBeforeFirst()) {
			this.printOutResult(resultSet1, "name");
			this.printOutResult(resultSet2, "row_num", "name", "age");
		} else {
			System.out.printf("No villain with ID %s exists in the database.", minionId).println();
		}
	}

	@SuppressWarnings("unused")
	private void addMinion() throws SQLException {
		String[] minionString = this.scanner.nextLine().split("\\s+");
		String minionName = minionString[1];
		String minionAge = minionString[2];
		String minionTown = minionString[3];
		
		String[] villainString = this.scanner.nextLine().split("\\s+");
		String villainName = villainString[1];		
		
		PreparedStatement stmtTown = this.connection
				.prepareStatement("SELECT * FROM towns\n" + 
						"WHERE name LIKE ?;");
		
		stmtTown.setString(1, minionTown);
		ResultSet resultSetTown = stmtTown.executeQuery();
		
		String townId = null;
		
		if (resultSetTown.next()) {
			townId = resultSetTown.getString("id");
		} else {
			PreparedStatement stmtInsertTown = this.connection
					.prepareStatement("INSERT INTO towns (name, country)\n" + 
							"VALUES (?, 'unknown');");
			stmtInsertTown.setString(1, minionTown);
			
			stmtInsertTown.execute();
			
			System.out.printf("Town %s was added to the database.",
					minionTown).println();;

			stmtTown = this.connection
					.prepareStatement("SELECT * FROM towns\n" + 
					"WHERE name LIKE ?;");

			stmtTown.setString(1, minionTown);
			resultSetTown = stmtTown.executeQuery();

			if (resultSetTown.next()) {
				townId = resultSetTown.getString("id");
			}
		}

		PreparedStatement stmtVillain = this.connection
				.prepareStatement("SELECT * FROM villains\n"  + 
						"WHERE name LIKE ?;");
		stmtVillain.setString(1, villainName);
		
		ResultSet resultSetVillain = stmtVillain.executeQuery();
		
		String villainId = null;
		
		if (resultSetVillain.next()) {
			villainId = resultSetVillain.getString("id");
		} else {
			PreparedStatement stmtInsertVillain = this.connection
					.prepareStatement("INSERT INTO villains (name, evilness_factor)\n" + 
							"VALUES (?, 'evil');");
			
			stmtInsertVillain.setString(1, villainName);
			stmtInsertVillain.execute();
			
			System.out.printf("Villain %s was added to the database.",
					villainName).println();;
					
			resultSetVillain = stmtVillain.executeQuery();
			if (resultSetVillain.next()) {
				villainId = resultSetVillain.getString("id");
			}
		}

		PreparedStatement stmtInsertMinion = this.connection
				.prepareStatement("\n" + 
						"INSERT INTO minions (name, age, town_id)\n" + 
						"VALUES (?, ?, ?);");
		stmtInsertMinion.setString(1, minionName);
		stmtInsertMinion.setString(2, minionAge);
		stmtInsertMinion.setString(3, resultSetTown.getString("id"));
		
		stmtInsertMinion.execute();

		PreparedStatement stmtMinion = this.connection
				.prepareStatement("SELECT * FROM minions\n" + 
						"WHERE name LIKE ? AND age = ? AND town_id = ?;");
		stmtMinion.setString(1, minionName);
		stmtMinion.setString(2, minionAge);
		stmtMinion.setString(3, townId);
		
		ResultSet resultSetMinion = stmtMinion.executeQuery();
		
		String minionId = null;
		if (resultSetMinion.next()) {
			minionId = resultSetMinion.getString("id");
		}
		
		PreparedStatement stmtVillainGetMinion = this.connection
				.prepareStatement("INSERT INTO minions_villains (minion_id, villain_id)\n" + 
						"VALUES (?, ?);");
		
		stmtVillainGetMinion.setString(1, minionId);
		stmtVillainGetMinion.setString(2, villainId);
		
		stmtVillainGetMinion.execute();
		
		System.out.printf("Successfully added %s to be minion of %s.",
				minionName,
				villainName).println();;
	}

	@SuppressWarnings("unused")
	private void changeTownNamesCasing() throws SQLException {
		String countryName = scanner.nextLine();
		
		PreparedStatement selectTownsStmt = this.connection
				.prepareStatement("SELECT COUNT(*) AS 'count' FROM towns\n" + 
						"WHERE country LIKE ?;");
		selectTownsStmt.setString(1, countryName);
		ResultSet selectResultSet = selectTownsStmt.executeQuery();
		
		if (selectResultSet.next()) {
			int affectedTownCount = selectResultSet.getInt("count");

			if (affectedTownCount > 0) {
				System.out.printf("%s town names were affected.", selectResultSet.getString("count")).println();
				
				PreparedStatement updateNamesStmt = this.connection
						.prepareStatement("UPDATE towns\n" + 
								"SET name = UPPER(name)\n" + 
								"WHERE country LIKE ?;");
				
				updateNamesStmt.setString(1, countryName);
				updateNamesStmt.execute();
				
				PreparedStatement getAffectedTownNames = this.connection
						.prepareStatement("SELECT * FROM towns\n" + 
								"WHERE country LIKE ?;");
				getAffectedTownNames.setString(1, countryName);
				
				ResultSet affectedTownsTeResultSet = getAffectedTownNames.executeQuery();
				System.out.print("[");
				ArrayList<String> outputTownNames = new ArrayList<String>();
				while (affectedTownsTeResultSet.next()) {
					outputTownNames.add(affectedTownsTeResultSet.getString("name"));
				}
				
				System.out.print(outputTownNames.stream().collect(Collectors.joining(", ")));
				System.out.println("]");
			} else {				
				System.out.println("No town names were affected.");
			}
		}
	}

	@SuppressWarnings("unused")
	private void removeVillain() throws SQLException {
		String villainId = scanner.nextLine();
		
		PreparedStatement villainNameStmt = this.connection
				.prepareStatement("SELECT * FROM villains\n" + 
						"WHERE id = ?;");
		villainNameStmt.setString(1, villainId);
		ResultSet villainNameResultSet = villainNameStmt.executeQuery();
		
		if (villainNameResultSet.next()) {
			String villainName = villainNameResultSet.getString("name");
			System.out.printf("%s was deleted", villainName).println();;
			
			PreparedStatement getMinionsCountStmt = this.connection
					.prepareStatement("SELECT COUNT(*) AS 'count' FROM minions_villains\n" + 
							"WHERE villain_id = ?;");
			getMinionsCountStmt.setString(1, villainId);
			ResultSet minionsCountResultSet = getMinionsCountStmt.executeQuery();
			
			if (minionsCountResultSet.next()) {
				System.out.printf("%s minions released", minionsCountResultSet.getString("count")).println();;
			}
			
			PreparedStatement deleteVillainMinionsStmt = this.connection
					.prepareStatement("DELETE FROM minions_villains\n" + 
							"WHERE villain_id = ?;");
			deleteVillainMinionsStmt.setString(1, villainId);
			deleteVillainMinionsStmt.execute();
			
			PreparedStatement deleteVillainStmt = this.connection
					.prepareStatement("DELETE FROM villains\n" + 
							"WHERE id = ?;");
			
			deleteVillainStmt.setString(1, villainId);
			deleteVillainStmt.execute();
		} else {
			System.out.println("No such villain was found");
		}
	}

	@SuppressWarnings("unused")
	private void printAllMinionNames() throws SQLException {
		PreparedStatement getALLMinionNamesStmt = this.connection
				.prepareStatement("SELECT name FROM minions;");
		ResultSet allMinionsResultSet = getALLMinionNamesStmt.executeQuery();
		
		ArrayList<String> minions = new ArrayList<String>();
		
		while (allMinionsResultSet.next()) {
			minions.add(allMinionsResultSet.getString("name"));
		}
		
		String[] orderedMinions = new String[minions.size()];
		
		int index = 0;
		for (int i = 0; i < minions.size() / 2; i++) {
			orderedMinions[index++] = minions.get(i);
			try {
				orderedMinions[index++] = minions.get(minions.size() - 1 - i);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		System.out.println(Arrays.stream(orderedMinions)
				.collect(Collectors.joining(System.lineSeparator())));
	}

	private void increaseMinionsAge() {
		String[] minionIds = scanner.nextLine().split("\\s+");
		
		// TODO ...
	}

	private void increaseAgeByStoredProcedure() {
		// TODO Auto-generated method stub
		
	}


	private void createInititalTables() throws SQLException {
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