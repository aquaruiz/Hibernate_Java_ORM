package ex.xml.service;

import java.io.IOException;

public interface SaleService {
    long getDbRecordsCount();

    void addSalesData();

    void getSalesWithAppliedDiscount() throws IOException;
}
