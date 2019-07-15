package ex.xml.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SaleService {
    long getDbRecordsCount();

    void addSalesData();

    void getSalesWithAppliedDiscount() throws IOException, JAXBException;
}
