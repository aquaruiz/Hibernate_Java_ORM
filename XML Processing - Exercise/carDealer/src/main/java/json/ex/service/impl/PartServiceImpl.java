package json.ex.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import json.ex.domain.dtos.PartDto;
import json.ex.domain.entities.Part;
import json.ex.domain.entities.Supplier;
import json.ex.repository.PartRepository;
import json.ex.repository.SupplierRepository;
import json.ex.service.PartService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PartServiceImpl implements PartService {
    private final String FOLDER_PATH = "src" + File.separator +
            "main" + File.separator +
            "resources" + File.separator +
            "files" + File.separator;
    private final String FILE_PATH = FOLDER_PATH + File.separator +
            "input" + File.separator +
            "parts.json";

    private final PartRepository partRepository;
    private final SupplierRepository supplierRepository;
    private final Gson gson;
    private ModelMapper modelMapper;

    public PartServiceImpl(PartRepository partRepository, Gson gson, SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
        this.partRepository = partRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public long getDbRecordsCount() {
        return this.partRepository.count();
    }

    @Override
    public void addPartsData() throws FileNotFoundException {
        FileReader fr = new FileReader(FILE_PATH);
        PartDto[] partDtosList = gson.fromJson(fr, PartDto[].class);
        List<Part> partsList = Arrays.stream(partDtosList)
                .map(dto -> this.modelMapper.map(dto, Part.class))
                .collect(Collectors.toList());

        List<Supplier> suppliers = this.supplierRepository.findAll();

        partsList.forEach(p -> p.setSupplier(suppliers.get(randomInRange(suppliers.size()))));

        this.partRepository.saveAll(partsList);
        this.partRepository.flush();
    }

    private int randomInRange(long size) {
        Random random = new Random();

        return random.nextInt((int) size);
    }
}