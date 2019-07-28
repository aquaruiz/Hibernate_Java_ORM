package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.config.Constants;
import softuni.exam.domain.dtos.TeamDto;
import softuni.exam.domain.dtos.TeamRoodDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.ValidatorUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private TeamRepository teamRepository;
    private PictureRepository pictureRepository;
    private FileUtil fileUtil;
    private XmlParser xmlParser;
    private ValidatorUtil validatorUtil;
    private ModelMapper modelMapper;

    public TeamServiceImpl(TeamRepository teamRepository,
                           PictureRepository pictureRepository,
                           FileUtil fileUtil,
                           XmlParser xmlParser,
                           ValidatorUtil validatorUtil,
                           ModelMapper modelMapper) {

        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public String importTeams() throws JAXBException {
        StringBuilder sb = new StringBuilder();

        List<TeamDto> teamDtos = xmlParser.parseXml(Constants.TEAMS_IMPORT_PATH, TeamRoodDto.class)
                                            .getTeamDtoList();

        teamDtos.stream().forEach(t -> {
            Picture savedPicture = pictureRepository.findByUrl(t.getPicture().getUrl()).orElse(null);

            if (savedPicture == null) {
                sb.append("Invalid team").append(System.lineSeparator());
//                sb.append("Invalid picture").append(System.lineSeparator());
                return;
            }

            Team newTeam = modelMapper.map(t, Team.class);
            newTeam.setPicture(savedPicture);

            if (!validatorUtil.isValid(newTeam)){
                sb.append("Invalid Team").append(System.lineSeparator());
                return;
            }

            teamRepository.saveAndFlush(newTeam);

            sb.append(String.format("Successfully imported %s - %s",
                    newTeam.getClass().getSimpleName().toLowerCase(),
                    newTeam.getName()))
                .append(System.lineSeparator());
        });

        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        return fileUtil.readFile(Constants.TEAMS_IMPORT_PATH);
    }
}