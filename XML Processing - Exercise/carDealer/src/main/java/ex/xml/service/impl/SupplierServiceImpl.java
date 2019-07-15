package ex.xml.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ex.xml.domain.dtos.SupplierDto;
import ex.xml.domain.dtos.query3.LocalSupplierDto;
import ex.xml.domain.entities.Supplier;
import ex.xml.repository.SupplierRepository;
import ex.xml.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final String FOLDER_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files";
    private final String FILE_PATH = FOLDER_PATH + File.separator +
            "input" + File.separator +
            "suppliers.xml";
    private final String FILE_WRITE3 = FOLDER_PATH + File.separator +
            "output" + File.separator +
            "local-suppliers.xml";

    private final SupplierRepository supplierRepository;
    private final Gson gson;
    private ModelMapper modelMapper;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        this.modelMapper = new ModelMapper();
    }

    @Override
    public long getDbRecordsCount() {
        return this.supplierRepository.count();
    }

    @Override
    public void addSuppliersData() throws FileNotFoundException {
        FileReader fr = new FileReader(FILE_PATH);
        SupplierDto[] supplierDtosList = gson.fromJson(fr, SupplierDto[].class);
        List<Supplier> suppliersList = Arrays.stream(supplierDtosList)
                .map(dto -> this.modelMapper.map(dto, Supplier.class))
                .collect(Collectors.toList());

        this.supplierRepository.saveAll(suppliersList);
        this.supplierRepository.flush();
    }

    @Override
    public void getLocalSuppliers() throws IOException {
        List<Supplier> allLocalSuppliers = this.supplierRepository.findAllByIsImporterFalse();

//        PropertyMap<Supplier, LocalSupplierDto> suuplierMap = new PropertyMap<Supplier, LocalSupplierDto>() {
//            @Override
//            protected void configure() {
//                map().setPartsCount(source.getParts().size());
//            }
//        };
//
//        modelMapper.addMappings(suuplierMap);

        List<LocalSupplierDto> localSupplierDtos = allLocalSuppliers.stream()
                .map(s -> {
                      LocalSupplierDto dto = this.modelMapper.map(s, LocalSupplierDto.class);
                      dto.setPartsCount(s.getParts().size());
                      return dto;
                    }
                )
                .collect(Collectors.toList());

        this.saveToJson(localSupplierDtos, FILE_WRITE3);
        System.out.println();
    }

    private void saveToJson(List<?> dtos, String filePath) throws IOException {
        FileWriter fr = new FileWriter(filePath);
        this.gson.toJson(dtos, fr);
        fr.flush();
    }
}
