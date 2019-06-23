import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

public class Engine implements Runnable {

	private final EntityManager entityManager;

	public Engine(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public void run() {
		String task = null;
		try {
			task = readInput("Choose A Task Number to Run: ");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		switch (task) {
		case "2":
			// Task 2
			this.removeObjects();
			break;
		case "3":
			// Task 3
			try {
				this.containsEmployee();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "4":
			// Task 4
			this.employeesWithSalaryOver();
			break;
		case "5":
			// Task 5
			this.employeesFromDepartment();
			break;
		case "6":
			// Task 6
			try {
				this.addAddressAndUpdateEmployee();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "7":
			// Task 7
			this.addressesWithEmployeeCount();
			break;
		case "8":
			// Task 8
			try {
				this.getEmployeesWithProject(); 
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "9":
			// Task 9
			this.findLatest10Projects();
			break;
		case "10":
			// Task 10
			this.increaseSalary();
			break;
		case "11":
			// Task 11
			try {
				this.removeTowns();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "12":
			// Task 12
			try {
				this.findEmployeeByFirstName();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			break;
		case "13":
			// Task 13
			this.employeesMaxSalary();
		default:
			System.out.println("Choose again: ");
			run();
			break;
		}
		
		try {
			String nextTask = readInput("Wanna continue: Y/N ? ");
			switch (nextTask.toLowerCase()) {
			case "y":
				run();
				break;
			default:
				System.out.println("Bye");
				this.entityManager.close();
				return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void removeObjects() {
		this.entityManager.getTransaction().begin();
		List<Town> towns = this.entityManager
				.createQuery("FROM Town", Town.class).getResultList();
		
		for (Town town : towns) {
			if (town.getName().length() > 5) {
				this.entityManager.detach(town);
			}
		}

		towns.forEach(t -> t.setName(t.getName().toLowerCase()));
		
		this.entityManager.getTransaction().commit();
	}

	private void containsEmployee() throws IOException {
		String employeeName = readInput("Enter Name and Last Name: ");
		
//		Scanner scanner = new Scanner(System.in);
//		String employeeName = bReader.readLine();
		
		this.entityManager.getTransaction().begin();
		
		try {
			this.entityManager
				.createQuery("FROM Employee WHERE CONCAT(firstName, ' ',  lastName) = :name", Employee.class)
				.setParameter("name", employeeName)
				.getSingleResult();

//			Employee emp = (Employee) this.entityManager
//				.createNativeQuery("SELECT * FROM employees WHERE CONCAT(first_name, ' ', last_name) = ?;")
//				.getSingleResult();
			
			this.entityManager.getTransaction().commit();

			System.out.println("Yes");
		} catch (Exception e) {
			System.out.println("No");
		}
	}

	private void employeesWithSalaryOver() {
		BigDecimal maxSalary = new BigDecimal(50000);
		this.entityManager.getTransaction().begin();
		
		List<Employee> employees = this.entityManager
				.createQuery("FROM Employee WHERE salary > :maxSalary", Employee.class)
				.setParameter("maxSalary", maxSalary)
				.getResultList();
		
		employees.forEach(empl -> System.out.println(empl.getFirstName()));
		
		this.entityManager.getTransaction().commit();;
	}

	private void employeesFromDepartment() {
		String departmentString = "Research and Development";
		
		this.entityManager.getTransaction().begin();
		
		List<Employee> employees = this.entityManager
				.createQuery("FROM Employee "
						+ "WHERE department.name = :name "
						+ "ORDER BY salary, id", Employee.class)
				.setParameter("name", departmentString)
				.getResultList();
		
		employees.forEach(empl -> System.out.printf("%s %s from %s - $%s%n",
				empl.getFirstName(),
				empl.getLastName(),
				empl.getDepartment().getName(),
				empl.getSalary()));
		
		this.entityManager.getTransaction().commit();;
	}

	private void addAddressAndUpdateEmployee() throws IOException {
		String lastName = readInput("Enter Employee Last Name: ");
//		String addressString = "bul. Vitosha 150";
		String addressString = "Vitoshka 15";
		
		Address newAddress = new Address();
		newAddress.setText(addressString);
		
		this.entityManager.getTransaction().begin();
		Town currentTown = this.entityManager
				.createQuery("FROM Town WHERE name = 'Sofia'",
				Town.class)
			.getSingleResult();
		
		newAddress.setTown(currentTown);
		this.entityManager.persist(newAddress);
		
		Employee employee = this.entityManager
				.createQuery("FROM Employee WHERE lastName = :lastName", 
					Employee.class)
				.setParameter("lastName", lastName)
				.getSingleResult();
		
		employee.setAddress(newAddress);
		
		this.entityManager.flush(); // ?? commit == auto flush
		this.entityManager.getTransaction().commit();
	}

	private void addressesWithEmployeeCount() {
		this.entityManager.getTransaction().begin();
		
		List<Address> addresses = this.entityManager.createQuery(
					"FROM Address ORDER BY size(employees) DESC, town.id", 
					Address.class)
				.setMaxResults(10)
				.getResultList();
		this.entityManager.getTransaction().commit();
		addresses.forEach(
				a -> System.out.printf(
						"%s, %s - %d employees%n", 
						a.getText(), 
						a.getTown().getName(), 
						a.getEmployees().size())
				);
	}
	
	private void getEmployeesWithProject() throws IOException {
		int employeeId = Integer.parseInt(readInput("Enter Employee ID: "));
		
		this.entityManager.getTransaction().begin();
		
		Employee employee = this.entityManager
				.createQuery("FROM Employee "
						+ "WHERE id = :emplId", Employee.class)
				.setParameter("emplId", employeeId)
				.getSingleResult();
		
		System.out.printf("%s %s - %s%n\t%s",
					employee.getFirstName(),
					employee.getLastName(),
					employee.getJobTitle(),
					employee.getProjects()
						.stream()
						.map(pr -> pr.getName())
						.sorted()
						.collect(Collectors.
									joining(System.lineSeparator()+"\t"))
						);
		
		this.entityManager.getTransaction().commit();
	}

	private void findLatest10Projects() {
		final int projectsCount = 10;
		
		this.entityManager.getTransaction().begin();
		
		List<Project> latestProjects = this.entityManager
				.createQuery("FROM Project "
						+ "ORDER BY startDate DESC, name", Project.class)
				.setMaxResults(projectsCount)
				.getResultList();
		
		latestProjects.stream()
			.sorted((a, b) -> a.getName().compareTo(b.getName()))
			.forEach(pr -> System.out.printf(
					"Project Name: %s%n\tProject Description: %s%n\tProject Start Date: %s%n\tProject End Date: %s%n", 
						pr.getName(),
						pr.getDescription(),
						pr.getStartDate(),
						pr.getEndDate()));

		this.entityManager.getTransaction().commit();
	}
	

	private void increaseSalary() {
		final double increasingRate = 0.12;
		List<String> affectedDepartments = Arrays.asList(
				"Engineering",
				"Tool Design",
				"Marketing",
				"Information Services"
		);

		this.entityManager.getTransaction().begin();
		List<Employee> affectedEmployees = this.entityManager
				.createQuery("FROM Employee "
						+ "WHERE department.name IN (:deps)", Employee.class)
				.setParameter("deps", affectedDepartments)
				.getResultList();
		
		affectedEmployees.stream()
			.forEach(empl -> 
						empl.setSalary(
								(empl.getSalary()
								.multiply(BigDecimal.valueOf(1 + increasingRate))))
					);

		this.entityManager.flush(); // ?? needed
		this.entityManager.getTransaction().commit();

		affectedEmployees.forEach(empl -> 
				System.out.printf("%s %s ($%s)%n", 
						empl.getFirstName(),
						empl.getLastName(),
						empl.getSalary()));
	}
	
	private void removeTowns() throws IOException {
		String townName = readInput("Enter Town Name for Deletion: ");
		
		this.entityManager.getTransaction().begin();
		
		Town townDelete = this.entityManager.createQuery("FROM Town "
				+ "WHERE name = :town", Town.class)
				.setParameter("town", townName)
				.getSingleResult();
		
		List<Address> addressesToDelete = this.entityManager
				.createQuery("FROM Address "
						+ "WHERE town_id = :id", Address.class)
				.setParameter("id", townDelete.getId())
				.getResultList();

		addressesToDelete
			.forEach(t -> t.getEmployees()
						.forEach(em -> em.setAddress(null)));

		addressesToDelete.forEach(t -> this.entityManager.remove(t));
		this.entityManager.remove(townDelete);

		int countDeletedAddresses = addressesToDelete.size();
		System.out.printf("%d address%s in %s deleted", 
				countDeletedAddresses,
				countDeletedAddresses == 1 ? "" : "es" ,
				townName);
		
		this.entityManager.getTransaction().commit();
	}

	private void findEmployeeByFirstName() throws IOException {
		String firstName2Letters = readInput("Enter First 2 Letters from Empoyee's First Name: ");
	
		this.entityManager.getTransaction().begin();
		List<Employee> employees = this.entityManager
				.createQuery("FROM Employee "
						+ "WHERE firstName LIKE CONCAT(:letters, '%')", Employee.class)
				.setParameter("letters", firstName2Letters)
				.getResultList();
		
		employees.forEach(empl-> System.out.printf("%s %s - %s - ($%s)%n",
				empl.getFirstName(),
				empl.getLastName(),
				empl.getJobTitle(),
				empl.getSalary()));
				
		this.entityManager.getTransaction().commit();
	}

	private void employeesMaxSalary() {
		final BigDecimal lowerBoundry = BigDecimal.valueOf(30000);
		final BigDecimal upperBoundry = BigDecimal.valueOf(70000);
		
		this.entityManager.getTransaction().begin();
		var filtereDepartments = this.entityManager
				.createQuery("SELECT department.name, MAX(salary) " +
						"FROM Employee " + 
						"GROUP BY department.name\n" +
						"HAVING MAX(salary) NOT BETWEEN :low AND :up",
					Object[].class)
				.setParameter("low", lowerBoundry)
				.setParameter("up", upperBoundry)
				.getResultList();

//		List<Object[]> filtereDepartments = this.entityManager
//                .createQuery("SELECT e.department.name, MAX(e.salary)" +
//                        "FROM Employee AS e " +
//                        "GROUP BY e.department.id " +
//                        "HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000", Object[].class)
//                .getResultList();

		filtereDepartments.forEach(empl -> System.out.println(empl[0] + " - " + empl[1]));
		
		this.entityManager.getTransaction().commit();
	}

	private String readInput(String promptMsgString) throws IOException {
		System.out.print(promptMsgString);
		
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		return bReader.readLine();
	}
}
