package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.raceentries.RaceEntryImportDto;
import mostwanted.domain.dtos.raceentries.RaceEntryImportRootDto;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Set;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {
    private final static String RACE_ENTRIES_XML_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/race-entries.xml";

    private RaceEntryRepository raceEntryRepository;
    private RacerRepository racerRepository;
    private CarRepository carRepository;
    private FileUtil fileUtil;
    private XmlParser xmlParser;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository,
                                RacerRepository racerRepository,
                                CarRepository carRepository,
                                FileUtil fileUtil,
                                XmlParser xmlParser,
                                ModelMapper modelMapper,
                                ValidationUtil validationUtil) {

        this.raceEntryRepository = raceEntryRepository;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count() > 0;
    }

    @Override
    public String readRaceEntriesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACE_ENTRIES_XML_FILE_PATH);
    }

    @Override
    @Transactional
    public String importRaceEntries() throws JAXBException {
        RaceEntryImportRootDto raceEntryRootDto = this.xmlParser.parseXml(RaceEntryImportRootDto.class, RACE_ENTRIES_XML_FILE_PATH);
        Set<RaceEntryImportDto> raceEntryDtos = raceEntryRootDto.getRaceEntries();

        StringBuilder sBuilder = new StringBuilder();

        raceEntryDtos.forEach(
                r -> {
                    RaceEntry newRaceEntry = this.modelMapper.map(r, RaceEntry.class);
                    boolean isValid = this.validationUtil.isValid(newRaceEntry);

                    if (!isValid){
                        sBuilder.append("Error: Incorrect Data!").append(System.lineSeparator());
                        return;
                    }

                    String racerName = r.getRacer();
                    Racer newRacer = this.racerRepository.findByName(racerName).orElse(null);
                    newRaceEntry.setRacer(newRacer);

                    Integer carId = r.getCarId();
                    Car newCar = this.carRepository.findById(carId).orElse(null);
                    newRaceEntry.setCar(newCar);

                    RaceEntry saved = this.raceEntryRepository.saveAndFlush(newRaceEntry);
                    sBuilder.append(String.format("Successfully imported Race Entry – %d.",
                            saved.getId()))
                            .append(System.lineSeparator());
                    return;
                }
        );

        return sBuilder.toString();
    }
}