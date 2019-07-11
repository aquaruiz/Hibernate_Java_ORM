package json.ex.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.ex.domain.dtos.SupplierDto;
import json.ex.domain.entities.Supplier;
import json.ex.repository.SupplierRepository;
import json.ex.service.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final String FOLDER_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files" + File.separator;
    private final String FILE_PATH = FOLDER_PATH + File.separator +
            "input" + File.separator +
            "suppliers.json";

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
}