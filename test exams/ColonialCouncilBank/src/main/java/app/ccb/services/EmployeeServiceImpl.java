package app.ccb.services;

import app.ccb.config.Constants;
import app.ccb.domain.dtos.EmployeeDto;
import app.ccb.domain.entities.Branch;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.BranchRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.apache.logging.log4j.util.StringBuilders;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private BranchRepository branchRepository;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private Gson gson;
    private ModelMapper modelMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               BranchRepository branchRepository,
                               FileUtil fileUtil,
                               ValidationUtil validationUtil,
                               Gson gson,
                               ModelMapper modelMapper) {

        this.employeeRepository = employeeRepository;
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() != 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return fileUtil.readFile(Constants.EMPLOYEES_IMPORT_PATH);
    }

    @Override
    public String importEmployees(String employees) throws FileNotFoundException {
        EmployeeDto[] employeeDtos = fileUtil.parseFromJson(
                gson,
                Constants.EMPLOYEES_IMPORT_PATH,
                EmployeeDto[].class
        );

        StringBuilder stringBuilder = new StringBuilder();
        Arrays.stream(employeeDtos)
                .forEach( e -> {
                    Employee newEmployee = modelMapper.map(e, Employee.class);

                    if (!validationUtil.isValid(newEmployee)) {
                        stringBuilder.append("Error: Incorrect Data!")
                                .append(System.lineSeparator());
                        return;
                    }

                    String branchName = e.getBranchName();
                    Branch savedBranch = branchRepository.findByName(branchName).orElse(null);

                    if (savedBranch == null){
                        stringBuilder.append("Error: Incorrect Data!")
                                .append(System.lineSeparator());
                        return;
                    }

                    newEmployee.setBranch(savedBranch);
                    employeeRepository.saveAndFlush(newEmployee);

                    stringBuilder.append(String.format("Successfully imported %s – %s %s.",
                                newEmployee.getClass().getSimpleName(),
                                newEmployee.getFirstName(),
                                newEmployee.getLastName()))
                            .append(System.lineSeparator());
                });

        return stringBuilder.toString();
    }

    @Override
    public String exportTopEmployees() {
        List<Employee> exported = employeeRepository.findAllByClients();
        StringBuilder stringBuilder = new StringBuilder();

        exported.forEach(
            e -> {
                stringBuilder.append(String.format("Full name: %s %s %n",
                        e.getFirstName(),
                        e.getLastName()))
                    .append(String.format("Salary: %.2f %n",
                        e.getSalary()))
                    .append(String.format("Started on : %s %n",
                        e.getStartedOn()))
                    .append("clients: ")
                    .append(System.lineSeparator());

                Set<Client> emplClients = e.getClients();
                emplClients.forEach(
                    c -> {
                        stringBuilder.append(String.format("    %s%n",
                                c.getFullName()));
                    }
                );
            }
        );

        return stringBuilder.toString();
    }
}