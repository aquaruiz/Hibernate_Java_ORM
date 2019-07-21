package alararestaurant.service;

import alararestaurant.config.Constants;
import alararestaurant.domain.dtos.EmployeeImportDto;
import alararestaurant.domain.entities.Employee;
import alararestaurant.domain.entities.Position;
import alararestaurant.repository.EmployeeRepository;
import alararestaurant.repository.PositionRepository;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private PositionRepository positionRepository;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private ModelMapper modelMapper;
    private Gson gson;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               PositionRepository positionRepository,
                               FileUtil fileUtil,
                               ValidationUtil validationUtil,
                               ModelMapper modelMapper,
                               Gson gson) {

        this.employeeRepository = employeeRepository;
        this.positionRepository = positionRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return fileUtil.readFile(Constants.EMPLOYEES_IMPORT_PATH);
    }

    @Override
    public String importEmployees(String employees) throws FileNotFoundException {
        StringBuilder sBuilder = new StringBuilder();

        EmployeeImportDto[] employeeDtos = fileUtil.parseJSON(gson,
            Constants.EMPLOYEES_IMPORT_PATH,
            EmployeeImportDto[].class);

        Arrays.stream(employeeDtos)
            .forEach(
            e -> {
                Employee newEmployee = modelMapper.map(e, Employee.class);

                if (!validationUtil.isValid(newEmployee)) {
                    sBuilder.append("Invalid data format.")
                            .append(System.lineSeparator());
                    return;
                }

                String positionName = e.getPosition();

                Position newPosition = positionRepository.findByName(positionName).orElse(null);

                if (newPosition == null){
                    newPosition = new Position();
                    newPosition.setName(positionName);

                    if (!validationUtil.isValid(newPosition)){
                        return;
                    }
                }

                Position savedPosition = this.positionRepository.saveAndFlush(newPosition);
                newEmployee.setPosition(newPosition);

                this.employeeRepository.saveAndFlush(newEmployee);
                sBuilder.append(String.format("Record %s successfully imported.",
                        newEmployee.getName()))
                        .append(System.lineSeparator());
                }
            );

        return sBuilder.toString();
    }
}