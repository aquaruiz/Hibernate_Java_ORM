package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.DistrictImportDto;
import mostwanted.domain.dtos.RacerImportDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
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
import java.util.Set;

@Service
public class RacerServiceImpl implements RacerService {
    private final static String RACERS_JSON_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/racers.json";

    private RacerRepository racerRepository;
    private TownRepository townRepository;
    private FileUtil fileUtil;
    private Gson gson;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    public RacerServiceImpl(RacerRepository racerRepository,
                            TownRepository townRepository,
                            FileUtil fileUtil, Gson gson,
                            ModelMapper modelMapper,
                            ValidationUtil validationUtil) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean racersAreImported() {
        return this.racerRepository.count() > 0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {
        return this.fileUtil.readFile(RACERS_JSON_FILE_PATH);
    }

    @Override
    public String importRacers(String racersFileContent) throws FileNotFoundException {
        FileReader fileReader = new FileReader(RACERS_JSON_FILE_PATH);
        RacerImportDto[] racerImportDtos = gson.fromJson(fileReader, RacerImportDto[].class);
        StringBuilder stringBuilder = new StringBuilder();

        Arrays.stream(racerImportDtos)
                .forEach(r -> {
                    String racerName = r.getName();
                    Racer foundRacer = this.racerRepository.findByName(racerName).orElse(null);

                    if (foundRacer != null){
                        stringBuilder
                                .append("Error: Duplicate Data!")
                                .append(System.lineSeparator());
                        return;
                    }

                    Racer newRacer = this.modelMapper.map(r, Racer.class);

                    String homeTownName = r.getHomeTown();
                    Town newHomeTown = this.townRepository.findByName(homeTownName).orElse(null);

                    newRacer.setHomeTown(newHomeTown);

                    boolean isValid = this.validationUtil.isValid(newRacer);
                    if (isValid){
                        stringBuilder
                                .append(String.format("Successfully imported Racer – %s.",
                                        newRacer.getName()))
                                .append(System.lineSeparator());
                        this.racerRepository.saveAndFlush(newRacer);
                        return;
                    }

                    stringBuilder
                            .append("Error: Incorrect Data!")
                            .append(System.lineSeparator());
                    return;
                });

        return stringBuilder.toString();
    }

    @Override
    public String exportRacingCars() {
        List<Racer> racingRacers = this.racerRepository.findRacerByCars();
        StringBuilder sBuilder = new StringBuilder();

        racingRacers.forEach(
                rr -> {
                    sBuilder.append(
                            String.format("Name: %s %s",
                                    rr.getName(),
                                    rr.getAge() > 0 ? rr.getAge() : ""))
                            .append(System.lineSeparator())
                            .append("Cars: ")
                            .append(System.lineSeparator());
                    Set<Car> racerCars = rr.getCars();

                    racerCars.forEach( rc ->{
                                sBuilder.append(
                                        String.format(" %s %s %d",
                                                rc.getBrand(),
                                                rc.getModel(),
                                                rc.getYearOfProduction()))
                                        .append(System.lineSeparator());
                            }
                    );

                    sBuilder.append(System.lineSeparator());
                }
        );

        return sBuilder.toString();
    }
}