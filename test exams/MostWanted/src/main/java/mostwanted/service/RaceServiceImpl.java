package mostwanted.service;

import com.google.gson.Gson;
import mostwanted.domain.dtos.RacerImportDto;
import mostwanted.domain.dtos.races.EntryImportDto;
import mostwanted.domain.dtos.races.EntryImportRootDto;
import mostwanted.domain.dtos.races.RaceImportDto;
import mostwanted.domain.dtos.races.RaceImportRootDto;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.repository.*;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RaceServiceImpl implements RaceService {
    private final static String RACES_XML_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/files/races.xml";

    private RaceRepository raceRepository;
    private DistrictRepository districtRepository;
    private RaceEntryRepository raceEntryRepository;
    private FileUtil fileUtil;
    private XmlParser xmlParser;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    public RaceServiceImpl(RaceRepository raceRepository,
                           DistrictRepository districtRepository,
                           RaceEntryRepository raceEntryRepository,
                           FileUtil fileUtil,
                           XmlParser xmlParser,
                           ModelMapper modelMapper,
                           ValidationUtil validationUtil) {

        this.raceRepository = raceRepository;
        this.districtRepository = districtRepository;
        this.raceEntryRepository = raceEntryRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean racesAreImported() {
        return this.raceRepository.count() > 0;
    }

    @Override
    public String readRacesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACES_XML_FILE_PATH);
    }

    @Override
    @Transactional
    public String importRaces() throws JAXBException {
        RaceImportRootDto raceRootDto = this.xmlParser.parseXml(RaceImportRootDto.class, RACES_XML_FILE_PATH);

        List<RaceImportDto> raceImportDtos = raceRootDto.getRaces();

        StringBuilder sBuilder = new StringBuilder();
        raceImportDtos.stream()
                .forEach(
                        r -> {
                            Race newRace = this.modelMapper.map(r, Race.class);
                            String districtName = r.getDistrictName();
                            District newDistrict = this.districtRepository.findByName(districtName).orElse(null);

                            newRace.setDistrict(newDistrict);

                            List<RaceEntry> newRaceEntries = new ArrayList<>();
                            Set<EntryImportDto> raceEntries = r.getEntires().getEntires();

                            raceEntries.forEach(
                                    re -> {
                                        Integer newRaceEntryId = re.getId();
                                        RaceEntry newRaceEntry = raceEntryRepository.findById(newRaceEntryId).orElse(null);
                                        newRaceEntries.add(newRaceEntry);
                                    }
                            );

                            newRace.setEntries(newRaceEntries);
                            Race saved = this.raceRepository.saveAndFlush(newRace);

                            sBuilder.append(String
                                            .format("Successfully imported Race – %d.",
                                                    saved.getId()))
                                    .append(System.lineSeparator());
                            return;
                        });

        return sBuilder.toString();
    }
}