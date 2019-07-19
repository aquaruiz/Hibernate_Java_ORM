package softuni.workshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.workshop.domain.dtos.*;
import softuni.workshop.domain.entities.Company;
import softuni.workshop.domain.entities.Employee;
import softuni.workshop.error.Constants;
import softuni.workshop.repository.CompanyRepository;
import softuni.workshop.repository.EmployeeRepository;
import softuni.workshop.util.FileUtil;
import softuni.workshop.util.ValidatorUtil;
import softuni.workshop.util.XmlParser;

import javax.validation.ConstraintViolation;
import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;
    private FileUtil fileUtil;
    private XmlParser xmlParser;
    private ValidatorUtil validatorUtil;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository, ModelMapper modelMapper, FileUtil fileUtil, XmlParser xmlParser, ValidatorUtil validatorUtil) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void importEmployees() throws FileNotFoundException, JAXBException {
        BufferedReader reader = this.fileUtil.readFile(Constants.EMPLOYEES_PATH_IMPORT);
        EmployeeRootDto rootEmployees = this.xmlParser.getFromXml(reader, EmployeeRootDto.class);

        List<EmployeeDto> employeeDtos = rootEmployees.getEmployees();

        List<Employee> employees = employeeDtos.stream()
                .map(e -> {
                    Set<ConstraintViolation<EmployeeDto>> violations = this.validatorUtil.makeValidation(e);
                    if (violations.size() > 0){
                        violations.forEach(
                                v -> System.out.println(v.getMessage())
                        );

                        return null;
                    } else {
                        Employee newEmployee = this.modelMapper.map(e, Employee.class);
//                        String companyName = e.getProject().getCompanyName();
//                        Company newCompany = this.companyRepository.findByName(companyName).orElse(null);
//                        newEmployee.getProject().setCompany(newCompany);
//                        System.out.println();
                        return newEmployee;
                    }
                })
                .collect(Collectors.toList());

        this.employeeRepository.saveAll(employees);
    }

    @Override
    public boolean areImported() {
       return this.employeeRepository.count() > 0;
    }

    @Override
    public String readEmployeesXmlFile() throws IOException {
        return fileUtil.getXmltoString(Constants.EMPLOYEES_PATH_IMPORT);
    }

    @Override
    public String exportEmployeesWithAgeAbove() throws JAXBException, IOException {
        List<Employee> employeesAfter25 = this.employeeRepository.findAllByAgeAfter(25);
        EmployeeRootDto employeeRootDto = new EmployeeRootDto();
        List<EmployeeDto> employeesToExport = employeesAfter25.stream()
                .map(e -> this.modelMapper.map(e, EmployeeDto.class))
                .collect(Collectors.toList());

        employeeRootDto.setEmployees(employeesToExport);
        this.xmlParser.saveRootDtoToXml(employeeRootDto, Constants.EXPORT_25_AGE_EMPLOYEES_PATH);

        return this.fileUtil.getXmltoString(Constants.EXPORT_25_AGE_EMPLOYEES_PATH);
    }
}