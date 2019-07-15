package json.ex.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.ex.domain.dtos.query6.SaleInfoDto;
import json.ex.domain.entities.Car;
import json.ex.domain.entities.Customer;
import json.ex.domain.entities.Sale;
import json.ex.repository.CarRepository;
import json.ex.repository.CustomerRepository;
import json.ex.repository.SaleRepository;
import json.ex.service.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    private final String FOLDER_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files";
    private final String FILE_WRITE6 = FOLDER_PATH + File.separator +
            "output" + File.separator +
            "sales-discounts.json";

    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;
    private final Gson gson;
    private ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public long getDbRecordsCount() {
        return this.saleRepository.count();
    }

    @Override
    public void addSalesData() {
        List<Car> allCars = this.carRepository.findAll();
        List<Customer> allCustomers = this.customerRepository.findAll();

        for (int i = 0; i < randomInRange(100, 200); i++) {
            Sale newSale = new Sale();
            newSale.setCar(allCars.get(randomInRange(0, allCars.size() - 1)));
            newSale.setCustomer(allCustomers.get(randomInRange(0, allCustomers.size() - 1)));

            double newDiscount = 2;
            while ((newDiscount % 5 != 0 && newDiscount < 21) || (newDiscount % 10 != 0 && newDiscount > 20)){
                newDiscount = randomInRange(0, 50);
            }

            newSale.setDiscountPercentage(newDiscount);

            this.saleRepository.saveAndFlush(newSale);
        }
    }

    @Override
    public void getSalesWithAppliedDiscount() throws IOException {
        List<Sale> allSales = this.saleRepository.findAll();

        List<SaleInfoDto> dtoes = allSales.stream()
            .map(s -> {
                SaleInfoDto dto = this.modelMapper.map(s, SaleInfoDto.class);
                dto.setPriceWithDiscount();
                return dto;
            })
                .collect(Collectors.toList());

        this.saveToJson(dtoes, FILE_WRITE6);
    }

    private int randomInRange(int start, int end) {
        Random random = new Random();

        return random.nextInt((end - start) + 1) + start;
    }

    private void saveToJson(List<?> dtos, String filePath) throws IOException {
        FileWriter fr = new FileWriter(filePath);
        this.gson.toJson(dtos, fr);
        fr.flush();
    }
}