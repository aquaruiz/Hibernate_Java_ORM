package ex.xml.service.impl;

import ex.xml.config.ExportService;
import ex.xml.config.ImportService;
import ex.xml.config.InputConstants;
import ex.xml.config.OutputConstants;
import ex.xml.domain.dtos.CustomerDto;
import ex.xml.domain.dtos.CustomerRootDto;
import ex.xml.domain.dtos.PartRootDto;
import ex.xml.domain.dtos.query1.OrderedCustomerDto;
import ex.xml.domain.dtos.query1.OrderedCustomerRootDto;
import ex.xml.domain.dtos.query5.TotalCustomerSalesDto;
import ex.xml.domain.dtos.query5.TotalCustomersRootDto;
import ex.xml.domain.entities.Customer;
import ex.xml.repository.CustomerRepository;
import ex.xml.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public long getDbRecordsCount() {
        return this.customerRepository.count();
    }

    @Override
    public void addCustomersData() throws FileNotFoundException, JAXBException {
        CustomerRootDto customerRootDto = ImportService
                .getFromXml(InputConstants.FILE_CUSTOMERS_PATH, CustomerRootDto.class);

        CustomerDto[] customerDtos = customerRootDto.getCustomers();
        List<Customer> customersList = ImportService.mappDtoToEntity(customerDtos, Customer.class);

        this.customerRepository.saveAll(customersList);
        this.customerRepository.flush();
    }

    @Override
    @Transactional
    public void getOrderedCustomers() throws IOException, JAXBException {
        List<Customer> allCustomers = this.customerRepository.getAllOrderedByBirthDateAsc();

        List<OrderedCustomerDto> orderedCustomerDtos = ExportService.mappEntitiesToDtos(allCustomers, OrderedCustomerDto.class);
        OrderedCustomerRootDto dtoToXml = new OrderedCustomerRootDto();
        dtoToXml.setCustomers(orderedCustomerDtos);

        ExportService.saveToXml(OutputConstants.FILE_QUERY1, dtoToXml);
    }

    @Override
    public void getTotalSalesByCustomer() throws IOException, JAXBException {
        List<List<String>> sellingCustomers = this.customerRepository.getAllBySalesAndOrder();

        List<TotalCustomerSalesDto> customerSalesDtos = new LinkedList<>();
        for (List<String> customer : sellingCustomers) {
            TotalCustomerSalesDto newDto = new TotalCustomerSalesDto();
            newDto.setFullName(customer.get(0));
            newDto.setBoughtCars(Integer.parseInt(customer.get(1)));
            newDto.setSpentMoney(new BigDecimal(customer.get(2)));
            customerSalesDtos.add(newDto);
        }

        TotalCustomersRootDto dtoToXml = new TotalCustomersRootDto();
        dtoToXml.setCustomers(customerSalesDtos);
        ExportService.saveToXml(OutputConstants.FILE_QUERY5, dtoToXml);
    }
}