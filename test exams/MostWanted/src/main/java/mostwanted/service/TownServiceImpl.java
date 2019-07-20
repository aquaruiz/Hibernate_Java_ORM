package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.TownImportDto;
import mostwanted.domain.entities.Town;
import mostwanted.repository.TownRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TownServiceImpl implements TownService{
    private final static String TOWNS_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/towns.json";

    private TownRepository townRepository;
    private FileUtil fileUtil;
    private Gson gson;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    public TownServiceImpl(TownRepository townRepository,
                           FileUtil fileUtil,
                           Gson gson,
                           ModelMapper modelMapper,
                           ValidationUtil validationUtil) {

        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return this.fileUtil.readFile(TOWNS_JSON_FILE_PATH);
    }

    @Override
    public String importTowns(String townsFileContent) throws FileNotFoundException {
        FileReader fileReader = new FileReader(TOWNS_JSON_FILE_PATH);
        TownImportDto[] townImportDtos = gson.fromJson(fileReader, TownImportDto[].class);
        StringBuilder stringBuilder = new StringBuilder();

        List<Town> towns = Arrays.stream(townImportDtos)
                .map(t -> {
                    Town newTown = this.modelMapper.map(t, Town.class);
                    boolean isValid = this.validationUtil.isValid(newTown);
                    if (isValid){
                        stringBuilder
                                .append(String.format("Successfully imported Town – %s.",
                                    newTown.getName()))
                                .append(System.lineSeparator());
                        return newTown;
                    }

                    stringBuilder
                            .append("Error: Incorrect Data!")
                            .append(System.lineSeparator());
                    return null;
                })
                .collect(Collectors.toList());

        this.townRepository.saveAll(towns);
        this.townRepository.flush();

        return stringBuilder.toString();
    }

    @Override
    public String exportRacingTowns() {
        List<Town> racerTowns = this.townRepository.findByRacersIsNotNull();

        StringBuilder sBuilder = new StringBuilder();
        racerTowns.forEach(
                rt -> sBuilder.append(
                                     String.format("Name: %s",
                                        rt.getName()))
                            .append(System.lineSeparator())
                            .append(
                                    String.format("Racers: %d",
                                        rt.getRacers().size()))
                            .append(System.lineSeparator())
                            .append(System.lineSeparator())
        );
        return sBuilder.toString();
    }
}
