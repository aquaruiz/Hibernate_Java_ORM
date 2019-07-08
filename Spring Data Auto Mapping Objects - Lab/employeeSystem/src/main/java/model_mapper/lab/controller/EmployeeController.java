package model_mapper.lab.controller;

import model_mapper.lab.domain.dtos.EmployeeDto;
import model_mapper.lab.domain.entities.Address;
import model_mapper.lab.domain.entities.Employee;
import model_mapper.lab.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController implements CommandLineRunner {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void run(String... args) throws Exception {
        Address address = new Address();
        address.setCity("Plovdiv");
        address.setCountry("Universe");

        Employee employee = new Employee();
        employee.setFirstName("Ivan");
        employee.setLastName("Yonkov");
        employee.setSalary(10000.0);
        employee.setAddress(address);

        ModelMapper modelMapper = new ModelMapper();
//        EmployeeDto employeeDto = modelMapper.map(employee,  EmployeeDto.class);
        int a = 5;
    }
}
