package json.ex.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.ex.domain.dtos.CustomerDto;
import json.ex.domain.dtos.query1.OrderedCustomerDto;
import json.ex.domain.dtos.query5.TotalCustomerSalesDto;
import json.ex.domain.entities.Customer;
import json.ex.repository.CustomerRepository;
import json.ex.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final String FOLDER_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files";
    private final String FILE_PATH = FOLDER_PATH + File.separator +
            "input" + File.separator +
            "customers.json";
    private final String FILE_WRITE1 = FOLDER_PATH + File.separator +
            "output" + File.separator +
            "ordered-customers.json";
    private final String FILE_WRITE4 = FOLDER_PATH + File.separator +
            "output" + File.separator +
            "customers-total-sales.json";

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

    @Override
    @Transactional
    public void getOrderedCustomers() throws IOException {
        List<Customer> allCustomers = this.customerRepository.getAllOrderedByBirthDateAsc();

        List<OrderedCustomerDto> orderedCustomerDtos = allCustomers.stream()
                .map(c -> this.modelMapper.map(c, OrderedCustomerDto.class))
                .collect(Collectors.toList());

        saveToJson(orderedCustomerDtos, FILE_WRITE1);
    }

    @Override
    public void getTotalSalesByCustomer() throws IOException {
        List<List<String>> sellingCustomers = this.customerRepository.getAllBySalesAndOrder();

        List<TotalCustomerSalesDto> dtos = new LinkedList<>();
        for (List<String> customer : sellingCustomers) {
            TotalCustomerSalesDto newDto = new TotalCustomerSalesDto();
            newDto.setFullName(customer.get(0));
            newDto.setBoughtCars(Integer.parseInt(customer.get(1)));
            newDto.setSpentMoney(new BigDecimal(customer.get(2)));
            dtos.add(newDto);
        }

        this.saveToJson(dtos, FILE_WRITE4);
    }

    private void saveToJson(List<?> dtos, String filePath) throws IOException {
        FileWriter fr = new FileWriter(filePath);
        this.gson.toJson(dtos, fr);
        fr.flush();
    }
}