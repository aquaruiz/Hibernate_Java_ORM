package ex.xml.service.impl;

import ex.xml.config.ExportService;
import ex.xml.config.ImportService;
import ex.xml.config.InputConstants;
import ex.xml.config.OutputConstants;
import ex.xml.domain.dtos.SupplierDto;
import ex.xml.domain.dtos.SupplierRootDto;
import ex.xml.domain.dtos.query3.LocalSupplierDto;
import ex.xml.domain.dtos.query3.LocalSupplierRootDto;
import ex.xml.domain.entities.Supplier;
import ex.xml.repository.SupplierRepository;
import ex.xml.service.SupplierService;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public long getDbRecordsCount() {
        return this.supplierRepository.count();
    }

    @Override
    public void addSuppliersData() throws FileNotFoundException, JAXBException {
        System.out.println();
        SupplierRootDto supplierRootDto = ImportService
                .getFromXml(InputConstants.FILE_SUPPLIERS_PATH, SupplierRootDto.class);
        SupplierDto[] suppliersDtos = supplierRootDto.getSuppliers();

        List<Supplier> suppliersList = ImportService.mappDtoToEntity(suppliersDtos, Supplier.class);
        this.supplierRepository.saveAll(suppliersList);
        this.supplierRepository.flush();
    }

    @Override
    public void getLocalSuppliers() throws IOException, JAXBException {
        List<Supplier> allLocalSuppliers = this.supplierRepository.findAllByIsImporterFalse();
        List<LocalSupplierDto> localSupplierDtos = new LinkedList<>();

        for (Supplier s : allLocalSuppliers) {
            LocalSupplierDto dto = ExportService.mappOneEntityToDto(s, LocalSupplierDto.class);
            dto.setPartsCount(s.getParts().size());
            localSupplierDtos.add(dto);
        }

        LocalSupplierRootDto dtoToXml = new LocalSupplierRootDto();
        dtoToXml.setSuppliers(localSupplierDtos);

        ExportService.saveToXml(OutputConstants.FILE_QUERY3, dtoToXml);
    }
}
