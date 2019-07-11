package json.ex.service.impl;

import json.ex.domain.entities.Car;
import json.ex.domain.entities.Customer;
import json.ex.domain.entities.Sale;
import json.ex.repository.CarRepository;
import json.ex.repository.CustomerRepository;
import json.ex.repository.SaleRepository;
import json.ex.service.SaleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
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


    private int randomInRange(int start, int end) {
        Random random = new Random();

        return random.nextInt((end - start) + 1) + start;
    }

}
