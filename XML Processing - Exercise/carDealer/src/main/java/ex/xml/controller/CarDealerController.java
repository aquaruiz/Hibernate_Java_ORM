package ex.xml.controller;

import ex.xml.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;

@Controller
public class CarDealerController implements CommandLineRunner {
    private final SupplierService supplierService;
    private final PartService partService;
    private final CarService carService;
    private final CustomerService customerService;
    private final SaleService saleService;

    public CarDealerController(SupplierService supplierService, PartService partService, CarService carService, CustomerService customerService, SaleService saleService) {
        this.supplierService = supplierService;
        this.partService = partService;
        this.carService = carService;
        this.customerService = customerService;
        this.saleService = saleService;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.populateDb();

        // query 1
//        this.customerService.getOrderedCustomers();

        // query 2
//        this.carService.getCarsFromMake("Toyota");

        // query 3
//        this.supplierService.getLocalSuppliers();

        // query 4
//        this.carService.getCarsWithPartsList();

        // query 5
//        this.customerService.getTotalSalesByCustomer();

        // query 6
//        this.saleService.getSalesWithAppliedDiscount();
    }

    private void populateDb() throws FileNotFoundException {
        if (this.supplierService.getDbRecordsCount() < 1){
            this.supplierService.addSuppliersData();
        }

//        if (this.partService.getDbRecordsCount() < 1){
//            this.partService.addPartsData();
//        }
//
//        if (this.carService.getDbRecordsCount() < 1){
//            this.carService.addCarsData();
//        }
//
//        if (this.customerService.getDbRecordsCount() < 1){
//            this.customerService.addCustomersData();
//        }
//
//        if (this.saleService.getDbRecordsCount() < 1){
//            this.saleService.addSalesData();
//        }
    }
}