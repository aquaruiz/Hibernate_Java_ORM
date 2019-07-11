package json.ex.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.ex.domain.dtos.CustomerDto;
import json.ex.domain.entities.Customer;
import json.ex.repository.CustomerRepository;
import json.ex.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final String FOLDER_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files" + File.separator;
    private final String FILE_PATH = FOLDER_PATH + File.separator +
            "input" + File.separator +
            "customers.json";

    private final CustomerRepository customerRepository;
    private final Gson gson;
    private ModelMapper modelMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public long getDbRecordsCount() {
        return this.customerRepository.count();
    }

    @Override
    public void addCustomersData() throws FileNotFoundException {
        FileReader fr = new FileReader(FILE_PATH);
        CustomerDto[] customerDtosList = gson.fromJson(fr, CustomerDto[].class);

        PropertyMap<CustomerDto, Customer> customerMap = new PropertyMap<CustomerDto, Customer>() {
            @Override
            protected void configure() {
                map().setBirthDate(source.getBirthDate());
            }
        };

        modelMapper.addMappings(customerMap);

        List<Customer> customersList = Arrays.stream(customerDtosList)
                .map(dto -> this.modelMapper.map(dto, Customer.class))
                .collect(Collectors.toList());

        this.customerRepository.saveAll(customersList);
        this.customerRepository.flush();
    }
}
