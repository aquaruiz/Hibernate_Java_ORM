package ex.xml.service.impl;

import ex.xml.config.ExportService;
import ex.xml.config.OutputConstants;
import ex.xml.config.RandomService;
import ex.xml.domain.dtos.query6.SaleInfoDto;
import ex.xml.domain.dtos.query6.SaleInfoRootDto;
import ex.xml.domain.entities.Car;
import ex.xml.domain.entities.Customer;
import ex.xml.domain.entities.Sale;
import ex.xml.repository.CarRepository;
import ex.xml.repository.CustomerRepository;
import ex.xml.repository.SaleRepository;
import ex.xml.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    @Autowired
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

        for (int i = 0; i < RandomService.getRandomInt(100, 200); i++) {
            Sale newSale = new Sale();
            newSale.setCar(allCars.get(RandomService.getRandomInt(0, allCars.size() - 1)));
            newSale.setCustomer(allCustomers.get(RandomService.getRandomInt(0, allCustomers.size() - 1)));

            double newDiscount = 2;
            while ((newDiscount % 5 != 0 && newDiscount < 21) || (newDiscount % 10 != 0 && newDiscount > 20)){
                newDiscount = RandomService.getRandomInt(0, 50);
            }

            newSale.setDiscountPercentage(newDiscount);

            this.saleRepository.saveAndFlush(newSale);
        }
    }

    @Override
    public void getSalesWithAppliedDiscount() throws IOException, JAXBException {
        List<Sale> allSales = this.saleRepository.findAll();

        List<SaleInfoDto> dtoes = allSales.stream()
            .map(s -> {
                SaleInfoDto dto = ExportService.mappOneEntityToDto(s, SaleInfoDto.class);
                dto.setPriceWithDiscount();
                return dto;
            })
                .collect(Collectors.toList());

        SaleInfoRootDto dtoToXml = new SaleInfoRootDto();
        dtoToXml.setSales(dtoes);
        System.out.println();
        ExportService.saveToXml(OutputConstants.FILE_QUERY6, dtoToXml);
    }
}