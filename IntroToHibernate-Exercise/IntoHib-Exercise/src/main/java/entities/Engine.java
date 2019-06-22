package entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

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
		try {
			// Task 2
			
			
			// Task 3
			try {
//				this.containsEmployee();
			} catch (Exception e) {
				// TODO: handle exception
			}

//			 Task 4
//			this.employeesWithSalaryOver();
			
			// Task 5
//			this.employeesFromDepartment();
			
			// Task 6
			try {
//				this.addAddressAndUpdateEmployee();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			// Task 7
//			this.addressesWithEmployeeCount();
	
			// Task 8
			try {
//				this.getEmployeesWithProject(); 
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			// Task 9
//			this.findLatest10Projects();
			
			// Task 10
//			this.increaseSalary();
			
			// Task 11
			this.removeTowns();
			// Task 12
			// Task 13
//			this.employeesMaxSalary();
		} 
	finally {
			this.entityManager.close();
		}
	}


	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
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


	@SuppressWarnings("unused")
	private void addAddressAndUpdateEmployee() throws IOException {
		String lastName = readInput("Enter Employee Last Name: ");
//		String addressString = "bul. Vitosha 150";
		String addressString = "Vitoshka 15";
		
		Address newAddress = new Address();
		newAddress.setText(addressString);
		
		this.entityManager.getTransaction().begin();
		Town currentTown = this.entityManager.createQuery("FROM Town WHERE name = 'Sofia'",
				Town.class)
			.getSingleResult();
		
		newAddress.setTown(currentTown);
		this.entityManager.persist(newAddress);
		
		Employee employee = this.entityManager.createQuery("FROM Employee WHERE lastName = :lastName", 
					Employee.class)
				.setParameter("lastName", lastName)
				.getSingleResult();
		
		employee.setAddress(newAddress);
		
		this.entityManager.flush(); // ?? commit == auto flush
		this.entityManager.getTransaction().commit();
	}

	@SuppressWarnings("unused")
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
	
	
	@SuppressWarnings("unused")
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

	@SuppressWarnings("unused")
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
		String townToDelete = readInput("Enter Town Name for Deletion: ");
		
		this.entityManager.getTransaction().begin();
		
		List<Town> townsToDelete = this.entityManager.createQuery("", Town.class);
		
		this.entityManager.getTransaction().commit();
	}

	private String readInput(String promptMsgString) throws IOException {
		System.out.print(promptMsgString);
		
		BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
		return bReader.readLine();
	}
}
