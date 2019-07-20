package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.DistrictImportDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

@Service
public class DistrictServiceImpl implements DistrictService{
    private final static String DISTRICT_JSON_FILE_PATH = System.getProperty("user.dir")+"/src/main/resources/files/districts.json";

    private DistrictRepository districtRepository;
    private TownRepository townRepository;
    private FileUtil fileUtil;
    private Gson gson;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository,
                               TownRepository townRepository,
                               FileUtil fileUtil,
                               Gson gson,
                               ModelMapper modelMapper,
                               ValidationUtil validationUtil) {

        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count() > 0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
        return this.fileUtil.readFile(DISTRICT_JSON_FILE_PATH);
    }

    @Override
    public String importDistricts(String districtsFileContent) throws FileNotFoundException {
        FileReader fileReader = new FileReader(DISTRICT_JSON_FILE_PATH);
        DistrictImportDto[] districtImportDtos = gson.fromJson(fileReader, DistrictImportDto[].class);
        StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(districtImportDtos)
            .forEach(d -> {
                String districtName = d.getName();
                District foundDistrict = this.districtRepository.findByName(districtName).orElse(null);

                if (foundDistrict != null){
                    stringBuilder
                            .append("Error: Duplicate Data!")
                            .append(System.lineSeparator());
                    return;
                }

                District newDistrict = this.modelMapper.map(d, District.class);

                String townName = d.getTownName();
                Town newTown = this.townRepository.findByName(townName).orElse(null);

                newDistrict.setTown(newTown);

                boolean isValid = this.validationUtil.isValid(newDistrict);
                if (isValid){
                    stringBuilder
                            .append(String.format("Successfully imported District – %s.",
                                    newDistrict.getName()))
                            .append(System.lineSeparator());
                    this.districtRepository.saveAndFlush(newDistrict);
                    return;
                }

                stringBuilder
                        .append("Error: Incorrect Data!")
                        .append(System.lineSeparator());
                return;
            });

        return stringBuilder.toString();
    }
}