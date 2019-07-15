package ex.xml.service.impl;

import ex.xml.config.ImportService;
import ex.xml.config.InputConstants;
import ex.xml.config.RandomService;
import ex.xml.domain.dtos.PartRootDto;
import ex.xml.domain.entities.Part;
import ex.xml.domain.entities.Supplier;
import ex.xml.repository.PartRepository;
import ex.xml.repository.SupplierRepository;
import ex.xml.service.PartService;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class PartServiceImpl implements PartService {
    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;

    public PartServiceImpl(PartRepository partRepository, SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
        this.partRepository = partRepository;
    }

    @Override
    public long getDbRecordsCount() {
        return this.partRepository.count();
    }

    @Override
    public void addPartsData() throws FileNotFoundException, JAXBException {
        PartRootDto partRootDto = ImportService.getFromXml(InputConstants.FILE_PARTS_PATH, PartRootDto.class);
        List<Part> partsList = ImportService.mappDtoToEntity(partRootDto.getParts(), Part.class);

        List<Supplier> suppliers = this.supplierRepository.findAll();

        partsList.forEach(p -> p.setSupplier(
                suppliers.get(
                    RandomService.getRandomInt(
                            0, suppliers.size() - 1))));

        this.partRepository.saveAll(partsList);
        this.partRepository.flush();
    }
}