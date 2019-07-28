package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.config.Constants;
import softuni.exam.domain.dtos.json.PlayerDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Player;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.PictureRepository;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.repository.TeamRepository;
import softuni.exam.util.FileUtil;
import softuni.exam.util.JsonParser;
import softuni.exam.util.ValidatorUtil;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class PlayerServiceImpl implements PlayerService {

    private TeamRepository teamRepository;
    private PictureRepository pictureRepository;
    private PlayerRepository playerRepository;
    private FileUtil fileUtil;
    private Gson gson;
    private JsonParser jsonParser;
    private ValidatorUtil validatorUtil;
    private ModelMapper modelMapper;

    public PlayerServiceImpl(TeamRepository teamRepository,
                             PictureRepository pictureRepository,
                             PlayerRepository playerRepository,
                             FileUtil fileUtil,
                             Gson gson,
                             JsonParser jsonParser,
                             ValidatorUtil validatorUtil,
                             ModelMapper modelMapper) {

        this.teamRepository = teamRepository;
        this.pictureRepository = pictureRepository;
        this.playerRepository = playerRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.jsonParser = jsonParser;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public String importPlayers() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        PlayerDto[] playerDtos = jsonParser.parseJSON(gson, Constants.PLAYERS_IMPORT_PATH, PlayerDto[].class);

        Arrays.stream(playerDtos).forEach(p -> {
            Player newPlayer = modelMapper.map(p, Player.class);

            Picture savedPicture = pictureRepository.findByUrl(p.getPicture().getUrl()).orElse(null);

            if (savedPicture == null){
                sb.append("Invalid player").append(System.lineSeparator());
                return;
            }

            Team savedTeam = teamRepository.findByName(p.getTeam().getName()).orElse(null);

            if (savedTeam == null){
                sb.append("Invalid player").append(System.lineSeparator());
                return;
            }

            newPlayer.setPicture(savedPicture);
            newPlayer.setTeam(savedTeam);

            if (!validatorUtil.isValid(newPlayer)){
                sb.append("Invalid player").append(System.lineSeparator());
                return;
            }

            playerRepository.saveAndFlush(newPlayer);

            sb.append(String.format("Successfully imported %s: %s %s",
                        newPlayer.getClass().getSimpleName().toLowerCase(),
                        newPlayer.getFirstName(),
                        newPlayer.getLastName()))
                    .append(System.lineSeparator());
        });

        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        return fileUtil.readFile(Constants.PLAYERS_IMPORT_PATH);
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        StringBuilder sb = new StringBuilder();

        List<Player> richestPlayers = playerRepository.findAllBySalaryGreaterThanOrderBySalaryDesc(Constants.MIN_SALARY_SIZE);

        richestPlayers.stream().forEach(rp -> {
            sb.append(String.format("Player name: %s %s",
                    rp.getFirstName(),
                    rp.getLastName()))
                .append(System.lineSeparator())
                .append(String.format("\tNumber: %d",
                        rp.getNumber()))
                .append(System.lineSeparator())
                .append(String.format("\tSalary: %.2f",
                        rp.getSalary()))
                .append(System.lineSeparator())
                .append(String.format("\tTeam: %s",
                        rp.getTeam().getName()))
                .append(System.lineSeparator());
        });

        return sb.toString();
    }

    @Override
    public String exportPlayersInATeam() {
        StringBuilder sb = new StringBuilder();
        sb.append("Team: ")
                .append(Constants.TEAM_NAME)
                .append(System.lineSeparator());

        List<Player> playersInTeam = playerRepository.findAllByTeamName(Constants.TEAM_NAME);

        playersInTeam.stream().forEach(p -> {
        sb.append(String.format("\tPlayer name: %s %s - %s",
                    p.getFirstName(),
                    p.getLastName(),
                    p.getPosition().name()))
                .append(System.lineSeparator())
                .append(String.format("\tNumber: %d",
                        p.getNumber()))
                .append(System.lineSeparator());
        });

        return sb.toString();
    }
}