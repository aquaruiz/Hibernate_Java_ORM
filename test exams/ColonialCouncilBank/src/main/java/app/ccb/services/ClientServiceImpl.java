package app.ccb.services;

import app.ccb.config.Constants;
import app.ccb.domain.dtos.ClientDto;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.ClientRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

@Service
public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;
    private FileUtil fileUtil;
    private ValidationUtil validationUtil;
    private Gson gson;
    private ModelMapper modelMapper;

    public ClientServiceImpl(ClientRepository clientRepository,
                             EmployeeRepository employeeRepository,
                             FileUtil fileUtil,
                             ValidationUtil validationUtil,
                             Gson gson,
                             ModelMapper modelMapper) {

        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean clientsAreImported() {
        return this.clientRepository.count() != 0;
    }

    @Override
    public String readClientsJsonFile() throws IOException {
        return fileUtil.readFile(Constants.CLIENTS_IMPORT_PATH);
    }

    @Override
    public String importClients(String clients) throws FileNotFoundException {
        ClientDto[] clientDtos = fileUtil.parseFromJson(
                gson,
                Constants.CLIENTS_IMPORT_PATH,
                ClientDto[].class
        );

        StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(clientDtos)
                .forEach( c -> {
                    Client newClient = modelMapper.map(c, Client.class);

                    if (!validationUtil.isValid(newClient)){
                        stringBuilder.append("Error: Incorrect Data!")
                                .append(System.lineSeparator());
                        return;
                    }

                    Client savedClient = clientRepository.findByFullName(c.getFullName()).orElse(null);

                    if (savedClient != null){
                        newClient = savedClient;
                    }

                    String employeeFName = c.getEmployeeName().split("\\s+")[0];
                    String employeeLName = c.getEmployeeName().split("\\s+")[1];
                    Employee savedEmployee = employeeRepository.findByFirstNameAndLastName(employeeFName, employeeLName).orElse(null);

                    if (savedEmployee == null){
                        stringBuilder.append("Error: Incorrect Data!")
                                .append(System.lineSeparator());
                        return;
                    }

                    newClient.addEmployee(savedEmployee);
                    clientRepository.saveAndFlush(newClient);

                    stringBuilder.append(String.format("Successfully imported %s – %s.",
                                newClient.getClass().getSimpleName(),
                                newClient.getFullName()))
                            .append(System.lineSeparator());

                });

        return stringBuilder.toString();
    }

    @Override
    public String exportFamilyGuy() {
        // TODO : Implement Me
        return null;
    }
}
